package com.channelsoft.dubbo.consumer;

import com.channelsoft.dubbo.DemoService;
import org.junit.Test;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.io.IOException;

public class Consumer {
    @Test
    public void testConsumer() throws InterruptedException {
        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext(new String[] {"consumer.xml"});
        context.start();
        DemoService demoService = (DemoService)context.getBean("demoService");
        for (int i = 0; i < 1; i++) {
            String hello = demoService.sayHello("world");
            System.out.println( hello );
            Thread.sleep(2000);
        }
        Thread.sleep(300000);
    }
}