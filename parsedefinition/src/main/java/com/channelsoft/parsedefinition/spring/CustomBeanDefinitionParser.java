package com.channelsoft.parsedefinition.spring;

import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.beans.factory.config.RuntimeBeanReference;
import org.springframework.beans.factory.support.RootBeanDefinition;
import org.springframework.beans.factory.xml.BeanDefinitionParser;
import org.springframework.beans.factory.xml.ParserContext;
import org.springframework.util.StringUtils;
import org.w3c.dom.Element;

public class CustomBeanDefinitionParser implements BeanDefinitionParser {
    @Override
    public BeanDefinition parse(Element element, ParserContext parserContext) {
        RootBeanDefinition multPropertiesBeanDefinition = new RootBeanDefinition();
        String className = element.getAttribute("class");
        if (!StringUtils.isEmpty(className)) {
            //SimpleBean
            RootBeanDefinition simpleBeanDefinition = new RootBeanDefinition();
            simpleBeanDefinition.setBeanClassName("com.channelsoft.parsedefinition.app.SimpleBean");
            simpleBeanDefinition.setLazyInit(false);
            simpleBeanDefinition.getPropertyValues().add("sex","nan");
            simpleBeanDefinition.getPropertyValues().add("school","heilongjiangdaxue");
            parserContext.getRegistry().registerBeanDefinition("simpleBean",simpleBeanDefinition);
            //MutlPropertiesBean
            multPropertiesBeanDefinition.setBeanClassName(className);
            multPropertiesBeanDefinition.setLazyInit(false);
            multPropertiesBeanDefinition.getPropertyValues().add("name", "sicwen");
            multPropertiesBeanDefinition.getPropertyValues().add("age", "25");
            RuntimeBeanReference runtimeBeanReference = new RuntimeBeanReference("simpleBean");
            multPropertiesBeanDefinition.getPropertyValues().add("simpleBean",runtimeBeanReference);
            parserContext.getRegistry().registerBeanDefinition("multPropertiesBean",multPropertiesBeanDefinition);
        }
        return multPropertiesBeanDefinition;
    }
}
