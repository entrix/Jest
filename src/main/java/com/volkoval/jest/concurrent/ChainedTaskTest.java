package com.volkoval.jest.concurrent;

import org.junit.Test;

import java.util.ArrayDeque;
import java.util.Queue;
import java.util.Random;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import static java.lang.System.out;

/**
 * Created with IntelliJ IDEA.
 * User: avvolkov
 * Date: 04.03.14
 * Time: 15:53
 */
public class ChainedTaskTest {

    private static Random random = new Random();
//    private static boolean isLocked = false;
    private static final Queue<Integer> queue = new ArrayDeque<Integer>();
    private static Integer currentTask = 0;
    private static final ExecutorService executorService = Executors.newSingleThreadExecutor();
    private static final ICallback callback = new ICallback() {

        private int taskNumber;

        @Override
        public void setTaskNumber(int taskNumber) {
            this.taskNumber = taskNumber;
        }

        @Override
        public void doIt() throws CloneNotSupportedException {
            out.println("Task #" + currentTask + " successfully ended.");
            currentTask = queue.poll();
            if (currentTask == null) {
                currentTask = 9;
            }
            taskNumber++;
            executorService.submit(new Task(callback, 3, 300, taskNumber));
            out.println("Task #" + taskNumber + " added to executor service.");
        }
    };

    @Test
    public void chaindeTaskTest() {
        addTask(3, 300, 0);
        addTask(3, 300, 1);
        addTask(3, 300, 2);
        addTask(3, 300, 3);
        addTask(3, 300, 4);
        addTask(3, 300, 5);
        addTask(3, 300, 6);
        addTask(3, 300, 7);
        addTask(3, 300, 8);
        addTask(3, 300, 9);
    }
    
    private void addTask(int startVal, int iterationCount, int taskNumber) {
        if (taskNumber > 0) {
            queue.add(taskNumber);
        }
        else {
            executorService.submit(new Task(callback, startVal, iterationCount, taskNumber));
            out.println("Task #" + taskNumber + " added to executor service.");
        }
    }

    private interface ICallback extends Cloneable {
        public void setTaskNumber(int taskNumber);
        public void doIt() throws CloneNotSupportedException;
    }

    private static class Task implements Callable<Integer> {
        private ICallback callback;
        private int startVal;
        private int iterationCount;
        private int taskNumber;

        public Task(ICallback callback, int startVal, int iterationCount, int taskNumber) {
            this.callback = callback;
            this.startVal = startVal;
            this.iterationCount = iterationCount;
            this.taskNumber = taskNumber;
        }

        @Override
        public Integer call() throws Exception {
            for (int i = 0; i < iterationCount; ++i) {
                startVal *= startVal % startVal;
                while (startVal == 0) {
                    startVal = Math.abs(random.nextInt() % 10);
                }
            }
            callback.doIt();
            return startVal;
        }
    }
}
