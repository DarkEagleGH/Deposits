package core;

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
        String regex = "^(show (?:type|bank)|info (?:account|depositor)|list|sum|count|add|delete)(?:\\s([-'\\w\\d ]+))?$";
        Matcher matcher = Pattern.compile(regex, Pattern.CASE_INSENSITIVE).matcher(line);
        if (matcher.matches()) {
            switch (matcher.group(1).toLowerCase()) {
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
    100     : parsing
    11x     : params check

    2xx     : transmission
*/
    public static String translateCode(int code) {
        String result;
        switch (code) {
            case 0:
                result = "OK";
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
