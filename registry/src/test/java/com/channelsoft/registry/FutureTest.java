package com.channelsoft.registry;

import org.junit.Test;

import java.util.concurrent.*;

public class FutureTest {
    @Test
    public void futureTest() throws ExecutionException, InterruptedException {
        ExecutorService executor = Executors.newSingleThreadExecutor();
        //返回值其实就是FutureTask
        Future future = executor.submit(new Callable<Object>() {
            @Override
            public Object call() throws Exception {
                return "hello test";
            }
        });
        Thread.sleep(1000);
        System.out.println("is done? " + future.isDone());
        String result = (String) future.get();
        System.out.println(result);
    }
}
