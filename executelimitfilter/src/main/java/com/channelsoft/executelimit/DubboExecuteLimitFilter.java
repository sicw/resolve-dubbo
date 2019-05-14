package com.channelsoft.executelimit;

import java.util.concurrent.Semaphore;

/**
 * 针对url进行线程数限制
 * @author sicwen
 * @date 2019/05/14
 */
public class DubboExecuteLimitFilter {

    public String invoke(String url) throws Exception {
        int max = 3;
        Semaphore semaphore = null;
        // max < 0时 如何处理
        boolean acquireResult = false;
        if (max > 0) {
            DubboRpcStatus status = DubboRpcStatus.getRpcStatus(url);
            semaphore = status.getSemaphore(max);
            if ((acquireResult = semaphore.tryAcquire()) == false) {
                throw new RuntimeException("actives >= max");
            }
        }

        //测试延时
        Thread.sleep(500);

        // 计数器+1
        DubboRpcStatus.beginCount(url);
        try {
            return url;
        } catch (Throwable t) {
            t.printStackTrace();
        } finally {
            // 在finally中进行计数器-1
            DubboRpcStatus.endCount(url);
            if (acquireResult) {
                semaphore.release();
            }
        }
        return null;
    }
}