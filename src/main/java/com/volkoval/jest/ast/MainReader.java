package com.volkoval.jest.ast;

import com.volkoval.jest.ast.stream.AstLispStreamBuilder;
import com.volkoval.jest.ast.stream.FileStreamReader;

/**
 * Created with IntelliJ IDEA.
 * User: avvolkov
 * Date: 04.07.15
 * Time: 23:52
 */
public class MainReader {

    public static void main(String[] args) throws InterruptedException {
        final FileStreamReader streamReader = new FileStreamReader();
        final AstLispStreamBuilder streamBuilder = new AstLispStreamBuilder(streamReader);
        Thread threadOne = new Thread(new Runnable() {
            @Override
            public void run() {
                streamReader.readResourceAsStream("test.lisp");
            }
        });
        Thread threadTwo = new Thread(new Runnable() {
            @Override
            public void run() {
                AstTree tree = streamBuilder.build();
                System.out.println(tree);
            }
        });

        threadTwo.start();
        threadOne.start();
        threadOne.join();
        threadTwo.join();
    }
}
