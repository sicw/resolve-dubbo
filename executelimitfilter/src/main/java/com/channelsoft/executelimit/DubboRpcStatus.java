package com.channelsoft.executelimit;

import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;
import java.util.concurrent.Semaphore;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @author sicwen
 * @date 2019/05/14
 */
public class DubboRpcStatus {
    private static ConcurrentMap<String,DubboRpcStatus> serviceStatistics = new ConcurrentHashMap<>();

    private static Semaphore semaphore = null;

    private AtomicInteger actives = new AtomicInteger();

    public static DubboRpcStatus getRpcStatus(String key){
        DubboRpcStatus status = serviceStatistics.get(key);
        if (status == null) {
            serviceStatistics.putIfAbsent(key, new DubboRpcStatus());
            status = serviceStatistics.get(key);
        }
        return status;
    }

    public int getActives(){
        return actives.get();
    }

    /**
     * 使用信号量而不使用锁做并发流量控制，这样可以提供程序运行效率
     * @param permits
     * @return
     */
    public Semaphore getSemaphore(int permits){
        if (semaphore == null) {
            synchronized (this) {
                if (semaphore == null) {
                    semaphore = new Semaphore(permits);
                }
            }
        }
        return semaphore;
    }

    public static void beginCount(String url){
        DubboRpcStatus status = getRpcStatus(url);
        status.actives.incrementAndGet();
    }

    public static void endCount(String url){
        DubboRpcStatus status = getRpcStatus(url);
        status.actives.decrementAndGet();
    }
}