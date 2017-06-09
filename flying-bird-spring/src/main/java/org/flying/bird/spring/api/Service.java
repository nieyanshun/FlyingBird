package org.flying.bird.spring.api;

public class Service {

    private String interfaceName;

    private String ref;

    private String registry;

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

    @Override
    public String toString() {
        return "Service [interfaceName=" + interfaceName + ", ref=" + ref + ", registry=" + registry
                + "]";
    }
}
