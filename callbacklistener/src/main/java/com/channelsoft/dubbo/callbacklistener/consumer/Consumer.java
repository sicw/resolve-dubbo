package com.channelsoft.dubbo.callbacklistener.consumer;

import com.channelsoft.dubbo.callbacklistener.CallbackService;
import org.junit.Test;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.io.IOException;

/**
 * consumer调用一个接口，并传递参数给provider
 * provider接收到该参数
 * provider返回来调用consumer的这个参数
 * 最终这个参数的执行是在consumer端
 */
public class Consumer {
    @Test
    public void testListener() throws InterruptedException, IOException {
        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext(new String[] {"consumer.xml"});
        context.start();
        CallbackService callbackService = (CallbackService)context.getBean("callbackService");
        callbackService.addListener("test", new SimpleCallBackListener());
        System.in.read();
    }
}