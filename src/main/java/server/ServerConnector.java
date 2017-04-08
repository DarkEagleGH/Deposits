package server;

import core.Constants;

import java.io.*;
import java.net.Socket;

public class ServerConnector implements Runnable {
    private Socket socket;
    private DataInputStream input;
    private DataOutputStream output;

    public ServerConnector(Socket sock) throws IOException {
        this.socket = sock;
        input = new DataInputStream(socket.getInputStream());
        output = new DataOutputStream(socket.getOutputStream());
    }

    @Override
    public void run() {
        System.out.println("new user connected from " + socket.getInetAddress().toString() + ":" + socket.getPort());
        StringBuffer response = new StringBuffer();
        try {
            while (socket.isConnected()) {
                String data = input.readUTF();
                if (data.equals(Constants.KEEP_ALIVE_SEQUENCE)){
                    continue;
                }
                System.out.println(data);
                response.setLength(0);
                response.append(socket.toString()).append(" - ").append(data);
                output.writeUTF(response.toString());
            }
        } catch (IOException e) {
//            System.out.println(e.toString());
        } finally {
            System.out.println("user disconnected from " + socket.getInetAddress().toString() + ":" + socket.getPort());
        }
    }

    public void execute() {
        Thread thread = new Thread(this);
        thread.start();
    }
}
