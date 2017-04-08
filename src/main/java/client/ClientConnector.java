package client;

import Helpers.Constants;
import Helpers.Helper;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.InetAddress;
import java.net.Socket;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.TimeUnit;

import static Helpers.Helper.translateCode;

public class ClientConnector implements Runnable {
    private InetAddress ipAddress;
    private Socket socket;
    private DataInputStream input;
    private DataOutputStream output;
    private boolean connected;
    private boolean stop;
    private LinkedBlockingQueue<String> requestQueue;
    private LinkedBlockingQueue<String> responseQueue;

    public ClientConnector(LinkedBlockingQueue requestQueue, LinkedBlockingQueue responseQueue) {
        try {
            ipAddress = InetAddress.getByName(Constants.SERVER_ADDRESS);
            socket = new Socket(ipAddress, Constants.PORT);
            socket.setSoTimeout(Constants.RESPONSE_TIMEOUT);
            input = new DataInputStream(socket.getInputStream());
            output = new DataOutputStream(socket.getOutputStream());
            this.requestQueue = requestQueue;
            this.responseQueue = responseQueue;
            connected = true;
        } catch (IOException e) {
            System.out.println(translateCode(201));
            System.exit(0);
//            e.printStackTrace();
        }
    }

    @Override
    public void run() {
        String data;
        while (!stop && connected) {
            try {
                data = requestQueue.poll(Constants.RESPONSE_TIMEOUT, TimeUnit.MILLISECONDS);
                if (data != null) {
                    output.writeUTF(data);
                    responseQueue.put(input.readUTF());
                } else {
                    output.writeUTF(Constants.KEEP_ALIVE_SEQUENCE);
                }
            } catch (InterruptedException | IOException e) {
                this.connected = false;
//                this.stop = true;
//                e.printStackTrace();
            }
        }
        if (!connected) {
            System.out.println(Helper.translateCode(202));
        }
        if (stop) {
            System.out.println(Helper.translateCode(203));
        }
        System.exit(0);
    }

    public boolean isConnected() {
        return connected;
    }

    public void execute() {
        Thread thread = new Thread(this);
        thread.start();
    }

    public void stop() {
        this.stop = true;
    }
}
