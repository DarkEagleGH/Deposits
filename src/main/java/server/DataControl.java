package server;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import static Helpers.Constants.*;

class DataControl {
    private List<Deposit> data;
    private Map<String, String> request;
    private DataConnector dataConnector;


    DataControl() {
        dataConnector = new DataConnector();
        this.data = dataConnector.getData();
    }

    void init() {
//        for (int i=0; i<5; i++) {
//            data.add(new Deposit("bank", "uk", "type", "John", Double.toString(Math.random() * 10000), 999999l, 14, 360));
//        }
        System.out.println(data.toString());
    }

    @SuppressWarnings("unchecked")
    String execute(String requestLine) {
        ObjectMapper mapper = new ObjectMapper();
        try {
            request = mapper.readValue(requestLine, Map.class);
        } catch (IOException e) {
            e.printStackTrace();
        }
        Map<String, String> response = new HashMap<>();
        StringBuilder responseLine = new StringBuilder();
        if (request.get("command").equals("add")) {
            response.put("code", add(request.get("param")));

        } else if (request.get("command").equals("delete")) {
            response.put("code", delete(request.get("param")));

        } else {
            if (data == null || data.isEmpty()) {
                response.put("code", "301");
                try {
                    return mapper.writeValueAsString(response);
                } catch (JsonProcessingException e) {
                    e.printStackTrace();
                }
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

    synchronized private String delete(String accountId) {
        boolean exists = false;
        final Iterator<Deposit> i = data.iterator();
        while (i.hasNext()) {
            if (accountId.equals(i.next().getAccountId())) {
                i.remove();
                exists = true;
            }
        }
        if (exists) {
            if (!dataConnector.writeData(data)) {
                return "310";
            }
            return "0";
        } else {
            return "302";
        }
    }

    synchronized private String add(String depositParams) {
        String[] params = depositParams.split(";");
        for (Deposit dep : data) {
            if (params[0].equals(dep.getAccountId())) {
                return "306";
            }
        }
        if (!TYPES.containsKey(params[3])) {
            return "304";
        }
        if (Long.parseLong(params[5]) < 0) {
            return "307";
        }
        if (Integer.parseInt(params[6]) < 0) {
            return "308";
        }
        if (Integer.parseInt(params[7]) < 0) {
            return "309";
        }
        Deposit deposit = new Deposit(params[0], params[1], params[2], params[3], params[4],
                Long.parseLong(params[5]) * 100,
                Integer.parseInt(params[6]) * 100,
                Integer.parseInt(params[7]));
        data.add(deposit);
        if (!dataConnector.writeData(data)) {
            return "310";
        }
        return "0";
    }


}
