package org.flying.bird.protocol;

/**
 ** 0++++++++++7++++++++++15+++++++++++23++++++++++31
 * +    magic &   flag    |  version   |   ext     +
 * 1++++++++++ +++++++++++ +++++++++++  +++++++++++
 * +         Serial Id                             +
 * 2++++++++++ +++++++++++ +++++++++++ ++++++++++++
 * +          TimeStamp                            +  
 * 3++++++++++ +++++++++++ ++++++++++++ ++++++++++++
 * +          body length                          +
 * 4++++++++++ ++++++++++++ +++++++++++++++++++++++
 * 
 * 
 * @author nieyanshun
 *
 */
/**
 * @author nieyanshun
 *
 */
public interface Header {

    final static short MAGIC = 0x0666;

    final static short RET_MASK = 0x0FF2;

    final static short RET_CODE = 0x0662;

    final static short REQ_MASK = 0x0FF4;

    final static short REQ_CODE = 0x0664;

    static byte VERSION = 0x01;

    final static byte EXT = 0x00;

    final static int HEADER_LENGTH = 16;

    int serialId();

    int bodyLength();

    int timeStamp();

    byte version();

    /**
     * ext 低4位用作序列化协议
     * 
     * @return
     */
    byte ext();

    boolean isReq();

    boolean isRet();

}

