package client;

import Helpers.Constants;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.*;
import java.util.Map;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.TimeUnit;

import static Helpers.Helper.*;

public class Client {

    public static void main(String[] args) {
        Boolean exit = false;
        System.out.println(translateCode(3));

        LinkedBlockingQueue<String> requestQueue = new LinkedBlockingQueue<>();
        LinkedBlockingQueue<String> responseQueue = new LinkedBlockingQueue<>();
        BufferedReader keyboard = new BufferedReader(new InputStreamReader(System.in));
        ClientConnector clientConnector = new ClientConnector(requestQueue, responseQueue);
        clientConnector.execute();
        ObjectMapper mapper = new ObjectMapper();
        while (!exit) {
            String line = null;
            try {
                line = normalize(keyboard.readLine());
            } catch (IOException e) {
                e.printStackTrace();
            }
            if (line != null && line.isEmpty()) {
                continue;
            }
            Map<String, String> parsed = parseLine(line);

            if (!clientConnector.isConnected()) {
                exit = true;
            }
            switch (parsed.get("code")) {
                case "0":
                    parsed.remove("code");
                    try {
                        requestQueue.put(mapper.writeValueAsString(parsed));
                        System.out.println(responseQueue.poll(Constants.RESPONSE_TIMEOUT, TimeUnit.MILLISECONDS));
                    } catch (InterruptedException | JsonProcessingException e) {
                        e.printStackTrace();
                    }
                    break;
                case "1":
                    exit = true;
                    break;
                default:
                    System.out.println(translateCode(Integer.parseInt(parsed.get("code"))));
            }
        }

        clientConnector.stop();
    }
}
