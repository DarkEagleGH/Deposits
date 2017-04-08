package server;

import Helpers.Constants;

import java.io.*;
import java.net.Socket;

class ServerConnector implements Runnable {
    private Socket socket;
    private DataInputStream input;
    private DataOutputStream output;
    private DataControl dataControl;

    ServerConnector(Socket sock, DataControl dataControl) throws IOException {
        this.socket = sock;
        this.dataControl = dataControl;
        input = new DataInputStream(socket.getInputStream());
        output = new DataOutputStream(socket.getOutputStream());
    }

    @Override
    public void run() {
        System.out.println("new user connected from " + socket.getInetAddress().toString() + ":" + socket.getPort());
        StringBuilder response = new StringBuilder();
        try {
            while (socket.isConnected()) {
                String requestLine = input.readUTF();
                if (requestLine.equals(Constants.KEEP_ALIVE_SEQUENCE)) {
                    continue;
                }
                System.out.println(requestLine);
                response.setLength(0);
                response.append(dataControl.execute(requestLine));
//                response.append(socket.toString()).append(" - ").append(data);
                output.writeUTF(response.toString());
            }
        } catch (IOException e) {
//            System.out.println(e.toString());
        } finally {
            System.out.println("user disconnected from " + socket.getInetAddress().toString() + ":" + socket.getPort());
        }
    }

    void execute() {
        Thread thread = new Thread(this);
        thread.start();
    }
}
