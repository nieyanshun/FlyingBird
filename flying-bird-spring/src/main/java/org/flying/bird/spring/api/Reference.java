package org.flying.bird.spring.api;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.util.HashMap;
import java.util.Map;
import org.flying.bird.common.MethodArg;
import org.flying.bird.common.URL;
import org.flying.bird.remoting.Request;
import org.flying.bird.remoting.client.BirdClient;
import org.flying.bird.remoting.client.BirdClientFactory;
import org.flying.bird.spring.ContextFactory;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.DisposableBean;
import org.springframework.beans.factory.FactoryBean;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;

/**
 * @author nieyanshun
 *
 */
@SuppressWarnings("rawtypes")
public class Reference
        implements FactoryBean, InitializingBean, DisposableBean, ApplicationContextAware {

    private String clazz;

    private int retries;

    private String loadBlance;

    private int timeout;

    private String registry;

    private String cluster;

    private String url;

    private String version;

    private Class<?> remoteInterface;



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

    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        this.version = version;
    }



    private BirdClient client;

    @Override
    public Object getObject() throws Exception {

        final Class<?>[] interfaces = new Class[1];

        interfaces[0] = remoteInterface;

        return Proxy.newProxyInstance(Reference.class.getClassLoader(), interfaces,
                new InvocationHandler() {
                    @Override
                    public Object invoke(Object proxy, Method method, Object[] args)
                            throws Throwable {
                        if (method.getName().equals("toString"))
                            return "Proxy bean toString invoked.";

                        if (method.getName().equals("hashCode")) {
                            throw new RuntimeException(
                                    "hashCode invoke not permitted on proxy bean.");
                        }

                        if (method.getName().equals("equals")) {
                            throw new RuntimeException(
                                    "equals invoke not permitted on proxy bean.");
                        }

                        final Request request = new Request();
                        final Map<String, MethodArg> methodParams = new HashMap<>();

                        Class<?>[] paramTypes = method.getParameterTypes();
                        int index = 0;
                        for (Class<?> param : paramTypes) {
                            if (!(param.isAssignableFrom(args[index].getClass()))) {
                                throw new RuntimeException("Method param class type exception.");
                            }
                            final MethodArg arg = new MethodArg(param.getName(), args[index]);
                            methodParams.put(String.valueOf(index + 1), arg);
                            index++;
                        }

                        final URL reqUrl = new URL.Builder().className(getClazz())
                                .method(method.getName()).param(methodParams).build();
                        request.setMsg(reqUrl);
                        return client.request(request).get().getValue();
                    }
                });
    }

    @Override
    public Class<?> getObjectType() {
        return remoteInterface;
    }

    @Override
    public boolean isSingleton() {
        return true;
    }

    private boolean isInit;

    @Override
    public void afterPropertiesSet() throws Exception {
        remoteInterface = Reference.class.getClassLoader().loadClass(getClazz());
        if (!isInit) {
            if (null == this.url) {
                client = BirdClientFactory.open();
            } else {
                String[] urls = url.split(":");
                client = BirdClientFactory.open(urls[0], Integer.valueOf(urls[1]));
            }
            isInit = true;
        }
    }

    @Override
    public void destroy() throws Exception {
        if (!isInit) {
            throw new RuntimeException("Reference bean not inited.");
        }
        client.disConnect();
    }

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        ContextFactory.regiest(applicationContext);
    }
}
