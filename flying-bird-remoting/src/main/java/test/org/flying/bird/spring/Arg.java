package test.org.flying.bird.spring;

import java.io.Serializable;

public class Arg implements  Serializable{
    
    private String arg;

    public Arg(String arg) {
        this.arg = arg;
    }

    public String getArg() {
        return arg;
    }
}
