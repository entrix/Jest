package com.volkoval.jest.ast;

/**
 * Created with IntelliJ IDEA.
 * User: avvolkov
 * Date: 01.07.15
 * Time: 17:29
 */
public class AstNode extends AbstractAstNode {

    private Object value;

    public AstNode(Object value) {
        this.value = value;
    }

    @Override
    public Object evaluate() {
        return value;
    }

    public Object getValue() {
        return value;
    }

    @Override
    public String toString() {
        return value.toString();
    }
}
