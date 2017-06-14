package org.flying.bird.rpc;

import java.io.IOException;
import java.lang.reflect.Method;
import javassist.CannotCompileException;
import javassist.ClassPool;
import javassist.CtClass;
import javassist.CtField;
import javassist.CtMethod;
import javassist.CtNewMethod;
import javassist.NotFoundException;
import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;
import javassist.util.proxy.MethodFilter;
import javassist.util.proxy.MethodHandler;
import javassist.util.proxy.ProxyFactory;


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
        assertTrue(true);
    }

    public void testJavassist() {

        String className = "org.flying.bird.rpc.ServiceProxy";
        String methodName = "testProxy";
        ClassPool pool = ClassPool.getDefault();
        try {
            CtClass clazz = pool.get(className);
            CtMethod method = clazz.getDeclaredMethod(methodName);
            String newMethodName = methodName + "$impl";
            method.setName(newMethodName);
            try {
                CtMethod newMethod = CtNewMethod.make("public void " + methodName
                        + "(){long start=System.currentTimeMillis();" + "" + newMethodName
                        + "(); System.out.println(\"耗时:\"+(System.currentTimeMillis()-start)+\"ms\");"
                        + "}", clazz);
                clazz.addMethod(newMethod);
                Service service = (Service) clazz.toClass().newInstance();
                service.testProxy();
            } catch (CannotCompileException | InstantiationException | IllegalAccessException e) {
                e.printStackTrace();
            }
        } catch (NotFoundException e) {
            e.printStackTrace();
        }
    }

    @SuppressWarnings("deprecation")
    public void testJavassistAop() {
        ProxyFactory factory = new ProxyFactory();
        // 设置父类，ProxyFactory将会动态生成一个类，继承该父类
        factory.setSuperclass(ServiceProxy.class);
        factory.setFilter(new MethodFilter() {
            @Override
            public boolean isHandled(Method m) {
                if (m.getName().equals("testProxy")) {
                    return true;
                }
                return false;
            }
        });
        // 设置拦截处理
        factory.setHandler(new MethodHandler() {
            @Override
            public Object invoke(Object self, Method thisMethod, Method proceed, Object[] args)
                    throws Throwable {
                long start = System.currentTimeMillis();
                Object result = proceed.invoke(self, args);
                System.out.println("耗时:" + (System.currentTimeMillis() - start) + "ms");
                return result;
            }
        });
        Class<?> c = factory.createClass();
        Service object = null;
        try {
            object = (Service) c.newInstance();
            object.testProxy();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
    }


    public static final String FIELD_NAME = "invoker";

    public static final String FIELD_SEPARATOR = " ";

    /**
     * 为接口实现一个代理实例
     */
    public void testJavassistProxyInterface() {
        String clazzName = Service.class.getName().concat("$Proxy");
        ClassPool pool = ClassPool.getDefault();
        CtClass clazz = pool.makeClass(clazzName);
        CtClass[] interfaces = new CtClass[1];
        try {
            interfaces[0] = pool.get("org.flying.bird.rpc.Service");
        } catch (NotFoundException e) {
            throw new RuntimeException(e);
        }
        clazz.setInterfaces(interfaces);
        try {
            final String invokerClassName = Invoker.class.getName();
            StringBuilder fieldSource = new StringBuilder("private").append(FIELD_SEPARATOR)
                    .append(invokerClassName).append(FIELD_SEPARATOR).append(FIELD_NAME)
                    .append(FIELD_SEPARATOR).append("=").append(FIELD_SEPARATOR).append("new")
                    .append(FIELD_SEPARATOR).append(invokerClassName).append("();");
            CtField f = CtField.make(fieldSource.toString(), clazz);
            clazz.addField(f);
        } catch (Exception e) {
            e.printStackTrace();
        }
        for (CtClass superClazz : interfaces) {
            CtMethod[] methods = superClazz.getDeclaredMethods();
            for (CtMethod m : methods) {
                try {
                    CtMethod newMethod = new CtMethod(m.getReturnType(), m.getName(),
                            m.getParameterTypes(), clazz);
                    newMethod.setBody(FIELD_NAME + ".invoke();");
                    // newMethod.setBody("System.out.println(invoker.toString());");
                    clazz.addMethod(newMethod);
                    Service s = (Service) clazz.toClass().newInstance();
                    s.testProxy();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
        try {
            clazz.writeFile("C:\\Users\\nieyanshun\\Desktop\\test");
        } catch (CannotCompileException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
