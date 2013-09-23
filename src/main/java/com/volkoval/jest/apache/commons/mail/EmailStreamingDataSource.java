package com.volkoval.jest.apache.commons.mail;

/**
 * Created with IntelliJ IDEA.
 * User: avvolkov
 * Date: 09.09.13
 * Time: 11:41
 * To change this template use File | Settings | File Templates.
 */

import javax.activation.DataSource;
import java.io.*;

/**
 * Created with IntelliJ IDEA.
 * User: avvolkov
 * Date: 06.09.13
 * Time: 13:07
 * To change this template use File | Settings | File Templates.
 */
public class EmailStreamingDataSource implements DataSource {

    private InputStream is;
    public static final int BUFFER_SIZE = 512;
    private String type = "application/octet-stream";

    public EmailStreamingDataSource(InputStream aIs, String aType) throws IOException {
        is = aIs;
    }

    @Override
    public InputStream getInputStream() throws IOException {
        return is;
    }

    @Override
    public OutputStream getOutputStream() throws IOException {
        return new PipedOutputStream((PipedInputStream)is);
    }

    @Override
    public String getContentType() {
        return this.type == null ? "application/octet-stream" : this.type;
    }

    @Override
    public String getName() {
        return "EmailStreamingDataSource";
    }
}
