package org.flying.bird.protocol.serialization;


public interface Serialization {

    /**
     * get content type id
     * 
     * @return content type id
     */
    byte getContentTypeId();

    /**
     * get content type
     * 
     * @return content type
     */
    String getContentType();

    byte[] serialize(Object obj);


    Object deserialize(byte[] data);

}
