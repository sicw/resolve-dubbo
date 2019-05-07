package com.channelsoft.springfactorybean;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.SmartFactoryBean;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;

public class DemoService implements SmartFactoryBean, InitializingBean, ApplicationContextAware {

    private ApplicationContext ctx;

    private SimpleBean target;

    @Override
    public Object getObject() throws Exception {
        System.out.println("DemoService.getObject");
        return new SimpleBean();
    }

    @Override
    public Class<?> getObjectType() {
        return SimpleBean.class;
    }

    @Override
    public boolean isSingleton() {
        return true;
    }

    @Override
    public void afterPropertiesSet() throws Exception {
        System.out.println("DemoService.afterPropertiesSet");
    }

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        this.ctx = applicationContext;
    }

    @Override
    public boolean isPrototype() {
        return false;
    }

    @Override
    public boolean isEagerInit() {
        return true;
    }
}
