package org.flying.bird.common;

import java.io.Serializable;

public class MethodArg implements Serializable{

    private String type;

    private Object value;

    public MethodArg(String type, Object value) {
        this.type = type;
        this.value = value;
    }

    public String getType() {
        return type;
    }

    public Object getValue() {
        return value;
    }


}
