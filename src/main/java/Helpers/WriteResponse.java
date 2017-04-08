package Helpers;

public class WriteResponse {

    public String writeResponse(String command, String data) {
        String result = null;
        switch (command) {
            case "list":
                result = data.replaceAll(";", "\n");
                break;
            case "sum":
            case "count":
            case "info account":
            case "info depositor":
            case "show type":
            case "show bank":
            case "add":
            case "delete":
                break;
        }
        return result;
    }

}
