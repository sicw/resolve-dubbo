package com.channelsoft.listener;

import com.channelsoft.event.SecondEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;

@Component
public class SecondEventListener implements ApplicationListener<SecondEvent> {
    @Override
    public void onApplicationEvent(SecondEvent secondEvent) {
        System.out.println("SecondEventListener get a event [" + secondEvent.getSource() + "]");
    }
}