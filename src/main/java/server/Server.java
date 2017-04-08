package server;

import Helpers.ConfigProperties;
import Helpers.Constants;
import Helpers.Helper;

import java.util.concurrent.LinkedBlockingQueue;

public class Server {
    private static LinkedBlockingQueue<String> requestQueue;
    private static LinkedBlockingQueue<String> responseQueue;

    public static void main(String[] args) {
        System.out.println(Helper.translateCode(2));
        System.out.println(Constants.PORT);

        requestQueue = new LinkedBlockingQueue<>();
        responseQueue = new LinkedBlockingQueue<>();
        ServerConnectorPool serverConnectorPool = new ServerConnectorPool();
        serverConnectorPool.execute();

        DataControl dataControl = new DataControl();
        dataControl.init();
    }
}
