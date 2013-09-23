package com.volkoval.jest.concurrent;

import org.junit.Test;

import java.util.Random;

import static java.lang.System.currentTimeMillis;
import static java.lang.System.out;

/**
 * Created with IntelliJ IDEA.
 * User: avvolkov
 * Date: 23.09.13
 * Time: 17:53
 * To change this template use File | Settings | File Templates.
 */
public class ConcurrentSortTest {

    private static Random randomizer = new Random(currentTimeMillis());

    @Test
    public void concurrentSortTest() throws InterruptedException {
        int[] numbers = new int[100000];
        SortThread[] workers = new SortThread[10];

        numbers[0] = randomizer.nextInt();
        for (int i = 1; i < 100000; ++i) {
            numbers[i] = randomizer.nextInt();

            if (i % 10000 == 0) {
                workers[i/10000 - 1] = new SortThread(i/10000 - 1);
                workers[i/10000 - 1].setElements(numbers, i - 10000, i);
            }
        }
        workers[9] = new SortThread(9);
        workers[9].setElements(numbers, 90000, 100000);

        for (int i = 0; i < 10; ++i) {
            workers[i].start();
        }

        for (Thread worker : workers) {
            if (worker.isAlive()) {
                worker.join();
            }
        }

    }

    static class SortThread extends Thread {

        private int   index;
        private int[] elems;

        public SortThread(int index) {
            super();
            this.index = index;
        }

        @Override
        public void run() {
            long  start = currentTimeMillis();
            long  end;

            out.println("start thread #" + index + " at " + start);
            bubbleSort();
            end = currentTimeMillis();
            out.println("end   thread #" + index + " at " + end + ", duration = " + (end - start) + " ms");
        }

        public void setElements(int[] elements, int startIndex, int endIndex) {
            int diff = endIndex - startIndex;

            if (diff < 0) {
                throw new IndexOutOfBoundsException("endIndex must be equal or more than startIndex");
            }
            elems = new int[diff];
            for (int i = startIndex; i < endIndex; ++i) {
                elems[i - startIndex] = elements[i];
            }
        }

        public void bubbleSort() {
            int maxReplacedIndex = 0;
            int limit = elems.length;

            do {
                maxReplacedIndex = 0;
                for (int i = 1; i < limit; ++i) {
                    if (elems[i-1] > elems[i]) {
                        swap(i - 1, i);
                        maxReplacedIndex = i;
                    }
                }
                limit = maxReplacedIndex;
            } while (limit > 0);
        }

        public void swap(int from, int to) {
            int tmp     = elems[from];
            elems[from] = elems[to];
            elems[to]   = tmp;
        }
    }
}
