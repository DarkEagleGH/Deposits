package client;

import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Client {
    static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        String enter = scanner.nextLine();
        validationEnter(enter);
    }

    static boolean validationEnter(String enter) {
        String line = normalize(enter);
        boolean result = false;
        String operation[] = {"list", "sum", "count"};
        try {
            for (int i = 0; i < enter.length(); i++) {
                if (enter.equals(operation[i])) {
                    result = true;
                    break;
                }
            }
        } catch (Exception e) {
        }
        if (!result) {
            System.out.println("Вы ввели недопустимую команду, воспользуйтесь командой \"help\" чтобы вывести подсказку");
        }
        return result;
    }

    static void request(String enter) {

    }

    static String normalize(String enter) {
        String result = enter.trim();
        Pattern pattern = Pattern.compile("(\\s{2,})");
        return pattern.matcher(result).replaceAll(" ");
    }
}
