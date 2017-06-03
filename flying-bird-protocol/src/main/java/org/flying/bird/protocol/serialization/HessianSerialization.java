package org.flying.bird.protocol.serialization;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import org.flying.bird.protocol.SerialPool;
import org.flying.bird.protocol.exception.BirdDecodingException;
import com.caucho.hessian.io.HessianInput;
import com.caucho.hessian.io.HessianOutput;

/**
 * @author nieyanshun
 *
 */
public class HessianSerialization implements Serialization {

    @Override
    public byte getContentTypeId() {
        return SerialPool.HESSIAN;
    }

    @Override
    public String getContentType() {
        return "hessian";
    }

    @Override
    public byte[] serialize(Object obj) {

        if (null == obj) {
            return null;
        }
        final ByteArrayOutputStream os = new ByteArrayOutputStream();
        final HessianOutput hessianOut = new HessianOutput(os);
        try {
            hessianOut.writeObject(obj);
        } catch (IOException e) {
            throw new BirdDecodingException(e);
        } finally {
            try {
                os.close();
                hessianOut.close();
            } catch (IOException e) {
            }
        }
        return os.toByteArray();

    }

    @Override
    public Object deserialize(byte[] data) {
        if (null == data) {
            return null;
        }
        final ByteArrayInputStream in = new ByteArrayInputStream(data);
        final HessianInput hessianIn = new HessianInput(in);
        Object body = null;
        try {
            body = hessianIn.readObject();
        } catch (IOException e) {
            throw new BirdDecodingException(e);
        } finally {
            try {
                in.close();
            } catch (IOException e) {
            }
            hessianIn.close();
        }
        return body;
    }

}
