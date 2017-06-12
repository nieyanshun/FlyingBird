package org.flying.bird.spring.reg;

import org.flying.bird.common.exception.RpcInitException;
import org.flying.bird.spring.api.Reg;
import org.flying.bird.spring.constant.Constant;
import org.springframework.beans.factory.support.AbstractBeanDefinition;
import org.springframework.beans.factory.support.BeanDefinitionBuilder;
import org.springframework.beans.factory.xml.AbstractBeanDefinitionParser;
import org.springframework.beans.factory.xml.ParserContext;
import org.w3c.dom.Element;

public class RegBeanDefinitionParser extends AbstractBeanDefinitionParser {
    private static volatile boolean IS_INIT = false;

    @Override
    protected AbstractBeanDefinition parseInternal(Element element, ParserContext parserContext) {
        if (IS_INIT)
            throw new RpcInitException("<reg:reg conflicts in xml .");
        BeanDefinitionBuilder result = BeanDefinitionBuilder.rootBeanDefinition(Reg.class);
        result.addPropertyValue("url", element.getAttribute(Constant.REG_ATTR_URL));
        return result.getBeanDefinition();
    }

}
