package server;

import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;
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
    synchronized String execute(String data) {
        try {
            ObjectMapper mapper = new ObjectMapper();
            request = mapper.readValue(data, Map.class);
        } catch (IOException e) {
            e.printStackTrace();
        }

        request.put("data", "response");
        return request.toString();
    }

}
