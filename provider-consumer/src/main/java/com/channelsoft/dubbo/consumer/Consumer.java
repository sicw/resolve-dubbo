package com.channelsoft.dubbo.consumer;

import com.channelsoft.dubbo.DemoService;
import org.junit.Test;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class Consumer {
    @Test
    public void testConsumer() throws InterruptedException {
        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext(new String[] {"consumer.xml"});
        context.start();
        DemoService demoService = (DemoService)context.getBean("demoService"); // 获取远程服务代理

        for (int i = 0; i < 2; i++) {
            String hello = demoService.sayHello("world"); // 执行远程方法
            System.out.println( hello ); // 显示调用结果
            Thread.sleep(2000);
        }

        Thread.sleep(10000);

    }
}