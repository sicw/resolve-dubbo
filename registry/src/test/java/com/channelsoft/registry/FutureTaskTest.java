package com.channelsoft.registry;

import org.junit.Test;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;

public class FutureTaskTest {
    @Test
    public void testFutureTask() throws ExecutionException, InterruptedException {
        FutureTask task = new FutureTask(new Callable() {
            @Override
            public Object call() throws Exception {
                return "hello test";
            }
        });
        Thread t = new Thread(task);
        t.setDaemon(false);
        t.setName("FutureTask");
        t.start();
        System.out.println("is done? " + task.isDone());
        System.out.println(task.get());
    }
}