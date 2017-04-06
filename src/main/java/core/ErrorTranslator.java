package core;

public class ErrorTranslator {

    /*
    Code groups:
        100     : parsing
        11x     : params check

        2xx     : transmission
    */
    public static String translateCode(int code) {
        String result;
        switch (code) {
            case 100:
                result = "Input correct";
                break;
            case 101:
                result = "Not enough params";
                break;
            case 102:
                result = "No such command";
                break;
            case 110:
                result = "Wrong account id";
                break;
            case 200:
                result = "OK";
                break;
            default:
                result = "Unknown error code";
        }
        return result;
    }
}
