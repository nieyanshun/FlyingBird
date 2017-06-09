package org.flying.bird.spring.service;

import org.flying.bird.spring.api.Service;
import org.flying.bird.spring.constant.Constant;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.beans.factory.config.BeanDefinitionHolder;
import org.springframework.beans.factory.support.AbstractBeanDefinition;
import org.springframework.beans.factory.support.BeanDefinitionBuilder;
import org.springframework.beans.factory.support.BeanDefinitionReaderUtils;
import org.springframework.beans.factory.support.RootBeanDefinition;
import org.springframework.beans.factory.xml.AbstractBeanDefinitionParser;
import org.springframework.beans.factory.xml.BeanDefinitionParser;
import org.springframework.beans.factory.xml.ParserContext;
import org.w3c.dom.Element;

public class ServiceBeanDefinitionParser extends AbstractBeanDefinitionParser {

    @Override
    protected AbstractBeanDefinition parseInternal(Element element, ParserContext parserContext) {
        BeanDefinitionBuilder result =
                BeanDefinitionBuilder.rootBeanDefinition(Service.class);
        result.addPropertyValue("interfaceName",element.getAttribute(Constant.SERVICE_ATTR_INTERFACE));
        result.addPropertyValue("ref",element.getAttribute(Constant.SERVICE_ATTR_REF));
        result.addPropertyValue("registry",element.getAttribute(Constant.SERVICE_ATTR_REGISTRY));
        return result.getBeanDefinition();
    }

    // @Override
    // public BeanDefinition parse(Element element, ParserContext parserContext) {
    //
    // RootBeanDefinition def = new RootBeanDefinition();
    // // 设置Bean Class
    // def.setBeanClass(Service.class);
    // // 注册ID属性
    // final String id = element.getAttribute("id");
    // BeanDefinitionHolder idHolder = new BeanDefinitionHolder(def, id);
    // BeanDefinitionReaderUtils.registerBeanDefinition(idHolder, parserContext.getRegistry());
    //
    // final String interfaceName = element.getAttribute(Constant.SERVICE_ATTR_INTERFACE);
    // BeanDefinitionHolder interfaceHolder = new BeanDefinitionHolder(def, interfaceName);
    // BeanDefinitionReaderUtils.registerBeanDefinition(interfaceHolder,
    // parserContext.getRegistry());
    // def.setScope("singleton");
    // // 注册属性
    // final String ref = element.getAttribute(Constant.SERVICE_ATTR_REF);
    // final String registry = element.getAttribute(Constant.SERVICE_ATTR_REGISTRY);
    //
    // BeanDefinitionHolder refHolder = new BeanDefinitionHolder(def, ref);
    // BeanDefinitionHolder registryHolder = new BeanDefinitionHolder(def, registry);
    //
    // BeanDefinitionReaderUtils.registerBeanDefinition(refHolder, parserContext.getRegistry());
    // BeanDefinitionReaderUtils.registerBeanDefinition(registryHolder,
    // parserContext.getRegistry());
    //
    // def.getPropertyValues().addPropertyValue("interfaceName", interfaceName);
    // def.getPropertyValues().addPropertyValue("ref", ref);
    // def.getPropertyValues().addPropertyValue("registry", registry);
    //
    // return def;
    //
    // }

}
