package org.flying.bird.protocol;

import org.flying.bird.protocol.serialization.HessianSerialization;
import org.flying.bird.protocol.serialization.Serialization;

public class SerialPool {

    public static final byte HESSIAN = 0X01;

    public static final byte MASK = 0x0F;

    public static Serialization getSerialization(byte serial) {
        Serialization seialization = null;

        switch (serial) {
            case HESSIAN:
                seialization = new HessianSerialization();
                break;

            default:
                break;
        }
        return seialization;
    }
}
