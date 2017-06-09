package org.flying.bird.spring;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import org.flying.bird.spring.api.Reference;
import org.flying.bird.spring.api.Reg;
import org.flying.bird.spring.api.Service;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.util.StringUtils;

/**
 * @author nieyanshun
 *
 */
final class SpringContext extends Context {

    private static Map<String, Object> OBJS = new ConcurrentHashMap<>();

    private static final String SERVICE_CLASS = Service.class.getName();

    private static final String REFERENCE_CLASS = Reference.class.getName();

    private static final String REG_CLASS = Reg.class.getName();

    @Override
    public void setApplicationContext(ApplicationContext context) throws BeansException {
        applicationContext = context;
    }

    @Override
    public Service getService() {
        Service service = null;

        if (!OBJS.containsKey(SERVICE_CLASS)) {
            service = applicationContext.getBean(Service.class);
            OBJS.putIfAbsent(SERVICE_CLASS, service);
        } else {
            service = (Service) OBJS.get(SERVICE_CLASS);
        }
        return service;
    }

    @Override
    public Reg getReg() {
        Reg reg = null;
        if (!OBJS.containsKey(REG_CLASS)) {
            reg = applicationContext.getBean(Reg.class);
            OBJS.put(REG_CLASS, reg);
        } else {
            reg = (Reg) OBJS.get(REG_CLASS);
        }
        return reg;
    }

    @Override
    public Reference getReference() {
        Reference reference = null;
        if (!OBJS.containsKey(REFERENCE_CLASS)) {
            reference = applicationContext.getBean(Reference.class);
            OBJS.putIfAbsent(REFERENCE_CLASS, reference);
        } else {
            reference = (Reference) OBJS.get(REFERENCE_CLASS);
        }
        return reference;
    }

    @Override
    Object getBean(String beanName) {
        if (!StringUtils.hasLength(beanName))
            return null;
        return (applicationContext.getBean(beanName));
    }
}
