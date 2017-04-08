package server;

import static Helpers.Helper.translateCode;

public class Server {

    public static void main(String[] args) {
        System.out.println(translateCode(2));

        DataControl dataControl = new DataControl();
        ServerConnectorPool serverConnectorPool = new ServerConnectorPool(dataControl);
        serverConnectorPool.execute();
    }
}
