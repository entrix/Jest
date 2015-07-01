package com.volkoval.jest.ast;

import java.util.ArrayList;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: avvolkov
 * Date: 01.07.15
 * Time: 15:08
 */
public class StringOperandTokenizer {

    public StringOperandTokenizer() {
        this.nothing();
    }

    public void nothing() {
        Object obj = StringOperandTokenizer.this;
    }

    public static List<String> parse(String source) {
        List<String> tokens = new ArrayList<>();
        final char buf[] = source.toCharArray();
        final int len = buf.length;

        for (int i = 0; i < len;) {
            StringBuilder token = new StringBuilder();
            while(i < buf.length && !isOperand(buf[i])) {
                if (buf[i] == '(' || buf[i] == ')') {
                    tokens.add(new Character(buf[i]).toString());
                }
                i++;
            }
            while(i < buf.length && isOperand(buf[i])) {
                token.append(buf[i]);
                i++;
            }
            if (token.length() > 0) {
                tokens.add(token.toString());
            }
        }
        return tokens;
    }

    private static boolean isOperand(char c) {
        if (c != '.' && c != ',' && c != ' ' && c != '(' && c != ')') {
            return true;
        }
        return false;
    }
}
