package org.flying.bird.spring.reg;

import org.flying.bird.spring.api.Reg;
import org.flying.bird.spring.constant.Constant;
import org.springframework.beans.factory.support.AbstractBeanDefinition;
import org.springframework.beans.factory.support.BeanDefinitionBuilder;
import org.springframework.beans.factory.xml.AbstractBeanDefinitionParser;
import org.springframework.beans.factory.xml.ParserContext;
import org.w3c.dom.Element;

public class RegBeanDefinitionParser extends AbstractBeanDefinitionParser {

    @Override
    protected AbstractBeanDefinition parseInternal(Element element, ParserContext parserContext) {
        BeanDefinitionBuilder result = BeanDefinitionBuilder.rootBeanDefinition(Reg.class);

        result.addPropertyValue("url", element.getAttribute(Constant.REG_ATTR_URL));
        return result.getBeanDefinition();

    }

    // @Override
    // public BeanDefinition parse(Element element, ParserContext parserContext) {
    // RootBeanDefinition def = new RootBeanDefinition();
    // def.setScope("singleton");
    // // 设置Bean Class
    // def.setBeanClass(org.flying.bird.spring.api.Reg.class);
    // // 注册ID属性
    // final String id = element.getAttribute("id");
    // BeanDefinitionHolder idHolder = new BeanDefinitionHolder(def, id);
    // BeanDefinitionReaderUtils.registerBeanDefinition(idHolder, parserContext.getRegistry());
    // final String url = element.getAttribute(Constant.REG_ATTR_URL);
    // BeanDefinitionHolder urlHolder = new BeanDefinitionHolder(def, url);
    // BeanDefinitionReaderUtils.registerBeanDefinition(urlHolder, parserContext.getRegistry());
    // // 注册属性
    // def.getPropertyValues().addPropertyValue("url", url);
    //
    // return def;
    // }

}
