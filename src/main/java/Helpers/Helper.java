package Helpers;

import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Helper {

    public static Map<String, String> parseLine(String line) {
        Map<String, String> result = new HashMap<>();
        String regex = "^(show (?:type|bank)|info (?:account|depositor)|list|sum|count|add|delete|exit|quit|help)(?:\\s(.+))?$";
        Matcher matcher = Pattern.compile(regex, Pattern.CASE_INSENSITIVE).matcher(line);
        if (matcher.matches()) {
            switch (matcher.group(1).toLowerCase()) {
                case "exit":
                case "quit":
                    result.put("code", "1");
                    break;
                case "list":
                case "sum":
                case "count":
                    result.put("code", "0");
                    result.put("command", matcher.group(1).toLowerCase());
                    break;
                case "info account":
                case "info depositor":
                case "show type":
                case "show bank":
                case "add":
                case "delete":
                    if (matcher.group(2) != null && !matcher.group(2).isEmpty()) {
                        result.put("code", "0");
                        result.put("command", matcher.group(1).toLowerCase());
                        result.put("param", matcher.group(2));
                    } else {
                        result.put("code", "101");
                    }
                    break;
                case "help":
                    result.put("code", "5");
                    break;
                default:
                    result.put("code", "102");
            }
        } else {
            result.put("code", "102");
        }
        return result;
    }

    public static String normalize(String enter) {
        String result = enter.trim();
        Pattern pattern = Pattern.compile("(\\s{2,})");
        return pattern.matcher(result).replaceAll(" ");
    }

    /*
Code groups:
      x     : special state
    100     : parsing
    11x     : params check

    2xx     : transmission
*/
    public static String translateCode(int code) {
        StringBuilder result = new StringBuilder("[");
        result.append(code).append("] ");
        switch (code) {
            case 0:
                result.append("OK");
                break;
            case 1:
                result.append("Exit");
                break;
            case 2:
                result.append("Server started");
                break;
            case 3:
                result.append("Client started");
                break;
            case 101:
                result.append("Not enough params");
                break;
            case 102:
                result.append("No such command");
                break;
            case 110:
                result.append("Wrong account id");
                break;
            case 201:
                result.append("No connection to server ").append(Constants.SERVER_ADDRESS)
                        .append(":").append(Constants.PORT);
                break;
            case 202:
                result.append("Lost connection with server. Exit");
                break;
            case 203:
                result.append("Disconnect from server. Exit");
                break;
            case 301:
                result.append("No data");
                break;
            case 302:
                result.append("No such AccountId");
                break;
            case 303:
                result.append("No such Depositor");
                break;
            case 304:
                result.append("No such Type");
                break;
            case 305:
                result.append("No such Bank");
                break;
            case 306:
                result.append("AccountId already exists");
                break;
            case 307:
                result.append("Wrong Amount value");
                break;
            case 308:
                result.append("Wrong Profitability value");
                break;
            case 309:
                result.append("Wrong TermOfDeposit value");
                break;
            case 310:
                result.append("Data writing failure");
                break;
            default:
                result.append("Unknown error code");
        }
        return result.toString();
    }
}
