package com.volkoval.jest.ast;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: avvolkov
 * Date: 01.07.15
 * Time: 17:11
 */
public abstract class AstLispBuilder {

    protected OperandTokenizer tokenizer = new OperandTokenizer();

    public abstract AstTree build();

    protected ArrayDeque<AbstractAstNode> constructInfixList(List<String> tokens, ArrayDeque<AbstractAstNode> prevNodes) {
        ArrayDeque<AbstractAstNode> nodes = prevNodes == null ?
                new ArrayDeque<AbstractAstNode>() : prevNodes;
        boolean hasPrevBrace = false;
        int braceCount = 0;
        for (String token : tokens) {
            if (isGroup(token)) {
                if (token.equals("(")) {
                    if (!hasPrevBrace) {
                        hasPrevBrace = true;
                    }
                    braceCount++;
                    nodes.addLast(new AstNode(token));
                }
                else {
                    if (!hasPrevBrace) {
                        throw new RuntimeException("invalid expr before ')'");
                    }
                    reverseConstruct(nodes);
                    braceCount--;
                }
            }
            else {
                MathOperand mathOperand = null;
                if (MathOperand.isOperand(token)) {
                    mathOperand = MathOperand.getOperand(token);
                    nodes.addLast(new MathOperatorNode(mathOperand));
                }
                else {
                    nodes.addLast(new AstNode(token));
                }
            }
        }
        if (braceCount > 0) {
            throw new RuntimeException("The count of '(' braces doesn't equl to count of ')' braces");
        }
        return nodes;
    }

    private void reverseConstruct(ArrayDeque<AbstractAstNode> nodes) {
        List<AbstractAstNode> opNodeArgs = new ArrayList<>();
        try {
            do {
                if (nodes.size() == 0) {
                    throw new RuntimeException("Open brace '(' not found.");
                }
                opNodeArgs.add(nodes.removeLast());
            } while (!nodes.getLast().evaluate().equals("("));
            // remove brace '('
            nodes.pollLast();
            // construct operator
            AbstractAstNode opNode = opNodeArgs.remove(opNodeArgs.size() - 1);
            // add operator back to the queue
            nodes.add(constructOperator(opNode, opNodeArgs));
        } catch (ClassCastException ex) {
            throw new RuntimeException("We haven't got operator node at the head of list");
        }
    }

    private AbstractAstNode constructOperator(AbstractAstNode headNode, List<AbstractAstNode> tailNodes) {
        if (headNode.getNodes() == null) {
            headNode.setNodes(tailNodes);
            return headNode;
        }
        AstNode rootNode = new AstNode(headNode);
        List<AbstractAstNode> childNodes = new ArrayList<>(tailNodes);
        rootNode.setNodes(childNodes);
        return rootNode;
    }

    private boolean isGroup(String token) {
        if (token.equals("(") || token.equals(")")) {
            return true;
        }
        return false;
    }

}
