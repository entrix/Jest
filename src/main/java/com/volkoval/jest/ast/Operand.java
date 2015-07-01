package com.volkoval.jest.ast;

/**
 * Created with IntelliJ IDEA.
 * User: avvolkov
 * Date: 01.07.15
 * Time: 17:08
 */
public enum Operand {

    PLUS("+"), MINUS("-"), MULTIPLY("*"), DIVIDE("/"), MOD("%"), POW("^");

    String name;

    private Operand(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public static Operand getOperand(String name) {
        for (Operand operand : values()) {
            if (operand.getName().equals(name)) {
                return operand;
            }
        }
        return null;
    }

    public static boolean isOperand(String name) {
        if (getOperand(name) != null) {
            return true;
        }
        return false;
    }
}
