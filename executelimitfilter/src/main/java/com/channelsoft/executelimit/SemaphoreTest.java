package com.channelsoft.executelimit;

import java.util.concurrent.Semaphore;

/**
 * @author sicwen
 * @date 2019/05/14
 */
public class SemaphoreTest {
    public static void main(String[] args){
        Semaphore semaphore = new Semaphore(3);
        semaphore.tryAcquire();
        semaphore.tryAcquire();
        semaphore.tryAcquire();
        semaphore.tryAcquire();
        System.out.println(semaphore.availablePermits());
        semaphore.release();
        semaphore.release();
        semaphore.release();
        //可以通过多次release来增加许可数
        semaphore.release();
        System.out.println(semaphore.availablePermits());
    }
}