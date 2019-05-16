package com.channelsoft.jdkspi;

public class DemoTwoServiceImpl implements DemoService {
    @Override
    public String say(String name) {
        return "Hi " + name;
    }
}
