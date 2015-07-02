package com.volkoval.jest.ast;

import com.volkoval.jest.ast.stream.AstLispStreamBuilder;
import com.volkoval.jest.ast.stream.FileStreamReader;
import org.junit.Test;

/**
 * Created with IntelliJ IDEA.
 * User: avvolkov
 * Date: 02.07.15
 * Time: 14:56
 */
public class AstLispStringBuilder extends AstLispBuilder {

    private String source;

    @Override
    public AstTree build() {
       return buildFromStringSource(source);
    }

    private AstTree buildFromStringSource(String source) {
        if (source == null) {
            return null;
        }
        return new AstTree(
                constructInfixList(
                        tokenizer.parse(source), null).poll());
    }

    public void setSource(String source) {
        this.source = source;
    }

    @Test
    public void test() throws InterruptedException {
//        AstTree ast = buildFromStringSource("a + b + c * (v + w)");
//        System.out.println(ast);

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
