package org.flying.bird.spring.api;

import java.util.HashMap;
import java.util.Map;

import org.flying.bird.spring.ContextFactory;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;

public class Service implements ApplicationContextAware {

    public static final Map<String, String> INTERFACE_2_SERVICE_REF = new HashMap<>();

    private String interfaceName;

    private String ref;

    private String registry;

    private String version;

    public String getInterfaceName() {
        return interfaceName;
    }

    public void setInterfaceName(String interfaceName) {
        this.interfaceName = interfaceName;
    }

    public String getRef() {
        return ref;
    }

    public void setRef(String ref) {
        this.ref = ref;
    }

    public String getRegistry() {
        return registry;
    }

    public void setRegistry(String registry) {
        this.registry = registry;
    }


    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        this.version = version;
    }

    @Override
    public String toString() {
        return "Service [interfaceName=" + interfaceName + ", ref=" + ref + ", registry=" + registry
                + ", version=" + version + "]";
    }

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        ContextFactory.regiest(applicationContext);
    }
    
}
