package org.flying.bird.common;

import java.util.Map;

public class URL {

    private String host;

    private int port;

    private String className;

    private String method;

    private Map<String, Object> param;

    public static class Builder {

        private String host;

        private int port;

        private String className;

        private String method;

        private Map<String, Object> param;

        public Builder host(String host) {
            this.host = host;
            return this;
        }

        public Builder port(int port) {
            this.port = port;
            return this;
        }

        public Builder className(String className) {
            this.className = className;
            return this;
        }

        public Builder method(String method) {
            this.method = method;
            return this;
        }

        public Builder param(Map<String, Object> param) {
            this.param = param;
            return this;
        }

        public URL build() {
            return new URL(this);
        }
    }

    private URL(Builder builder) {
        this.host = builder.host;
        this.className = builder.className;
        this.port = builder.port;
        this.method = builder.method;
        this.param = builder.param;
    }

    public String getHost() {
        return host;
    }

    public int getPort() {
        return port;
    }

    public String getClassName() {
        return className;
    }

    public String getMethod() {
        return method;
    }

    public Map<String, Object> getParam() {
        return param;
    }

    @Override
    public String toString() {
        return "URL [host=" + host + ", port=" + port + ", className=" + className + ", method="
                + method + ", param=" + param + "]";
    }

}
