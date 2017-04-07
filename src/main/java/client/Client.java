package client;

import core.Constants;

import java.io.*;
import java.net.InetAddress;
import java.net.Socket;
import java.util.Map;
import java.util.Scanner;

import static core.Helper.*;

public class Client {
//    private static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) throws IOException {
        try {
            InetAddress ipAddress = InetAddress.getByName(Constants.SERVER_ADDRESS);
            Socket socket = new Socket(ipAddress, Constants.PORT);
            InputStream sin = socket.getInputStream();
            OutputStream sout = socket.getOutputStream();
            DataInputStream in = new DataInputStream(sin);
            DataOutputStream out = new DataOutputStream(sout);
            BufferedReader keyboard = new BufferedReader(new InputStreamReader(System.in));

            while (true) {
                String line = normalize(keyboard.readLine());
                if (line.isEmpty()) {
                    continue;
                }
                Map<String, String> parsed = parseLine(line);
                System.out.println(parsed.toString());
                System.out.println(translateCode(Integer.parseInt(parsed.get("code"))));
                out.writeUTF(line);
                out.flush();
            }
        } catch (Exception x) {
            x.printStackTrace();
        }
    }

    static void request(String enter) {

    }
}
