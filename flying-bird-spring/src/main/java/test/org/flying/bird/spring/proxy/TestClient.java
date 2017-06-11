package test.org.flying.bird.spring.proxy;

import org.springframework.context.support.ClassPathXmlApplicationContext;
import test.org.flying.bird.spring.Arg;
import test.org.flying.bird.spring.Service;

public class TestClient {

    public static void main(String[] args) {

        final ClassPathXmlApplicationContext context =
                new ClassPathXmlApplicationContext("spring-client-test.xml");

        context.start();
        try {
            final Service servcie = (Service) context.getBean("birdService");

            final String result = servcie.print(new Arg("Test!"));
            System.out.println("Invoke result :" + result);

        } catch (Exception e) {
            e.printStackTrace();
        }
        try {
            Thread.currentThread().sleep(10000L);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        context.close();

    }

}
