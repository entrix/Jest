package com.volkoval.jest.ast;

import java.util.ArrayDeque;

/**
 * Created with IntelliJ IDEA.
 * User: avvolkov
 * Date: 01.07.15
 * Time: 17:13
 */
public class AstTree {
    AbstractAstNode rootNode;

    public AstTree(AbstractAstNode rootNode) {
        this.rootNode = rootNode;
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        ArrayDeque<ExtNode> children = new ArrayDeque<>();
        children.addFirst(new ExtNode(0, rootNode));
        while (children.size() > 0) {
            ExtNode node = children.pollFirst();
            if (node.node.evaluate() instanceof AstNode) {
                AstNode childNode = (AstNode) node.node.evaluate();
                children.addFirst(new ExtNode(node.depth + 1, childNode));
            }
            if (node.node.getNodes() != null) {
                for (AbstractAstNode abstractAstNode : node.node.getNodes()) {
                    children.addFirst(new ExtNode(node.depth + 1, abstractAstNode));
                }
            }
            for (int i = 0; i < node.depth; ++i) {
                builder.append(' ');
            }
            if (node.node instanceof MathOperatorNode) {
                builder.append(((MathOperatorNode)node.node).getMathOperand().getName());
                builder.append("\n");
            }
            else {
                builder.append(node.node.evaluate());
                builder.append("\n");
            }
        }
        return builder.toString();
    }

    private static class ExtNode {
        int depth;
        AbstractAstNode node;

        private ExtNode(int depth, AbstractAstNode node) {
            this.depth = depth;
            this.node = node;
        }
    }
}
