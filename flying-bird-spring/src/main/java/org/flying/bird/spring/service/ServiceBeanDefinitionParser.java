package org.flying.bird.spring.service;

import java.util.HashSet;
import java.util.Set;
import org.flying.bird.common.exception.RpcInitException;
import org.flying.bird.spring.ContextFactory;
import org.flying.bird.spring.api.Service;
import org.flying.bird.spring.constant.Constant;
import org.springframework.beans.factory.support.AbstractBeanDefinition;
import org.springframework.beans.factory.support.BeanDefinitionBuilder;
import org.springframework.beans.factory.xml.AbstractBeanDefinitionParser;
import org.springframework.beans.factory.xml.ParserContext;
import org.w3c.dom.Element;

public class ServiceBeanDefinitionParser extends AbstractBeanDefinitionParser {

    private static Set<String> SERVICE_CACAHE = new HashSet<>();

    @Override
    protected AbstractBeanDefinition parseInternal(Element element, ParserContext parserContext) {
        BeanDefinitionBuilder result = BeanDefinitionBuilder.rootBeanDefinition(Service.class);
        final String interfaceName = element.getAttribute(Constant.SERVICE_ATTR_INTERFACE);
        result.addPropertyValue("interfaceName", interfaceName);
        final String ref = element.getAttribute(Constant.SERVICE_ATTR_REF);
        result.addPropertyValue("ref", ref);
        result.addPropertyValue("registry", element.getAttribute(Constant.SERVICE_ATTR_REGISTRY));
        final String version = element.getAttribute(Constant.SERVICE_ATTR_VERSION);
        result.addPropertyValue("version", version);
        final String key = interfaceName.concat(":").concat(version);
        if (!SERVICE_CACAHE.contains(key))
            synchronized (SERVICE_CACAHE) {
                if (SERVICE_CACAHE.contains(key))
                    throw new RpcInitException(
                            "Service " + interfaceName + " version: " + version + " conflicts .");
                SERVICE_CACAHE.add(key);
            }
        Service.INTERFACE_2_SERVICE_REF.put(interfaceName, ref);
        return result.getBeanDefinition();
    }
}
