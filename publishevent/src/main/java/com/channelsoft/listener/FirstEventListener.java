package com.channelsoft.listener;

import com.channelsoft.event.FirstEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;

@Component
public class FirstEventListener implements ApplicationListener<FirstEvent> {
    @Override
    public void onApplicationEvent(FirstEvent firstEvent) {
        System.out.println("FirstEventListener get a event [" + firstEvent.getSource() + "]");
    }
}
