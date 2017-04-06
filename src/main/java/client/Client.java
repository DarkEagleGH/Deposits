package client;

import java.util.Scanner;

public class Client {
    static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        String enter = scanner.nextLine();
        validationEnter(enter);
    }

    static boolean validationEnter(String enter) {
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

    static void normalize() {

    }
}
