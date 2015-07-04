package com.volkoval.jest.ast;

/**
 * Created with IntelliJ IDEA.
 * User: avvolkov
 * Date: 01.07.15
 * Time: 17:08
 */
public enum MathOperand {

    PLUS("+"), MINUS("-"), MULTIPLY("*"), DIVIDE("/"), MOD("%"), POW("^");

    String name;

    private MathOperand(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public static MathOperand getOperand(String name) {
        for (MathOperand mathOperand : values()) {
            if (mathOperand.getName().equals(name)) {
                return mathOperand;
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
