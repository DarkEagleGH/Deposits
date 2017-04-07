package server;

import core.Constants;
import core.Helper;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {
    public static void main(String[] args) throws IOException {
        try {
            ServerSocket ss = new ServerSocket(Constants.PORT);
            Socket socket = ss.accept();
            System.out.println(Helper.translateCode(200));
            InputStream sin = socket.getInputStream();
            OutputStream sout = socket.getOutputStream();
            DataInputStream in = new DataInputStream(sin);
            DataOutputStream out = new DataOutputStream(sout);

            String line = null;
            while(true) {
                line = in.readUTF();
                System.out.println(line);
                out.flush();
            }
        } catch(Exception x) { x.printStackTrace(); }
    }
}
