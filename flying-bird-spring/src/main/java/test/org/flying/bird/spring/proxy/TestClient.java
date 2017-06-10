package test.org.flying.bird.spring.proxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.util.HashMap;
import java.util.Map;

import org.flying.bird.common.MethodArg;
import org.flying.bird.common.URL;
import org.flying.bird.remoting.Request;
import org.flying.bird.remoting.client.BirdClient;
import org.flying.bird.spring.SpringContext;
import org.flying.bird.spring.api.Reference;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import test.org.flying.bird.spring.Arg;
import test.org.flying.bird.spring.Service;

public class TestClient {

    public static void main(String[] args) {

        final ClassPathXmlApplicationContext context =
                new ClassPathXmlApplicationContext("spring-client-test.xml");

        context.start();
        final BirdClient client = new BirdClient();
        client.connect();
        try {
            final Reference referenceBeanHolder = (Reference) context.getBean("birdService");

            final Class[] interfaces = new Class[1];

            interfaces[0] =
                    SpringContext.class.getClassLoader().loadClass(referenceBeanHolder.getClazz());
            final Arg arg = new Arg("Rpc arg");
            test.org.flying.bird.spring.Service service =
                    (Service) Proxy.newProxyInstance(SpringContext.class.getClassLoader(),
                            interfaces, new InvocationHandler() {
                                @Override
                                public Object invoke(Object proxy, Method method, Object[] args)
                                        throws Throwable {
                                    System.out.println("Invoke " + method.getName());
                                    final Request request = new Request();
                                    final Map<String, MethodArg> param = new HashMap<>();
                                    final MethodArg arg1 =
                                            new MethodArg("test.org.flying.bird.spring.Arg", arg);
                                    param.put("1", arg1);
                                    final URL reqUrl = new URL.Builder()
                                            .className(referenceBeanHolder.getClazz())
                                            .method("print").param(param).build();
                                    request.setMsg(reqUrl);
                                    return client.request(request).get().getValue();
                                }
                            });
            final String result = service.print(arg);
            System.out.println("Invoke result :" + result);

        } catch (Exception e) {
            e.printStackTrace();
        }
        try {
            Thread.currentThread().sleep(10000L);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        client.disConnect();
        context.close();

    }

}
