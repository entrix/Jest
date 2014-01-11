package com.volkoval.jest.xml.sax;

/**
 * Created with IntelliJ IDEA.
 * User: avvolkov
 * Date: 11.01.14
 * Time: 18:12
 */

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.regex.Pattern;

/**
 * Created with IntelliJ IDEA.
 * User: avvolkov
 * Date: 21.06.13
 * Time: 12:47
 * To change this template use File | Settings | File Templates.
 */
public class    LinguaUtil {


    private static Random random = new Random(System.currentTimeMillis());


    private static Pattern punctuationPattern =
            Pattern.compile("[\\r\\n\\t\\\"\\'\\`\\,\\:\\;\\(\\)\\[\\]\\{\\}\\@\\#\\$\\%\\^\\&\\*\\=\\+]");
    private static String whiteSpacePattern = "\\ +";


    public static List<List<String>> toSentences(char[] ch) {
        List<List<String>> sentences = new ArrayList<List<String>>();
        StringBuilder builder = new StringBuilder();
        List<String> items = new ArrayList<String>();

        for (int i = 0; i < ch.length; ++i) {

            if (!isCyrillic(ch[i]) ) {
                while (i < ch.length && ch[i] != '.' && ch[i] != '?' && ch[i] != '!' && ch[i] != ' ') {
                    i++;
                }

                if (i == ch.length) {
                    return sentences;
                }
            }

//            if (Character.isUpperCase(ch[i])) {
//                ch[i] = Character.toLowerCase(ch[i]);
//            }
            if (ch[i] != '.' && ch[i] != '?' && ch[i] != '!') {
                builder.append(ch[i]);
            }
//            else if (Character.isUpperCase(ch[i + 1]) || Character.isUpperCase(ch[i + 2]) ||
//                    Character.isUpperCase(ch[i + 3]) && random.nextBoolean()) {
//                builder.append(ch[i]);
//            }
            else {

                String sentence = punctuationPattern.matcher(builder.toString()).replaceAll("");

                sentence = sentence.replaceAll(whiteSpacePattern, " ").trim();

                for (String str : sentence.split(" ")) {
                    if (!str.isEmpty()) {
                        items.add(str);
                    }
                }

                sentences.add(new ArrayList<String>(items));
                items.clear();
                builder = new StringBuilder();
            }
        }

        return sentences;
    }

    public static boolean isCyrillic(char c) {
        return Character.UnicodeBlock.CYRILLIC.equals(Character.UnicodeBlock.of(c));
    }

}