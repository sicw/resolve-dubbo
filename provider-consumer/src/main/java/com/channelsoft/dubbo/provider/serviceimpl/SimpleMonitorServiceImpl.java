package com.channelsoft.dubbo.provider.serviceimpl;

import com.alibaba.dubbo.common.URL;
import com.alibaba.dubbo.monitor.MonitorService;

import java.util.List;

/**
 * @author sicwen
 * @date 2019/05/10
 */
public class SimpleMonitorServiceImpl implements MonitorService {
    @Override
    public void collect(URL statistics) {
        System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~" +statistics.toFullString());
    }

    @Override
    public List<URL> lookup(URL query) {
        return null;
    }
}