package com.channelsoft.dubbo.monitorservice.provider.serviceimpl;

import com.channelsoft.dubbo.monitorservice.DemoService;

public class DemoServiceImplStub implements DemoService {

    private DemoService demoService;

    public DemoServiceImplStub(DemoService demoService){
        this.demoService = demoService;
    }

    @Override
    public String sayHello(String name) {
        System.out.println("this is provider side demoServiceImpl monitorservice");
        return demoService.sayHello(name);
    }
}
