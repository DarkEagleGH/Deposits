package Helpers;

import java.io.FileReader;

public class FileReaderForHelp {

    public static void ReadHelp() {
        try (FileReader fileReader = new FileReader("help.txt")) {
            int c;
            while ((c = fileReader.read()) != -1) System.out.print((char) c);
            System.out.println();
        } catch (Exception e) {
            System.out.println("HELP file not found!");
        }
    }
}

