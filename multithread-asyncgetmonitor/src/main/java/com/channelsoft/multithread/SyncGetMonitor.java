package com.channelsoft.multithread;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * 同步的获取monitor，会占用业务逻辑时间
 */
public class SyncGetMonitor {

    private Map<String,Object> monitors = new ConcurrentHashMap<String,Object>();

    private Lock lock = new ReentrantLock();

    public static void main(String[] args) throws InterruptedException {
        SyncGetMonitor app = new SyncGetMonitor();

        new Thread(new Runnable() {
            @Override
            public void run() {
                Object monitor = app.getMonitor("test");
                System.out.println(monitor);
            }
        }).start();

        Object monitor = app.getMonitor("test");
        System.out.println(monitor);
        Thread.sleep(100);
    }

    /**
     * 会有多个线程调用该方法
     * 但相同的url要确保返回相同的monitor
     * @param url
     * @return
     */
    public Object getMonitor(String url){
        Object monitor = monitors.get(url);
        if(monitor != null){
            return monitor;
        }
        try{
            //会有多个线程阻塞在这里
            lock.lock();
            //所以要加上双重判断
            monitor = monitors.get(url);
            if(monitor != null){
                return monitor;
            }
            //调用业务方法
            monitors.put(url,new Object());
        }finally {
            lock.unlock();
        }
        return monitors.get(url);
    }
}
