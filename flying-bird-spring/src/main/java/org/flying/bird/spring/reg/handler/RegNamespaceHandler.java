package org.flying.bird.spring.reg.handler;

import org.flying.bird.spring.reg.RegBeanDefinitionParser;
import org.springframework.beans.factory.xml.NamespaceHandlerSupport;

public class RegNamespaceHandler extends NamespaceHandlerSupport {

    @Override
    public void init() {
        registerBeanDefinitionParser("reg", new RegBeanDefinitionParser());
    }

}
