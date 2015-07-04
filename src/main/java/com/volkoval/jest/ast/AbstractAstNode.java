package com.volkoval.jest.ast;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: avvolkov
 * Date: 01.07.15
 * Time: 17:15
 */
public abstract class AbstractAstNode {

    protected List<AbstractAstNode> nodes;

    public abstract Object evaluate();

    public List<AbstractAstNode> getNodes() {
        return nodes;
    }

    public void setNodes(AbstractAstNode... args) {
        this.nodes = new ArrayList<>(Arrays.asList(args));
    }

    public void setNodes(List<AbstractAstNode> args) {
        this.nodes = new ArrayList<>(args);
    }
}
