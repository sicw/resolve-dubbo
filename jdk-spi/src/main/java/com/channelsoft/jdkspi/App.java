package com.channelsoft.jdkspi;

import java.util.ServiceLoader;

public class App {
    public static void main(String[] args){
        ServiceLoader<DemoService> serviceServiceLoader = ServiceLoader.load(DemoService.class);
        for (DemoService demoService : serviceServiceLoader) {
            System.out.println(demoService.say("sicwen"));
        }
    }
}
