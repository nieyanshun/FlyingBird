package org.flying.bird.remoting.client;

import java.util.HashMap;
import java.util.Map;

public class BirdClientFactory {
    public static final String DEFAULT_CLIENT = "127.0.0.1:9000";


    private static Map<String, BirdClient> CLIENT_POOL = new HashMap<>();

    public static BirdClient open() {
        synchronized (CLIENT_POOL) {
            if (!CLIENT_POOL.containsKey(DEFAULT_CLIENT)) {
                BirdClient client = new BirdClient();
                client.connect();
                CLIENT_POOL.put(DEFAULT_CLIENT, client);
                return client;
            }
            return CLIENT_POOL.get(DEFAULT_CLIENT);
        }
    }

    public static BirdClient open(String host, int port) {
        final String key = host.concat(":").concat(String.valueOf(port));
        synchronized (key) {
            if (!CLIENT_POOL.containsKey(key)) {
                BirdClient client = new BirdClient(host, port);
                client.connect();
                CLIENT_POOL.put(key, client);
                return client;
            }
            return CLIENT_POOL.get(key);
        }
    }

    public void disConnect(BirdClient client) {
        client.disConnect();
    }
}
