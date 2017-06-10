package test.org.flying.bird.spring;


public class ServiceImpl implements Service {

    @Override
    public void test() {
        System.out.println("ServiceImpl test invoking.");
    }

    @Override
    public String print(Arg arg) {
        System.out.println("ServiceImpl test invoking." + arg.getArg());
        return "Result from server.";
    }

}
