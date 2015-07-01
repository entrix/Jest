package com.volkoval.jest.ast;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: avvolkov
 * Date: 01.07.15
 * Time: 17:16
 */
public class OperatorNode extends AstNode {

    private List<AstNode> nodes;
    private Operand operand;

    public OperatorNode(Operand operand) {
        this.operand = operand;
    }

    public OperatorNode(Operand operand, AstNode ... args) {
        this.operand = operand;
        setNodes(args);
    }

    public Operand getOperand() {
        return operand;
    }

    public List<AstNode> getNodes() {
        return nodes;
    }

    public void setNodes(AstNode ... args) {
        this.nodes = new ArrayList<>(Arrays.asList(args));
    }

    public double evaluate() {
        double val = nodes.get(0).evaluate();

        for (int i = 1; i < nodes.size(); ++i) {
            switch (operand) {
                case PLUS:
                    val += nodes.get(i).evaluate();
                    break;
                case MINUS:
                    val -= nodes.get(i).evaluate();
                    break;
                case MULTIPLY:
                    val *= nodes.get(i).evaluate();
                    break;
                case DIVIDE:
                    val /= nodes.get(i).evaluate();
                    break;
                case MOD:
                    val %= nodes.get(i).evaluate();
                    break;
                case POW:
                    Math.exp(nodes.get(1).evaluate() * Math.log(val));
                    break;
            }
        }

        return val;
    }
}
