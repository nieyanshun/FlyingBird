package org.flying.bird.spring.reference;

import org.flying.bird.spring.api.Reference;
import org.flying.bird.spring.constant.Constant;
import org.springframework.beans.factory.support.AbstractBeanDefinition;
import org.springframework.beans.factory.support.BeanDefinitionBuilder;
import org.springframework.beans.factory.xml.AbstractBeanDefinitionParser;
import org.springframework.beans.factory.xml.ParserContext;
import org.w3c.dom.Element;

public class ReferenceBeanDefinitionParser extends AbstractBeanDefinitionParser {

    @Override
    protected AbstractBeanDefinition parseInternal(Element element, ParserContext parserContext) {
        BeanDefinitionBuilder result = BeanDefinitionBuilder.rootBeanDefinition(Reference.class);
        result.addPropertyValue("clazz", element.getAttribute(Constant.REFERENCE_ATTR_CLAZZ));
        result.addPropertyValue("cluster", element.getAttribute(Constant.REFERENCE_ATTR_CLUSTER));
        result.addPropertyValue("loadBlance",
                element.getAttribute(Constant.REFERENCE_ATTR_LOADBLANCE));
        result.addPropertyValue("registry", element.getAttribute(Constant.REFERENCE_ATTR_REGISTRY));
        result.addPropertyValue("retries", element.getAttribute(Constant.REFERENCE_ATTR_RETRIES));
        result.addPropertyValue("timeout", element.getAttribute(Constant.REFERENCE_ATTR_TIMEOUT));
        result.addPropertyValue("url", element.getAttribute(Constant.REFERENCE_ATTR_URL));
        return result.getBeanDefinition();
    }


    // @Override
    // public BeanDefinition parse(Element element, ParserContext parserContext) {
    // RootBeanDefinition def = new RootBeanDefinition();
    // // 设置Bean Class
    // def.setBeanClass(Reference.class);
    // def.setScope("singleton");
    // // 注册ID属性
    // final String id = element.getAttribute("id");
    // BeanDefinitionHolder idHolder = new BeanDefinitionHolder(def, id);
    // BeanDefinitionReaderUtils.registerBeanDefinition(idHolder, parserContext.getRegistry());
    //
    // final String clazz = element.getAttribute(Constant.REFERENCE_ATTR_CLAZZ);
    // BeanDefinitionHolder clazzHolder = new BeanDefinitionHolder(def, clazz);
    // BeanDefinitionReaderUtils.registerBeanDefinition(clazzHolder, parserContext.getRegistry());
    // // 注册属性
    // final String retries = element.getAttribute(Constant.REFERENCE_ATTR_RETRIES);
    // final String loadBlance = element.getAttribute(Constant.REFERENCE_ATTR_LOADBLANCE);
    // final String timeOut = element.getAttribute(Constant.REFERENCE_ATTR_TIMEOUT);
    // final String registry = element.getAttribute(Constant.REFERENCE_ATTR_REGISTRY);
    // final String cluster = element.getAttribute(Constant.REFERENCE_ATTR_CLUSTER);
    // final String url = element.getAttribute(Constant.REFERENCE_ATTR_URL);
    //
    // BeanDefinitionHolder retriesHolder = new BeanDefinitionHolder(def, retries);
    // BeanDefinitionHolder loadBlanceHolder = new BeanDefinitionHolder(def, loadBlance);
    // BeanDefinitionHolder timeOutHolder = new BeanDefinitionHolder(def, timeOut);
    // BeanDefinitionHolder registryHolder = new BeanDefinitionHolder(def, registry);
    // BeanDefinitionHolder clusterHolder = new BeanDefinitionHolder(def, cluster);
    // BeanDefinitionHolder urlHolder = new BeanDefinitionHolder(def, url);
    //
    // BeanDefinitionReaderUtils.registerBeanDefinition(retriesHolder,
    // parserContext.getRegistry());
    // BeanDefinitionReaderUtils.registerBeanDefinition(loadBlanceHolder,
    // parserContext.getRegistry());
    // BeanDefinitionReaderUtils.registerBeanDefinition(timeOutHolder,
    // parserContext.getRegistry());
    // BeanDefinitionReaderUtils.registerBeanDefinition(registryHolder,
    // parserContext.getRegistry());
    // BeanDefinitionReaderUtils.registerBeanDefinition(clusterHolder,
    // parserContext.getRegistry());
    // BeanDefinitionReaderUtils.registerBeanDefinition(urlHolder, parserContext.getRegistry());
    //
    // def.getPropertyValues().addPropertyValue("clazz", clazz);
    // def.getPropertyValues().addPropertyValue("retries", Integer.valueOf(retries));
    // def.getPropertyValues().addPropertyValue("loadBlance", loadBlance);
    // def.getPropertyValues().addPropertyValue("timeOut", Integer.valueOf(timeOut));
    // def.getPropertyValues().addPropertyValue("registry", registry);
    // def.getPropertyValues().addPropertyValue("cluster", cluster);
    // def.getPropertyValues().addPropertyValue("url", url);
    //
    // return def;
    // }
}
