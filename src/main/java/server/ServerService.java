package server;

import core.Constants;

import java.io.IOException;
import java.net.ServerSocket;
import java.util.LinkedList;
import java.util.List;

public class ServerService extends Thread {

    static ServerSocket socket;
    public static List<ConnectedClient> clients = new LinkedList<>();

    @Override
    public void start() {

        try {
            socket = new ServerSocket(Constants.PORT);
        } catch (IOException e) {
            System.out.println(e.toString());
            System.exit(0);
//            e.printStackTrace();
        }
        System.out.println("Server service started");
        while (true) {
            ConnectedClient client = null;
            try {
                client = new ConnectedClient(socket.accept());
            } catch (IOException e) {
                e.printStackTrace();
            }
            clients.add(client);
            if (client != null) {
                client.start();
            }
        }

    }


}
