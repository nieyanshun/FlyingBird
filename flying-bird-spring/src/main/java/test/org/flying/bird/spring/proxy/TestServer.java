package test.org.flying.bird.spring.proxy;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import test.org.flying.bird.spring.Service;

public class TestServer {

    public static void main(String[] args) {


        ClassPathXmlApplicationContext context =
                new ClassPathXmlApplicationContext("spring-test.xml");

        context.start();
        try {
            Object byType = context.getBean(org.flying.bird.spring.SpringContext.class);
            org.flying.bird.spring.api.Service serviceBeanHolder =
                    (org.flying.bird.spring.api.Service) context.getBean("birdService");
            System.out.println(serviceBeanHolder);

            String serviceName = serviceBeanHolder.getRef();

            Service service = (Service) context.getBean(serviceName);

            service.test();

        } catch (Exception e) {
            e.printStackTrace();
        }
        context.close();
    
    }

}
