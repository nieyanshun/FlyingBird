package org.flying.bird.spring;

import java.util.Map;
import java.util.Objects;
import java.util.concurrent.ConcurrentHashMap;
import org.flying.bird.spring.api.Service;
import org.springframework.context.ApplicationContext;
import org.springframework.util.StringUtils;

/**
 * @author nieyanshun
 *
 */
final public class SpringContext extends Context {

    private Map<String, Object> objs = new ConcurrentHashMap<>();

    SpringContext() {}

    SpringContext(ApplicationContext context) {
        this.applicationContext = context;
    }

    @Override
    public Object getBean(String beanName) {
        if (!StringUtils.hasLength(beanName))
            return null;
        return (applicationContext.getBean(beanName));
    }

    @Override
    public Object getService(String className) {
        Objects.requireNonNull(className);
        String serviceRef = Service.INTERFACE_2_SERVICE_REF.get(className);
        Objects.requireNonNull(serviceRef);
        Object service = objs.get(serviceRef);
        if (null != service)
            return service;

        service = getBean(serviceRef);

        if (null == service) {
            throw new IllegalArgumentException("Service bean not found for beanId " + serviceRef);
        }

        return service;
    }
}
