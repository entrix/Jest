package com.volkoval.jest.xslt;

import org.junit.Test;

import javax.xml.transform.Source;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerConfigurationException;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.stream.StreamResult;
import javax.xml.transform.stream.StreamSource;
import java.io.File;
import java.io.FileNotFoundException;

import static java.lang.System.out;
/**
 * Created with IntelliJ IDEA.
 * User: avvolkov
 * Date: 14.01.14
 * Time: 19:49
 */
public class XSLTTranslatorTest {

//    @Test
    public void transformTest() {
        try {
        TransformerFactory factory = TransformerFactory.newInstance();
        Source xslt = new StreamSource(new File("files/xslt/XXGL042.xsl"));
        Transformer transformer = factory.newTransformer(xslt);

        Source text = new StreamSource(new File("files/xslt/XXGL042.xml"));
        transformer.transform(text, new StreamResult(new File("files/xslt/XXGL042_new.xml")));
        } catch (TransformerConfigurationException e) {
            e.printStackTrace();
            out.println(e.getMessage());
        } catch (TransformerException e) {
            e.printStackTrace();
            out.println(e.getMessage());
        }
    }

    @Test
    public void XalanTransformTest() {
        try {
            // 1. Instantiate a TransformerFactory.
            javax.xml.transform.TransformerFactory tFactory =
                    javax.xml.transform.TransformerFactory.newInstance();

            // 2. Use the TransformerFactory to process the stylesheet Source and
            //    generate a Transformer.
            javax.xml.transform.Transformer transformer = tFactory.newTransformer
                    (new javax.xml.transform.stream.StreamSource("files/xslt/XXGL042.xsl"));

            // 3. Use the Transformer to transform an XML Source and send the
            //    output to a Result object.
            transformer.transform
                    (new javax.xml.transform.stream.StreamSource("files/xslt/XXGL042.xml"),
                            new javax.xml.transform.stream.StreamResult( new
                                    java.io.FileOutputStream("files/xslt/XXGL042_new.xml")));
        } catch (FileNotFoundException e) {
            e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
        } catch (TransformerConfigurationException e) {
            e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
        } catch (TransformerException e) {
            e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
        }
    }
}


