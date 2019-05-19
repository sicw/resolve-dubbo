package com.channelsoft.dubbo.callbacklistener.provider.serviceimpl;


import com.channelsoft.dubbo.callbacklistener.DemoService;

public class DemoServiceImpl implements DemoService {
    @Override
    public String sayHello(String name) {
        return "Hello" + name;
    }
}
