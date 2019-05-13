package com.channelsoft.multithread;

import java.util.Map;
import java.util.concurrent.*;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class AsyncGetMonitor {

    /**
     * 这里coreSize设置成0，也就是说使用的都是maxSize
     * 线程在keepAliveTime时间内没有任务执行就会自动退出，从而节省系统资源了
     */
    private ThreadPoolExecutor executor = new ThreadPoolExecutor(0, 10, 60L, TimeUnit.SECONDS, new ArrayBlockingQueue<>(100), new ThreadFactory() {
        @Override
        public Thread newThread(Runnable r) {
            Thread t = new Thread(r);
            t.setDaemon(true);
            t.setName("GetMonitorThread");
            return t;
        }
    });

    private Map<String,Object> monitors = new ConcurrentHashMap<String,Object>();

    private Map<String,Future> futures = new ConcurrentHashMap<String,Future>();

    private Lock lock = new ReentrantLock();



    public static void main(String[] args) throws InterruptedException {
        AsyncGetMonitor app = new AsyncGetMonitor();

        new Thread(new Runnable() {
            @Override
            public void run() {
                Object monitor = app.getMonitor("test");
                System.out.println(monitor);
            }
        }).start();
        Thread.sleep(1000);
        Object monitor = app.getMonitor("test");
        System.out.println(monitor);
        Thread.sleep(1000);
    }

    /**
     * 会有多个线程调用该方法
     * 但相同的url要确保返回相同的monitor
     * 而且不想耽误业务逻辑的运行，所以在创建新对象的过程在新的线程中，
     * 但是这种方式在get时最开始的几个会为null
     * @param url
     * @return
     */
    public Object getMonitor(String url){
        Object monitor = monitors.get(url);
        Future future = futures.get(url);
        if(monitor != null || future != null){
            return monitor;
        }
        try{
            //会有多个线程阻塞在这里
            lock.lock();
            //所以要加上双重判断
            monitor = monitors.get(url);
            future = futures.get(url);
            if(monitor != null || future != null){
                return monitor;
            }

            /*
            * 使用该方法不可以，因为在多线程情况下，有可能run方法还没执行，另一个线程已经执行完上面的判断了
            * 会导致执行多次run方法
            * */
            /*executor.execute(new Runnable() {
                @Override
                public void run() {
                    monitors.put(url,new Object());
                }
            });*/

            /*
            * 使用该方法，我们可以缓存url和future的映射关系，在上面的双重判断中
            * 通过url获取future，如果获得成功那么就说明已经有线程去创建该monitor了，就不要在调用了
            * */
            /*Future newFuture = executor.submit(new Callable<Object>() {
                @Override
                public Object call() throws Exception {
                    //调用业务方法
                    monitors.put(url,new Object());
                    //删除临时存储
                    futures.remove(url);
                    return monitors.get(url);
                }
            });
            futures.put(url,newFuture);*/

            FutureTask<Object> task = new FutureTask<Object>(()->{
                //调用业务方法
                monitors.put(url,new Object());
                //删除临时存储
                futures.remove(url);
                return monitors.get(url);
            });
            futures.put(url,task);
            executor.execute(task);
        }finally {
            lock.unlock();
        }
        return monitors.get(url);
    }
}