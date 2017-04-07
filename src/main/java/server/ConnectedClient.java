package server;

import java.io.*;
import java.net.Socket;

public class ConnectedClient extends Thread {
    private Socket socket;
    private DataInputStream input;
    private DataOutputStream output;

    public ConnectedClient(Socket socket) throws IOException {
        this.socket = socket;
        System.out.println("new user connected from " + socket.getInetAddress().toString());
//        input = new BufferedReader(new InputStreamReader(sock.getInputStream()));
//        output = new PrintWriter(sock.getOutputStream());
        input = new DataInputStream(this.socket.getInputStream());
        output = new DataOutputStream(this.socket.getOutputStream());
    }

    @Override
    public void start() {
        try {
            while (this.socket.isConnected()) {
                String data = input.readUTF();
                System.out.println(data);
            }
        } catch (IOException e) {
            System.out.println(e.toString());
        } finally {
            System.out.println("user disconnected from " + this.socket.getInetAddress().toString());
        }
    }

    public void send(String s) throws IOException {
//        output.println(s);
//        output.flush();
    }
}
