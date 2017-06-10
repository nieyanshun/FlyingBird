package org.flying.bird.spring.invoker;

public interface Invoker<T>  {

    /**
     * get service interface.
     * 
     * @return service interface.
     */
    Class<T> getInterface();

    /**
     * invoke.
     * 
     * @param invocation
     * @return result
     * @throws RpcException
     */
    Object invoke(Invocation invocation) ;
    

}