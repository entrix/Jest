package com.volkoval.jest.ast.stream;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.LinkedList;
import java.util.Queue;

/**
 * Created with IntelliJ IDEA.
 * User: avvolkov
 * Date: 02.07.15
 * Time: 15:08
 */
public class FileStreamReader {

    private volatile boolean isRead = false;
    private volatile boolean isFinished = false;

    private char[] storageBuf = new char[BUF_SIZE];

    private Queue<byte[]> queue = new LinkedList<>();

    private static final int BUF_SIZE = 4096;

    public synchronized void readResourceAsStream(String fileName) {
        try {
            InputStream is = new FileInputStream(fileName);
            byte[] buf = new byte[BUF_SIZE];
            while (is.available() > 0) {
                is.read(buf);
                queue.add(buf.clone());
                copyBuf();
            }
            queue.add(buf.clone());
            while (!queue.isEmpty() && isRead) {
                wait();
                copyBuf();
            }
            isFinished = true;
            notify();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    private synchronized void copyBuf() throws InterruptedException {
        if (isRead) {
            return;
        }
        byte[] localBuf = queue.poll();
        for (int i = 0; i < localBuf.length; ++i) {
            storageBuf[i] = (char) localBuf[i];
        }
        isRead = true;
        try {
            notify();
        }
        catch (IllegalMonitorStateException ex) {
            ex.printStackTrace();
        }
    }

    public synchronized char[] getBuf() throws InterruptedException {
        while(!isRead) {
            wait();
        }
        if (isFinished) {
            return null;
        }
        char[] buf = new char[BUF_SIZE];
        for (int i = 0; i < storageBuf.length; ++i) {
            buf[i] = storageBuf[i];
        }
        isRead = false;
        notify();
        return buf;
    }
}
