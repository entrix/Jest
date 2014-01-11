package com.volkoval.jest.xml.sax;

import org.junit.Test;
import org.xml.sax.SAXException;
import org.xml.sax.XMLReader;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import java.io.File;
import java.io.IOException;

/**
 * Created with IntelliJ IDEA.
 * User: avvolkov
 * Date: 11.01.14
 * Time: 18:16
 */
public class SAXParserTest {

    @Test
    public void FB2Test() {
        SAXParserFactory spf = SAXParserFactory.newInstance();
        spf.setNamespaceAware(true);
        SAXParser saxParser;
        XMLReader xmlReader;

        try {
            saxParser = spf.newSAXParser();
            xmlReader = saxParser.getXMLReader();
            FB2Handler retriever = new FB2Handler();
            xmlReader.setContentHandler(retriever);
            xmlReader.parse(convertToFileURL("doc/fb2/Gertruda.fb2"));
        } catch (ParserConfigurationException e) {
            e.printStackTrace();
        } catch (SAXException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static String convertToFileURL(String filename) {
        String path = new File(filename).getAbsolutePath();
        if (File.separatorChar != '/') {
            path = path.replace(File.separatorChar, '/');
        }

        if (!path.startsWith("/")) {
            path = "/" + path;
        }
        return "file:" + path;
    }

}
