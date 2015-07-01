package com.volkoval.jest.ast;

/**
 * Created with IntelliJ IDEA.
 * User: avvolkov
 * Date: 01.07.15
 * Time: 17:29
 */
public class OperandNode extends AstNode{

    private String value;

    public OperandNode(String value) {
        this.value = value;
    }

    @Override
    public double evaluate() {
        return Double.valueOf(value);
    }

    public String getValue() {
        return value;
    }
}
