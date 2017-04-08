package client;

import Helpers.Constants;
import Helpers.FileReaderForHelp;
import Helpers.WriteResponse;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.*;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.TimeUnit;

import static Helpers.Helper.*;

public class Client {

    public static void main(String[] args) {
        Boolean exit = false;
        System.out.println(translateCode(3));
        WriteResponse writeResponse = new WriteResponse();
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
                        String responseLine = responseQueue.poll(Constants.RESPONSE_TIMEOUT, TimeUnit.MILLISECONDS);
                        if (responseLine == null || responseLine.isEmpty()) {
                            System.out.println(translateCode(204));
                            continue;
                        }
                        Map<String, String> response;
                        response = mapper.readValue(responseLine, new TypeReference<HashMap<String, String>>() {
                        });
                        if (response != null && response.containsKey("code")) {
                            if (response.get("code").equals("0")) {
                                if (response.containsKey("data")) {
                                    System.out.println(writeResponse.writeResponse(parsed.get("command"), response.get("data")));
                                }
                                if (parsed.get("command").equals("add")) {
                                    System.out.println("OK");
                                }
                            } else {
                                System.out.println(translateCode(Integer.parseInt(response.get("code"))));
                            }
                        }
                    } catch (InterruptedException | IOException e) {
                        e.printStackTrace();
                    }
                    break;
                case "1":
                    exit = true;
                    break;
                case "5":
                    FileReaderForHelp.ReadHelp();
                    break;
                default:
                    System.out.println(translateCode(Integer.parseInt(parsed.get("code"))));
            }
        }

        clientConnector.stop();
    }
}
