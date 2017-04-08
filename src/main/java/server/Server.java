package server;

public class Server {

    public static void main(String[] args) {
        System.out.println("Server started");

        DataControl dataControl = new DataControl();
        dataControl.init();

        ServerConnectorPool serverConnectorPool = new ServerConnectorPool(dataControl);
        serverConnectorPool.execute();


    }
}
