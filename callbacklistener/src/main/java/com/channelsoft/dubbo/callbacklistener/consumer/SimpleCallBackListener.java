package com.channelsoft.dubbo.callbacklistener.consumer;

import com.channelsoft.dubbo.callbacklistener.CallbackListener;

public class SimpleCallBackListener implements CallbackListener {

    @Override
    public void changed(String msg) {
        System.out.println("consumer listener recv msg: " + msg);
    }
}
