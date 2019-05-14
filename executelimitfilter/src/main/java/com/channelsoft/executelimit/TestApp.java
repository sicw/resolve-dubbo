package com.channelsoft.executelimit;

import java.util.concurrent.CountDownLatch;

/**
 * @author sicwen
 * @date 2019/05/14
 */
public class TestApp {

    private static CountDownLatch latch = new CountDownLatch(1);

    private static DubboExecuteLimitFilter limitFilter = new DubboExecuteLimitFilter();

    public static void main(String[] args) throws InterruptedException {
        for (int i = 1; i <= 11; i++) {
            new Thread(new RunTask("url"+i)).start();
        }
        Thread.sleep(500);
        latch.countDown();
    }

    private static class RunTask implements Runnable{

        private String url;

        public RunTask(String url){
            this.url = url;
        }

        @Override
        public void run() {
            try {
                latch.await();
                System.out.println(limitFilter.invoke(url));
            } catch (InterruptedException e) {
                e.printStackTrace();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

}