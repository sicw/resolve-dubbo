package com.channelsoft.dubbo.monitorservice.consumer;

import com.channelsoft.dubbo.monitorservice.DemoService;
import org.junit.Test;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class Consumer {
    @Test
    public void testStub() throws InterruptedException {
        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext(new String[] {"consumer.xml"});
        context.start();
        DemoService demoService = (DemoService)context.getBean("demoServiceNoRegistry");
        String hello = demoService.sayHello("world");
        System.out.println(hello);
        Thread.sleep(1000);
    }
}