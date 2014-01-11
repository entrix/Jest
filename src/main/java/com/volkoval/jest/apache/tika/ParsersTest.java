package com.volkoval.jest.apache.tika;

import org.apache.tika.Tika;
import org.apache.tika.exception.TikaException;
import org.junit.Test;

import java.io.File;
import java.io.IOException;

import static java.lang.System.out;

/**
 * Created with IntelliJ IDEA.
 * User: avvolkov
 * Date: 11.01.14
 * Time: 17:38
 */
public class ParsersTest {

    @Test
    public void microsoftWordTest() {
        File document = new File("doc/fb2/Gertruda.fb2");
        String content = "";
        try {
            content = new Tika().parseToString(document);
        } catch (IOException e) {
            e.printStackTrace();
        } catch (TikaException e) {
            e.printStackTrace();
        }
        out.println(content);
    }
}
