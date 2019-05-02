package com.channelsoft.parsedefinition.app;

import org.springframework.context.support.ClassPathXmlApplicationContext;

public class App {
    public static void main(String[] args){
        ClassPathXmlApplicationContext ctx = new ClassPathXmlApplicationContext("classpath:spring.xml");
        MultPropertiesBean multPropertiesBean = (MultPropertiesBean) ctx.getBean("multPropertiesBean");
        System.out.println(multPropertiesBean);
    }
}