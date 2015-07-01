package com.volkoval.jest.lang.reflect;

import junit.framework.Assert;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: avvolkov
 * Date: 17.07.14
 * Time: 18:30
 */
public class ForEachElementsOrderTest {

    @Test
    public void elementsOrderTest() {
        List<Integer> elems = new ArrayList<Integer>();

        for (int i = 0; i < 100; ++i) {
            elems.add(i);
        }

        int ind = 0;
        for (int e : elems) {
            System.out.println(e);
            Assert.assertTrue("Element's order is violated!", e == ind);
            ind++;
        }
    }
}
