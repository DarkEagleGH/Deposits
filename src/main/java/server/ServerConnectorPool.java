package server;

import Helpers.Constants;

import java.io.IOException;
import java.net.ServerSocket;
import java.util.concurrent.LinkedBlockingQueue;

public class ServerConnectorPool {

    static ServerSocket socket;
    private static LinkedBlockingQueue requestQueue;
    private static LinkedBlockingQueue responseQueue;

//    public static List<ServerConnector> clients = new LinkedList<>();

    public void execute() {

        try {
            socket = new ServerSocket(Constants.PORT);
        } catch (IOException e) {
            System.out.println(e.toString());
            System.exit(0);
//            e.printStackTrace();
        }
        System.out.println("Listening");
        while (true) {
            ServerConnector client = null;
            try {
                client = new ServerConnector(socket.accept());
            } catch (IOException e) {
                e.printStackTrace();
            }
            if (client != null) {
//                clients.add(client);
                client.execute();
            }
        }

    }


}
