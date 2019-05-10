package com.channelsoft.dubbo.consumer;

import com.channelsoft.dubbo.DemoService;
import org.junit.Test;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class Consumer {
    @Test
    public void testConsumer(){
        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext(new String[] {"consumer.xml"});
        context.start();
        DemoService demoService = (DemoService)context.getBean("demoService"); // 获取远程服务代理
        String hello = demoService.sayHello("world"); // 执行远程方法
        System.out.println( hello ); // 显示调用结果
    }
}