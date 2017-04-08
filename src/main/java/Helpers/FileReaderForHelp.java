package Helpers;

import java.io.FileReader;

public class FileReaderForHelp {

    public static void ReadHelp() {
//        try (FileReader fileReader = new FileReader("src/main/resources/help.txt")) {
        try (FileReader fileReader = new FileReader("help.txt")) {
            int c;
            while ((c = fileReader.read()) != -1) System.out.print((char) c);
            System.out.println();
        } catch (Exception e) {
            System.out.println("file HELP not found!");
        }
    }
}
