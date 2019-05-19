package com.channelsoft.dubbo.monitorservice.provider.serviceimpl;

import com.channelsoft.dubbo.monitorservice.DemoService;

public class DemoServiceImpl implements DemoService {
    @Override
    public String sayHello(String name) {
        return "Hello" + name;
    }
}
