package org.flying.bird.spring;

import org.springframework.context.ApplicationContext;

public abstract class Context {

    ApplicationContext applicationContext;

    public abstract Object getBean(String beanName);

    public abstract Object getService(String serviceRef);

    void setContext(ApplicationContext applicationContext) {
        this.applicationContext = applicationContext;
    }

}
