package com.channelsoft.dubbo.provider.serviceimpl;

import com.channelsoft.dubbo.DemoService;

public class DemoServiceImpl implements DemoService {
    @Override
    public String sayHello(String name) {
        return "Hello" + name;
    }
}
