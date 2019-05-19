package com.channelsoft.dubbo.callbacklistener.provider;

import org.junit.Test;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class Provider {
    @Test
    public void testProvider() throws Exception {
        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext(new String[] {"classpath:provider.xml"});
        context.start();
        System.in.read(); // 按任意键退出
    }
}