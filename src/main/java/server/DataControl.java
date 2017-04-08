package server;

import java.util.List;

public class DataControl {
    private List<Deposit> data;
    private DataConnector dataConnector;

    DataControl() {
        dataConnector = new DataConnector();
        System.out.println(dataConnector.toString());
        this.data = dataConnector.getData();
    }

    void init() {
        data = dataConnector.getData();
//        for (int i=0; i<5; i++) {
//            data.add(new Deposit("bank", "uk", "type", "John", Double.toString(Math.random() * 10000), 999999l, 14, 360));
//        }
        System.out.println(data.toString());
//        dataConnector.writeData(data);
//        System.out.println("written");
    }
}
