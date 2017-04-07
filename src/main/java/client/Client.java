package client;

import core.Constants;

import java.io.*;
import java.net.InetAddress;
import java.net.Socket;
import java.util.Map;

import static core.Helper.*;

public class Client {
    //    private static Scanner scanner = new Scanner(System.in);
    private static InetAddress ipAddress;
    private static Socket socket;
    private static DataInputStream input;
    private static DataOutputStream output;


    public static void main(String[] args) {
        Boolean exit = false;
        System.out.println("Client started");
        try {
            ipAddress = InetAddress.getByName(Constants.SERVER_ADDRESS);
            socket = new Socket(ipAddress, Constants.PORT);
            input = new DataInputStream(socket.getInputStream());
            output = new DataOutputStream(socket.getOutputStream());
        } catch (IOException e) {
            System.out.println(translateCode(201));
            System.exit(0);
//            e.printStackTrace();
        }

        BufferedReader keyboard = new BufferedReader(new InputStreamReader(System.in));

        while (!exit) {
            String line = null;
            try {
                line = normalize(keyboard.readLine());
            } catch (IOException e) {
                e.printStackTrace();
            }
            if (line.isEmpty()) {
                continue;
            }
            Map<String, String> parsed = parseLine(line);
            System.out.println(parsed.toString());
            System.out.println(translateCode(Integer.parseInt(parsed.get("code"))));
            switch (parsed.get("code")) {
                case "0":
                    parsed.remove("code");
                    request(parsed.toString());
                    break;
                case "1":
                    exit = true;
                    break;
            }
        }

        try {
            if (socket.isConnected()) {
                socket.close();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    static void request(String data) {
        try {
            output.writeUTF(data);
            output.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
