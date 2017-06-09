package org.flying.bird.spring.service.handler;

import org.flying.bird.spring.service.ServiceBeanDefinitionParser;
import org.springframework.beans.factory.xml.NamespaceHandlerSupport;

public class ServiceNamespaceHandler extends NamespaceHandlerSupport {

    @Override
    public void init() {
        registerBeanDefinitionParser("service", new ServiceBeanDefinitionParser());
    }

}
