package org.flying.bird.spring;


public class ContextFactory {

    private static Context CONTEXT = null;

    synchronized static void regiest(Context c) {
        if (null == CONTEXT) {
            CONTEXT = c;
        }
    }

    static Context getContext() {
        if (null == CONTEXT) {
            throw new RuntimeException("Factory method regiest must called before getContext.");
        }
        return CONTEXT;
    }
}
