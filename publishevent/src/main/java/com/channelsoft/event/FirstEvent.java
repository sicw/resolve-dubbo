package com.channelsoft.event;

import org.springframework.context.ApplicationEvent;

public class FirstEvent extends ApplicationEvent {
    public FirstEvent(Object source) {
        super(source);
    }

    public Object getEvent(){
        return super.getSource();
    }
}