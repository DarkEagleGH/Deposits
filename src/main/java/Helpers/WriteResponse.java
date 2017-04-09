package Helpers;

import java.util.Map;

import static Helpers.Helper.translateCode;

public class WriteResponse {

    public void writeResponse(Map<String, String> response, String command) {
        if (!response.containsKey("code")) {
            System.out.println(translateCode(205));
            return;
        }
        if (response.get("code").equals("0")) {
            String result = null;
            if (response.containsKey("data")) {
                switch (command) {
                    case "list":
                        result = response.get("data").replaceAll(";", "\n").trim();
                        break;
                    case "sum":
                        try {
                            double res = Long.parseLong(response.get("data")) / 100;
                            result = Double.toString(res);
                        } catch (NumberFormatException e) {
                            System.out.println(translateCode(205));
                            return;
                        }
                        break;
                    case "info account":
                        try {
                            String[] params = response.get("data").split(";");
                            double res = Long.parseLong(params[5]) / 100;
                            params[5] = Double.toString(res);
                            res = Integer.parseInt(params[6]) / 100;
                            params[6] = Double.toString(res).concat("%");
                            StringBuilder sb = new StringBuilder();
                            for (String str: params) {
                                sb.append(str).append("\n");
                            }
                            result = sb.toString().trim();
                        } catch (NumberFormatException e) {
                            System.out.println(translateCode(205));
                            return;
                        }

                        break;
                    case "count":
                    case "info depositor":
                    case "show type":
                    case "show bank":
                        result = response.get("data");
                }
            } else {
                switch (command) {
                    case "add":
                    case "delete":
                        result = translateCode(Integer.parseInt(response.get("code")));
                }
            }
            System.out.println(result);
        } else {
            System.out.println(translateCode(Integer.parseInt(response.get("code"))));
        }
    }
}
