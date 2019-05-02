package com.channelsoft.shutdownhook;

public class ShutDownHook {
    public static void main( String[] args ) throws InterruptedException {
        Thread t1 = new Thread(new Runnable() {
            @Override
            public void run() {
                //获取java application中唯一的Runtime单实例
                //该方法的意思是当java application退出时，会调用hook方法
                Runtime.getRuntime().addShutdownHook(new Thread(new Runnable() {
                    @Override
                    public void run() {
                        System.out.println("Execute Hook ......");
                    }
                }));
                try {
                    Thread.sleep(5000);
                    System.out.println("T1 thread exiting ......");
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });
        t1.setDaemon(false);
        t1.start();
        Thread.sleep(1000);
        System.out.println("app exiting ......");
    }
}
