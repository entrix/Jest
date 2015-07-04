package com.volkoval.jest.ast.stream;

import com.volkoval.jest.ast.AstLispBuilder;
import com.volkoval.jest.ast.AbstractAstNode;
import com.volkoval.jest.ast.AstTree;

import java.util.ArrayDeque;
import java.util.LinkedList;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: avvolkov
 * Date: 02.07.15
 * Time: 14:58
 */
public class AstLispStreamBuilder extends AstLispBuilder {

    private volatile StringBuffer buffer = new StringBuffer();

    private FileStreamReader reader;

    public AstLispStreamBuilder(FileStreamReader reader) {
        this.reader = reader;
    }

    @Override
    public AstTree build() {
        char[] buf = null;
        List<String> tokens = null;
        ArrayDeque<AbstractAstNode> nodes = new ArrayDeque<>();
        try {
            while ((buf = reader.getBuf()) != null) {
                tokens = new LinkedList<>(tokenizer.parse(buf));
                constructInfixList(tokens, nodes);
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return new AstTree(nodes.poll());
    }
}
