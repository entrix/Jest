package com.volkoval.jest.xml.sax;

import org.xml.sax.Attributes;
import org.xml.sax.ContentHandler;
import org.xml.sax.Locator;
import org.xml.sax.SAXException;

import java.util.Arrays;
import java.util.List;

import static java.lang.System.out;

/**
 * Created with IntelliJ IDEA.
 * User: avvolkov
 * Date: 11.01.14
 * Time: 18:04
 */
public class FB2Handler implements ContentHandler {

    private boolean read;

    @Override
    public void setDocumentLocator(Locator locator) {
        //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public void startDocument() throws SAXException {
        //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public void endDocument() throws SAXException {
        //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public void startPrefixMapping(String prefix, String uri) throws SAXException {
        //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public void endPrefixMapping(String prefix) throws SAXException {
        //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public void startElement(String uri, String localName, String qName, Attributes atts) throws SAXException {
        if (!qName.equals("p")) {
            read = false;
        }
        else {
            read = true;
        }
    }

    @Override
    public void endElement(String uri, String localName, String qName) throws SAXException {
        //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public void characters(char[] ch, int start, int length) throws SAXException {

        if (!read) {
            return;
        }

        List<List<String>> sentences = LinguaUtil.toSentences(Arrays.copyOfRange(ch, start, start + length));

        for (List<String> sentence : sentences) {
            for (String word : sentence) {
                System.out.print(word);
                System.out.print(' ');
            }
            out.println(' ');
        }
    }

    @Override
    public void ignorableWhitespace(char[] ch, int start, int length) throws SAXException {
        //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public void processingInstruction(String target, String data) throws SAXException {
        //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public void skippedEntity(String name) throws SAXException {
        //To change body of implemented methods use File | Settings | File Templates.
    }
}
