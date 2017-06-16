package org.flying.bird.spring.reference;

import java.util.HashSet;
import java.util.Set;

import org.flying.bird.common.exception.RpcInitException;
import org.flying.bird.spring.ContextFactory;
import org.flying.bird.spring.api.Reference;
import org.flying.bird.spring.constant.Constant;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.support.AbstractBeanDefinition;
import org.springframework.beans.factory.support.BeanDefinitionBuilder;
import org.springframework.beans.factory.xml.AbstractBeanDefinitionParser;
import org.springframework.beans.factory.xml.ParserContext;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.w3c.dom.Element;

public class ReferenceBeanDefinitionParser extends AbstractBeanDefinitionParser
        implements ApplicationContextAware {

    private static Set<String> REFERENCE_CACAHE = new HashSet<>();

    @Override
    protected AbstractBeanDefinition parseInternal(Element element, ParserContext parserContext) {
        BeanDefinitionBuilder result = BeanDefinitionBuilder.rootBeanDefinition(Reference.class);
        final String clazz = element.getAttribute(Constant.REFERENCE_ATTR_CLAZZ);
        result.addPropertyValue("clazz", clazz);
        result.addPropertyValue("cluster", element.getAttribute(Constant.REFERENCE_ATTR_CLUSTER));
        result.addPropertyValue("loadBlance",
                element.getAttribute(Constant.REFERENCE_ATTR_LOADBLANCE));
        result.addPropertyValue("registry", element.getAttribute(Constant.REFERENCE_ATTR_REGISTRY));
        result.addPropertyValue("retries", element.getAttribute(Constant.REFERENCE_ATTR_RETRIES));
        result.addPropertyValue("timeout", element.getAttribute(Constant.REFERENCE_ATTR_TIMEOUT));
        result.addPropertyValue("url", element.getAttribute(Constant.REFERENCE_ATTR_URL));
        final String version = element.getAttribute(Constant.REFERENCE_ATTR_VERSION);
        result.addPropertyValue("version", version);
        result.addPropertyValue("proxy", element.getAttribute(Constant.REFERENCE_ATTR_PROXY));
        final String key = clazz.concat(":").concat(version);
        if (!REFERENCE_CACAHE.contains(key))
            synchronized (REFERENCE_CACAHE) {
                if (REFERENCE_CACAHE.contains(key))
                    throw new RpcInitException(
                            "Reference " + clazz + " version: " + version + " conflicts .");
                REFERENCE_CACAHE.add(key);
            }
        return result.getBeanDefinition();
    }

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        ContextFactory.regiest(applicationContext);
    }

}
