package com.volkoval.jest.parse;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;

/**
 * Created with IntelliJ IDEA.
 * User: avvolkov
 * Date: 01.07.15
 * Time: 15:08
 */
public class StringOperandTokenizer {

    static Pattern pattern = Pattern.compile("\\w");

    public static List<String> parse(String source) {
        List<String> tokens = new ArrayList<>();
        final char buf[] = source.toCharArray();
        final int len = buf.length;

        for (int i = 0; i < len;) {
            StringBuilder token = new StringBuilder();
            while(i < buf.length && !isOperand(buf[i])) {
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
        if ((c >= 48 && c <= 52) || (c >= 65 && c <= 90) || (c >= 97 && c <= 122)) {
            return true;
        }
        return false;
    }
}
