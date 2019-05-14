package com.channelsoft.executelimit;

import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @author sicwen
 * @date 2019/05/14
 */
public class DubboRpcStatus {
    private static ConcurrentMap<String,DubboRpcStatus> serviceStatistics = new ConcurrentHashMap<>();

    private AtomicInteger actives = new AtomicInteger();

    private static DubboRpcStatus getRpcStatus(String key){
        DubboRpcStatus status = serviceStatistics.get(key);
        if (status == null) {
            serviceStatistics.putIfAbsent(key, new DubboRpcStatus());
            status = serviceStatistics.get(key);
        }
        return status;
    }

    public static Integer getActives(String key){
        DubboRpcStatus status = getRpcStatus(key);
        return status.actives.get();
    }

    public static void beginCount(String key){
        DubboRpcStatus status = getRpcStatus(key);
        status.actives.incrementAndGet();
    }

    public static void endCount(String key){
        DubboRpcStatus status = getRpcStatus(key);
        status.actives.decrementAndGet();
    }
}