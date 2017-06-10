package org.flying.bird.core;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.util.HashMap;
import java.util.Map;
import org.flying.bird.common.MethodArg;
import org.flying.bird.common.URL;
import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;

/**
 * Unit test for simple App.
 */
public class AppTest extends TestCase {
    /**
     * Create the test case
     *
     * @param testName name of the test case
     */
    public AppTest(String testName) {
        super(testName);
    }

    /**
     * @return the suite of tests being tested
     */
    public static Test suite() {
        return new TestSuite(AppTest.class);
    }

    /**
     * Rigourous Test :-)
     */
    public void testApp() {

        Class clazz = null;
        try {
            clazz = Class.forName("org.flying.bird.core.ProxyAble");
            System.out.println(clazz.getName());
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        Method[] methods = clazz.getMethods();
        for (Method m : methods) {
            System.out.println(m.getName());
        }
        assertTrue(true);
    }

    public void testProxy() {
        Impl impl = new Impl();
        ProxyHandler handler = new ProxyHandler(impl);
        ProxyAble implService = (ProxyAble) Proxy.newProxyInstance(Impl.class.getClassLoader(),
                Impl.class.getInterfaces(), handler);
        implService.print();
    }

    public void testInvokProxyService() {
        Map<String, MethodArg> param = new HashMap<>();
        MethodArg arg1 = new MethodArg("java.lang.String", "Hello World !");
        param.put("1", arg1);
        URL url = new URL.Builder().className("org.flying.bird.core.ProxyAble").method("print")
                .param(param).build();
        try {
            Class clazz = Class.forName("org.flying.bird.core.ProxyAble");
            Method[] methods = clazz.getMethods();
            for (Method m : methods) {
                if (m.getName().equals(url.getMethod())) {
                    Class[] paramTypes = m.getParameterTypes();
                    if (paramTypes.length != url.getParam().keySet().size()) {
                        continue;
                    } else {
                        int tag = 1;
                        boolean matched = true;
                        Object[] args = new Object[url.getParam().keySet().size()];
                        for (Class methodParam : paramTypes) {
                            String name = url.getParam().get(String.valueOf(tag)).getType();
                            if (!name.equals(methodParam.getName())) {
                                matched = false;
                                break;
                            }
                            args[tag - 1] = url.getParam().get(String.valueOf(tag)).getValue();
                            tag++;
                        }
                        if (matched) {
                            // do invoke
                            Impl impl = new Impl();
                            ProxyHandler handler = new ProxyHandler(impl);
                            ProxyAble implService = (ProxyAble) Proxy.newProxyInstance(
                                    impl.getClass().getClassLoader(),
                                    impl.getClass().getInterfaces(), handler);
                            try {
                                Object result = m.invoke(implService, args);
                                System.out.println("Result : " + result);
                            } catch (IllegalAccessException e) {
                                e.printStackTrace();
                            } catch (IllegalArgumentException e) {
                                e.printStackTrace();
                            } catch (InvocationTargetException e) {
                                e.printStackTrace();
                            }
                        }
                    }
                }
            }


        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    public static class ProxyHandler implements InvocationHandler {
        ProxyAble proxyObj;

        ProxyHandler(ProxyAble proxy) {
            this.proxyObj = proxy;
        }

        @Override
        public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
            System.out.println("Before invoke ....");
            Object obj = method.invoke(proxyObj, args);
            System.out.println("After invoke ....");
            return obj;
        }

    }
}
