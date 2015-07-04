package com.volkoval.jest.ast;

/**
 * Created with IntelliJ IDEA.
 * User: avvolkov
 * Date: 01.07.15
 * Time: 17:16
 */
public class MathOperatorNode extends AbstractAstNode {

    private MathOperand mathOperand;

    public MathOperatorNode(MathOperand mathOperand) {
        this.mathOperand = mathOperand;
    }

    public MathOperatorNode(MathOperand mathOperand, AbstractAstNode... args) {
        this.mathOperand = mathOperand;
        setNodes(args);
    }

    public MathOperand getMathOperand() {
        return mathOperand;
    }

    public Object evaluate() {
        double val = (double) nodes.get(0).evaluate();

        for (int i = 1; i < nodes.size(); ++i) {
            double nextVal = (Double) nodes.get(i).evaluate();
            switch (mathOperand) {
                case PLUS:
                    val += nextVal;
                    break;
                case MINUS:
                    val -= nextVal;
                    break;
                case MULTIPLY:
                    val *= nextVal;
                    break;
                case DIVIDE:
                    val /= nextVal;
                    break;
                case MOD:
                    val %= nextVal;
                    break;
                case POW:
                    Math.exp(nextVal * Math.log(val));
                    break;
            }
        }

        return new Double(val);
    }
}
