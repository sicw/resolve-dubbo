package com.channelsoft.springfactorybean;

import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * 如果一个bean想使用getbject来获取对象，并且在preinstall时就获取到getObject的值
 * 那么该bean需要实现SmartFactoryBean，而不是FactoryBean(调用getBean时才会获取到真实的对象)
 * 这样在ApplicationContext初始化时，就已经获取到了真正的Bean
 */
public class App {
    public static void main(String[] args){
        ClassPathXmlApplicationContext ctx = new ClassPathXmlApplicationContext("classpath:spring.xml");
        SimpleBean simpleBean = (SimpleBean) ctx.getBean("demoService");
        simpleBean = (SimpleBean) ctx.getBean("demoService");
        //System.out.println(simpleBean);
    }
}
