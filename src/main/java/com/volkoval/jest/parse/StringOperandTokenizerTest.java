package com.volkoval.jest.parse;

import org.junit.Test;

import java.util.List;

import static java.lang.System.out;

/**
 * Created with IntelliJ IDEA.
 * User: avvolkov
 * Date: 01.07.15
 * Time: 16:39
 */
public class StringOperandTokenizerTest {

    @Test
    public void test() {
        String source = "+ aor + 4 + c";
        List<String> tokens = StringOperandTokenizer.parse(source);
        for (String token : tokens) {
            out.println(token);
        }
    }
}
