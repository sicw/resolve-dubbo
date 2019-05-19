package com.channelsoft.dubbo.monitorservice.consumer;

import com.channelsoft.dubbo.monitorservice.DemoService;
import org.junit.Test;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class Consumer {
    @Test
    public void testConsumer() throws InterruptedException {
        //TODO: consumer在使用直连方式时，不会去加载monitor，可能需要修复该bug
        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext(new String[] {"consumer.xml"});
        context.start();
        DemoService demoService = (DemoService)context.getBean("demoServiceNoRegistry");
        for (int i = 0; i < 5; i++) {
            String hello = demoService.sayHello("world");
            System.out.println(hello);
            Thread.sleep(2000);
        }
    }
}