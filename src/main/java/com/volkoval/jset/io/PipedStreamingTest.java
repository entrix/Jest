package com.volkoval.jset.io;

import org.junit.Before;
import org.junit.Test;

import java.io.*;

import static java.lang.System.out;
import static java.lang.System.err;

/**
 * Created with IntelliJ IDEA.
 * User: avvolkov
 * Date: 07.09.13
 * Time: 17:22
 * To change this template use File | Settings | File Templates.
 */
public class PipedStreamingTest {

    private PipedInputStream  piStream;
    private PipedOutputStream poutStream;

    @Before
    public void setUp() throws IOException {
        piStream   = new PipedInputStream();
        poutStream = new PipedOutputStream(piStream);
    }

    @Test
    public void pipedWriteReadTest() {
        BufferedInputStream  bis = new BufferedInputStream(piStream, 10);
        byte[] buf = new byte[10];
        // create new thread in which we write some data to pipedOutputStream
        new Runnable() {
            @Override
            public void run() {
                BufferedOutputStream bos = new BufferedOutputStream(poutStream, 10);
                try {
                    for (String str : new String[] {
                            "Sasha was walking along highway",
                            System.getProperty("line.separator"),
                            "and was sucking something",
                            System.getProperty("line.separator")
                    }) {
                        bos.write(str.getBytes());
                    }
                }
                catch (Exception e) {
                    err.println("error occured while writing data to pipe");
                    e.printStackTrace();
                }
                finally {
                    try {
                        bos.close();
                    } catch (IOException e) {
                        err.println("error occured while closing output pipe");
                        e.printStackTrace();
                    }
                }
            }
        }.run();
        // read was written data
        try {
            while (bis.available() > 0) {
                bis.read(buf);
                out.print(new String(buf));
            }
        } catch (IOException e) {
            err.println("error occured while reading data from pipe");
            e.printStackTrace();
        }
        finally {
            try {
                bis.close();
            } catch (IOException e) {
                err.println("error occured while closing input pipe");
                e.printStackTrace();
            }
        }
    }
}
