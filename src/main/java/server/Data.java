package server;

import java.util.List;

public class Data {
    private List<Deposit> data;
    private DataConnector dataConnector;

    Data() {
        dataConnector = new DataConnector();
        this.data = dataConnector.getData();
    }
}
