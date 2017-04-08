package server;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.util.HashMap;
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
        StringBuilder responseLine = new StringBuilder();
        if (request.get("command").equals("add")) {
            add(request.get("param"));
        } else if (request.get("command").equals("delete")) {
            delete(request.get("param"));
        } else {
            if (data == null || data.isEmpty()) {
                response.put("code", "301");
            }
            response.put("code", "0");
            boolean exists = false;
            switch (request.get("command")) {
                case "list":
                    for (Deposit dep : data) {
                        responseLine.append(dep.getAccountId()).append(";");
                    }
                    response.put("data", responseLine.toString());
                    break;
                case "sum":
                    long sum = 0;
                    for (Deposit dep : data) {
                        sum += dep.getAmountOnDeposit();
                    }
                    response.put("data", Long.toString(sum));
                    break;
                case "count":
                    response.put("data", Integer.toString(data.size()));
                    break;
                case "info account":
                    for (Deposit dep : data) {
                        if (request.get("param").equals(dep.getAccountId())) {
                            responseLine.append(dep.getAccountId()).append(";");
                            responseLine.append(dep.getName()).append(";");
                            responseLine.append(dep.getCountry()).append(";");
                            responseLine.append(dep.getType()).append(";");
                            responseLine.append(dep.getDepositor()).append(";");
                            responseLine.append(dep.getAmountOnDeposit()).append(";");
                            responseLine.append(dep.getProfitability()).append(";");
                            responseLine.append(dep.getTermOfDeposit()).append(";");
                            exists = true;
                            break;
                        }
                    }
                    if (exists) {
                        response.put("data", responseLine.toString());
                    } else {
                        response.put("code", "302");
                    }
                    break;
                case "info depositor":
                    for (Deposit dep : data) {
                        if (request.get("param").equals(dep.getDepositor())) {
                            responseLine.append(dep.getAccountId()).append(";");
                            exists = true;
                        }
                    }
                    if (exists) {
                        response.put("data", responseLine.toString());
                    } else {
                        response.put("code", "303");
                    }
                case "show type":
                    for (Deposit dep : data) {
                        if (request.get("param").equals(dep.getType())) {
                            responseLine.append(dep.getAccountId()).append(";");
                            exists = true;
                        }
                    }
                    if (exists) {
                        response.put("data", responseLine.toString());
                    } else {
                        response.put("code", "304");
                    }
                case "show bank":
                    for (Deposit dep : data) {
                        if (request.get("param").equals(dep.getName())) {
                            responseLine.append(dep.getAccountId()).append(";");
                            exists = true;
                        }
                    }
                    if (exists) {
                        response.put("data", responseLine.toString());
                    } else {
                        response.put("code", "305");
                    }
                    break;
            }
        }

        try {
            return mapper.writeValueAsString(response);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        return null;
    }

    String delete(String accountId) {

        return "";
    }

    String add(String data) {

        return "";
    }


}
