package com.channelsoft.event;

import org.springframework.context.ApplicationEvent;

public class SecondEvent extends ApplicationEvent {
    public SecondEvent(Object source) {
        super(source);
    }

    @Override
    public Object getSource() {
        return super.getSource();
    }
}
