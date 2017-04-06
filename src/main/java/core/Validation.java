package core;

import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Validation {
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
                    result.put("code", "100");
                    result.put("command", matcher.group(1).toLowerCase());
                    break;
                case "info account":
                case "info depositor":
                case "show type":
                case "show bank":
                case "add":
                case "delete":
                    if (matcher.group(2) != null && !matcher.group(2).isEmpty()) {
                        result.put("code", "100");
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

}
