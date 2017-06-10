package org.flying.bird.remoting;

import org.flying.bird.remoting.client.BirdClient;

public class TestClient {

    public static void main(String[] args) throws InterruptedException {
        BirdClient client = new BirdClient();
        try {
            client.connect();
            client.request(new Request().setMsg("hello!"));
        } catch (Exception e) {
            e.printStackTrace();
        }
        Thread.sleep(1000L);
        client.disConnect();
    }
}
