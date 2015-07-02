package com.volkoval.jest.ast;

import java.util.ArrayDeque;

/**
 * Created with IntelliJ IDEA.
 * User: avvolkov
 * Date: 01.07.15
 * Time: 17:13
 */
public class AstTree {
    AstNode rootNode;

    public AstTree(AstNode rootNode) {
        this.rootNode = rootNode;
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        int depth = 0;
        ArrayDeque<ExtNode> children = new ArrayDeque<>();
        children.addFirst(new ExtNode(0, rootNode));
        while (children.size() > 0) {
            ExtNode node = children.pollFirst();
            if (node.node instanceof OperatorNode) {
                for (AstNode astNode : ((OperatorNode)node.node).getNodes()) {
                    children.addFirst(new ExtNode(node.depth + 1, astNode));
                }
            }
            for (int i = 0; i < node.depth; ++i) {
                builder.append(' ');
            }
            if (node.node instanceof OperatorNode) {
                builder.append(((OperatorNode)node.node).getOperand().getName());
                builder.append("\n");
            }
            else {
                builder.append(((OperandNode)node.node).getValue());
                builder.append("\n");
            }
        }
        return builder.toString();
    }

    private static class ExtNode {
        int depth;
        AstNode node;

        private ExtNode(int depth, AstNode node) {
            this.depth = depth;
            this.node = node;
        }
    }
}
