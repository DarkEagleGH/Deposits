package server;

public class Server {

    public static void main(String[] args) {
        ServerConnectorPool serverConnectorPool = new ServerConnectorPool();
        serverConnectorPool.execute();
    }
}
