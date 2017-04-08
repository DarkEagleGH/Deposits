package Helpers;

import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Helper {
    //проверка кол-ва символов в accountId. по умолчанию 16 символов
    boolean validationAccountID(String accountId) {
        boolean resultValAccId;
        if (accountId.length() == 16) {
            resultValAccId = true;
        } else {
            resultValAccId = false;
        }
        return resultValAccId;
    }

    public static Map<String, String> parseLine(String line) {
        Map<String, String> result = new HashMap<>();
        String regex = "^(show (?:type|bank)|info (?:account|depositor)|list|sum|count|add|delete|exit|quit)(?:\\s(.+))?$";
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
        StringBuffer result = new StringBuffer("[");
        result.append(code).append("] ");
        switch (code) {
            case 0:
                result.append("OK");
                break;
            case 1:
                result.append("Exit");
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
            default:
                result.append("Unknown error code");
        }
        return result.toString();
    }
}
