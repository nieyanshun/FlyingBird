package test.org.flying.bird.spring.proxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

import org.flying.bird.spring.SpringContext;
import org.flying.bird.spring.api.Reference;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import test.org.flying.bird.spring.Service;

public class TestClient {

    public static void main(String[] args) {

        ClassPathXmlApplicationContext context =
                new ClassPathXmlApplicationContext("spring-client-test.xml");

        context.start();
        try {
            Reference referenceBeanHolder = (Reference) context.getBean("birdService");

            Class[] interfaces = new Class[1];

            interfaces[0] =
                    SpringContext.class.getClassLoader().loadClass(referenceBeanHolder.getClazz());

            test.org.flying.bird.spring.Service service =
                    (Service) Proxy.newProxyInstance(SpringContext.class.getClassLoader(),
                            interfaces, new InvocationHandler() {
                                @Override
                                public Object invoke(Object proxy, Method method, Object[] args)
                                        throws Throwable {
                                    if (method.getName().equals("test"))
                                        System.out.println("========" + method.getName());
                                    return "Result";
                                }
                            });
            String result = service.print();
            System.out.println("Invoke result :" + result);

        } catch (Exception e) {
            e.printStackTrace();
        }
        context.close();

    }

}
