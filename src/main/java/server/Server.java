package server;

import java.util.concurrent.LinkedBlockingQueue;

public class Server {
    private static LinkedBlockingQueue<String> requestQueue;
    private static LinkedBlockingQueue<String> responseQueue;

    public static void main(String[] args) {
        System.out.println("Server started");

        requestQueue = new LinkedBlockingQueue<>();
        responseQueue = new LinkedBlockingQueue<>();
        ServerConnectorPool serverConnectorPool = new ServerConnectorPool();
        serverConnectorPool.execute();

        DataControl dataControl = new DataControl();
        dataControl.init();
    }
}
