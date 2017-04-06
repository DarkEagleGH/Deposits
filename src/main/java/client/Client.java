package client;

import java.util.Map;
import java.util.Scanner;

import static core.Helper.*;

public class Client {
    private static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        while (true) {
            String line = normalize(scanner.nextLine());
            if (line.isEmpty()) {
                continue;
            }
            Map<String, String> parsed = parseLine(line);
            System.out.println(parsed.toString());
            System.out.println(translateCode(Integer.parseInt(parsed.get("code"))));
        }
    }

    static void request(String enter) {

    }
}
