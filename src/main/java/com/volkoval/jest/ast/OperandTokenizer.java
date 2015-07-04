package com.volkoval.jest.ast;

import java.util.ArrayList;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: avvolkov
 * Date: 02.07.15
 * Time: 15:03
 */
public class OperandTokenizer {

    public List<String> parse(String source) {
        List<String> tokens = new ArrayList<>();
        final char buf[] = source.toCharArray();
        final int len = buf.length;

        return parse(buf);
    }

    public List<String> parse(char buf[]) {
        List<String> tokens = new ArrayList<>();
        final int len = buf.length;

        for (int i = 0; i < len;) {
            StringBuilder token = new StringBuilder();
            while(i < buf.length && !isOperand(buf[i])) {
                if (buf[i] == '(' || buf[i] == ')') {
                    tokens.add(new Character(buf[i]).toString());
                }
                i++;
            }
            // ignore comments
            if (i < buf.length && buf[i] == ';') {
                while (buf[i] != '\n') {
                    i++;
                }
                i++;
                continue;
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
        if (c != '.' && c != ',' && c != ' ' && c != '(' && c != ')' && c != '\r' && c != '\n' && c != '\0') {
            return true;
        }
        return false;
    }
}
