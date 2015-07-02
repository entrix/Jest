package com.volkoval.jest.ast;

import java.util.ArrayDeque;
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

    protected ArrayDeque<AstNode> constructInfixList(List<String> tokens, ArrayDeque<AstNode> prevNodes) {
        ArrayDeque<AstNode> nodes = prevNodes == null ?
                new ArrayDeque<AstNode>() : prevNodes;
        boolean hasPrevOp = false;
        for (String token : tokens) {
            Operand operand = null;
            if (Operand.isOperand(token)) {
                operand = Operand.getOperand(token);
            }
            if (operand == null) {
                if (hasPrevOp) {
                    if (isGroup(token)) {
                        if (token.equals("(")) {
                            hasPrevOp = false;
                        }
                        else {
                            throw new RuntimeException("invalid expr before ')'");
                        }
                    }
                    else {
                        OperatorNode op = (OperatorNode) nodes.pollLast();
                        AstNode argOne = nodes.pollLast();
                        AstNode argTwo = new OperandNode(token);
                        op.setNodes(argOne, argTwo);
                        nodes.addLast(op);
                        hasPrevOp = false;
                    }
                }
                else {
                    if (isGroup(token)) {
                        if (token.equals("(")) {
                            throw new RuntimeException("invalid expr before '('");
                        }
                        else {
                            AstNode argOne = nodes.pollLast();
                            OperatorNode op = (OperatorNode) nodes.pollLast();
                            AstNode argTwo = nodes.pollLast();
                            op.setNodes(argOne, argTwo);
                            nodes.addLast(op);
                        }
                    }
                    else {
                        nodes.addLast(new OperandNode(token));
                    }
                }
            }
            else {
                nodes.addLast(new OperatorNode(operand));
                hasPrevOp = true;
            }
        }
        return nodes;
    }
    private boolean isGroup(String token) {
        if (token.equals("(") || token.equals(")")) {
            return true;
        }
        return false;
    }

}