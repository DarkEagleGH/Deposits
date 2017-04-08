package server;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

class DataControl {
    private List<Deposit> data;
    private Map<String, String> request;


    DataControl() {
        DataConnector dataConnector = new DataConnector();
        this.data = dataConnector.getData();
    }

    void init() {
//        for (int i=0; i<5; i++) {
//            data.add(new Deposit("bank", "uk", "type", "John", Double.toString(Math.random() * 10000), 999999l, 14, 360));
//        }
        System.out.println(data.toString());
    }

    @SuppressWarnings("unchecked")
    synchronized String execute(String requestLine) {
        ObjectMapper mapper = new ObjectMapper();
        try {
            request = mapper.readValue(requestLine, Map.class);
        } catch (IOException e) {
            e.printStackTrace();
        }
        Map<String, String> response = new HashMap<>();
        switch (request.get("command")) {
            case "list":
                if (data != null) {
                    StringBuilder responseLine = new StringBuilder();
                    System.out.println(data.getClass());
                    for (Deposit dep : data) {
                        responseLine.append(dep.getAccountId()).append(";");
                    }
                    response.put("code", "0");
                    response.put("data", responseLine.toString());
                } else {
                    response.put("code", "no data");
                }
                break;
            case "sum":
            case "count":
                break;
            case "info account":
            case "info depositor":
            case "show type":
            case "show bank":
            case "add":
            case "delete":
                break;
        }

        try {
            return mapper.writeValueAsString(response);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        return null;
    }

    boolean delete(String accountId) {

        return true;
    }

    boolean add(String data) {

        return true;
    }


}
