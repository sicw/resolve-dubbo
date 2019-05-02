package com.channelsoft;

import org.springframework.context.support.ClassPathXmlApplicationContext;

public class App {
    public static void main(String[] args){
        ClassPathXmlApplicationContext ctx = new ClassPathXmlApplicationContext("spring.xml");
        ServiceBean serviceBean = (ServiceBean) ctx.getBean("serviceBean");
    }
}