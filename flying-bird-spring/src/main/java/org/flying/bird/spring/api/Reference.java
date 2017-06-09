package org.flying.bird.spring.api;

/**
 * @author nieyanshun
 *
 */
public class Reference {

    private String clazz;

    private int retries;

    private String loadBlance;

    private int timeout;

    private String registry;

    private String cluster;

    private String url;



    // private Reference(Builder builder) {
    // this.clazz = builder.clazz;
    // this.retries = builder.retries;
    // this.loadBlance = builder.loadBlance;
    // this.timeout = builder.timeout;
    // this.registry = builder.registry;
    // this.cluster = builder.cluster;
    // this.url = builder.url;
    // }

    // public static class Builder {
    // private String clazz;
    //
    // private int retries;
    //
    // private String loadBlance;
    //
    // private int timeout;
    //
    // private String registry;
    //
    // private String cluster;
    //
    // private String url;
    //
    // public Builder clazz(String clazz) {
    // this.clazz = clazz;
    // return this;
    // }
    //
    // public Builder retries(int retries) {
    // this.retries = retries;
    // return this;
    // }
    //
    // public Builder loadBlance(String loadBlance) {
    // this.loadBlance = loadBlance;
    // return this;
    // }
    //
    // public Builder timeout(int timeout) {
    // this.timeout = timeout;
    // return this;
    // }
    //
    // public Builder registry(String registry) {
    // this.registry = registry;
    // return this;
    // }
    //
    // public Builder cluster(String cluster) {
    // this.cluster = cluster;
    // return this;
    // }
    //
    // public Builder url(String url) {
    // this.url = url;
    // return this;
    // }
    //
    // public Reference builder() {
    // return new Reference(this);
    // }
    // }


    public void setClazz(String clazz) {
        this.clazz = clazz;
    }

    public void setRetries(int retries) {
        this.retries = retries;
    }

    public void setLoadBlance(String loadBlance) {
        this.loadBlance = loadBlance;
    }

    public void setTimeout(int timeout) {
        this.timeout = timeout;
    }

    public void setRegistry(String registry) {
        this.registry = registry;
    }

    public void setCluster(String cluster) {
        this.cluster = cluster;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getClazz() {
        return clazz;
    }

    public int getRetries() {
        return retries;
    }

    public String getLoadBlance() {
        return loadBlance;
    }

    public int getTimeout() {
        return timeout;
    }

    public String getRegistry() {
        return registry;
    }

    public String getCluster() {
        return cluster;
    }

    public String getUrl() {
        return url;
    }
}
