package com.channelsoft.parsedefinition.spring;

import org.springframework.beans.factory.xml.NamespaceHandlerSupport;

public class CustomNamespaceHandler extends NamespaceHandlerSupport {
    @Override
    public void init() {
        registerBeanDefinitionParser("umg", new CustomBeanDefinitionParser());
    }
}
