package server;

import Helpers.Constants;

import java.io.IOException;
import java.net.ServerSocket;

class ServerConnectorPool implements Runnable {

    private static ServerSocket socket;
    private DataControl dataControl;

    ServerConnectorPool(DataControl dataControl) {
        this.dataControl = dataControl;
    }

    @Override
    public void run() {
        try {
            socket = new ServerSocket(Constants.PORT);
        } catch (IOException e) {
            System.out.println(e.toString());
            System.exit(0);
//            e.printStackTrace();
        }

        while (true) {
            ServerConnector client = null;
            try {
                client = new ServerConnector(socket.accept(), dataControl);
            } catch (IOException e) {
                e.printStackTrace();
            }
            if (client != null) {
                client.execute();
            }
        }
    }

    void execute() {
        Thread thread = new Thread(this);
        thread.start();
    }

}
