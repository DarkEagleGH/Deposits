package client;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;
import java.util.regex.Pattern;

import static core.ErrorTranslator.translateCode;
import static core.Validation.parseLine;

public class Client {
    static Scanner scanner = new Scanner(System.in);
    static Map<String, String> parsed = new HashMap<>();

    public static void main(String[] args) {
        while (true) {
            String line = normalize(scanner.nextLine());
            if (line.isEmpty()) {
                continue;
            }
            parsed = parseLine(line);
            System.out.println(parsed.toString());
            System.out.println(translateCode(Integer.parseInt(parsed.get("code"))));
        }
    }

    static void request(String enter) {

    }

    static String normalize(String enter) {
        String result = enter.trim();
        Pattern pattern = Pattern.compile("(\\s{2,})");
        return pattern.matcher(result).replaceAll(" ");
    }
}
