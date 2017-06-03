package org.flying.bird.protocol;

import java.io.Serializable;

/**
 * Binary message
 * 
 * @author nieyanshun
 * 
 * 
 * 
 */
public interface Message extends Serializable {

    Header getHeader();

    Object body();
}
