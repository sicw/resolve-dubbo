package com.channelsoft.registry;

import org.junit.Test;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;

public class FutureTaskDoneTest {
    @Test
    public void testTaskDone() throws ExecutionException, InterruptedException {
        FutureTask task = new ListenableFutureTask(new Callable() {
            @Override
            public Object call() throws Exception {
                return "hello test";
            }
        });

        Thread t = new Thread(task);
        t.setName("TestTaskDone");
        t.setDaemon(false);
        t.start();
        System.out.println(task.get());
    }

    private class ListenableFutureTask extends FutureTask{

        public ListenableFutureTask(Callable callable) {
            super(callable);
        }

        //在异步线程执行完毕后，会调用该方法执行回调
        @Override
        protected void done() {
            System.out.println("Task is done");
        }
    }

}
