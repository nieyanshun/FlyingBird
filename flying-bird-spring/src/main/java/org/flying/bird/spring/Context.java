package org.flying.bird.spring;

import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;

public abstract class Context implements ApplicationContextAware {

    ApplicationContext applicationContext;

    abstract org.flying.bird.spring.api.Service getService();

    abstract org.flying.bird.spring.api.Reg getReg();

    abstract org.flying.bird.spring.api.Reference getReference();

    abstract Object getBean(String beanName);

}
