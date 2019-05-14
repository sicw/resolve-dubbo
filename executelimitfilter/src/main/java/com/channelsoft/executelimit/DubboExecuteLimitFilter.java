package com.channelsoft.executelimit;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @author sicwen
 * @date 2019/05/14
 */
public class DubboExecuteLimitFilter {

    private Lock lock = new ReentrantLock();

    public String invoke(String url) throws Exception {
        int max = 3;
        lock.lock();
        if (max > 0) {
            int actives = DubboRpcStatus.getActives(url);
            Thread.sleep(10);
            if (actives >= max) {
                lock.unlock();
                throw new RuntimeException("actives >= max");
            }
        }
        // 计数器+1
        DubboRpcStatus.beginCount(url);
        lock.unlock();
        try {
            Thread.sleep(500);
            return url;
        } catch (Throwable t) {
            t.printStackTrace();
        } finally {
            // 在finally中进行计数器-1
            DubboRpcStatus.endCount(url);
        }
        return null;
    }
}