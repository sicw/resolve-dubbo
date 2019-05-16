package com.channelsoft.jdkspi;

public class DemoOneServiceImpl implements DemoService {
    @Override
    public String say(String name) {
        return "Hello " + name;
    }
}
