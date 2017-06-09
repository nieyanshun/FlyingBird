package org.flying.bird.spring.reference.handler;

import org.flying.bird.spring.reference.ReferenceBeanDefinitionParser;
import org.springframework.beans.factory.xml.NamespaceHandlerSupport;

public class ReferenceNamespaceHandler extends NamespaceHandlerSupport {

    @Override
    public void init() {
        registerBeanDefinitionParser("reference", new ReferenceBeanDefinitionParser());
    }

}
