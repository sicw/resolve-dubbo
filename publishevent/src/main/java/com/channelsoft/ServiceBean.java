package com.channelsoft;

import com.channelsoft.event.FirstEvent;
import com.channelsoft.event.SecondEvent;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.context.ApplicationEventPublisherAware;
import org.springframework.stereotype.Component;

@Component("serviceBean")
public class ServiceBean implements ApplicationEventPublisherAware, InitializingBean {

    private ApplicationEventPublisher applicationEventPublisher;

    @Override
    public void setApplicationEventPublisher(ApplicationEventPublisher applicationEventPublisher) {
        this.applicationEventPublisher = applicationEventPublisher;
    }

    @Override
    public void afterPropertiesSet() throws Exception {
        applicationEventPublisher.publishEvent(new FirstEvent("Hi~ FirstEvent"));
        applicationEventPublisher.publishEvent(new SecondEvent("Hello~ SecondEvent"));
    }
}