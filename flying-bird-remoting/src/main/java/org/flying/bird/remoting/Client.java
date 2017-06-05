package org.flying.bird.remoting;

import org.flying.bird.remoting.exception.RemotingException;
import org.flying.bird.remoting.future.ResponseFuture;


public interface Client {
    
    void connect();
    
    void disConnect();

    ResponseFuture request(Request req) throws RemotingException;

    void send(Request req) throws RemotingException;
}
