package org.flying.bird.spring;

import org.springframework.context.ApplicationContext;

public class ContextFactory {

    private static Context CONTEXT = new SpringContext();

    private static volatile boolean IS_INIT = false;

    public synchronized static void regiest(ApplicationContext c) {
        if (!IS_INIT)
            CONTEXT.setContext(c);
    }

    public static Context getContext() {
        if (null == CONTEXT) {
            throw new RuntimeException("Factory method regiest must called before getContext.");
        }
        return CONTEXT;
    }
}
