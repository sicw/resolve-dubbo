package com.channelsoft.dubbo.monitorservice.consumer;

import com.channelsoft.dubbo.monitorservice.DemoService;

public class DemoServiceStud implements DemoService {

    private DemoService demoService;


    public DemoServiceStud(DemoService demoService){
        this.demoService = demoService;
    }

    @Override
    public String sayHello(String name) {
        System.out.println("This is monitorservice and invoke real demoService");
        return demoService.sayHello(name);
    }
}
