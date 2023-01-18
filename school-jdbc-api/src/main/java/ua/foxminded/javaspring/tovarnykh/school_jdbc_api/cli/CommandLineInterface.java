package ua.foxminded.javaspring.tovarnykh.school_jdbc_api.cli;

import java.util.Scanner;

public abstract class CommandLineInterface {

    protected static Scanner scanner = new Scanner(System.in);
    
    protected static final String DELIMITER = " ----------------------------------------";

    public String readLine() {
        System.out.print(">");
        return scanner.nextLine();
    }

    public int readNumber() {
        String input;
        do {
            input = readLine();
        } while (input.chars().noneMatch(Character::isDigit));
        return Integer.parseInt(input);
    }

    public void closeSection() {
        System.out.println("""
                ╚════════════════════════════════════════╝
                Enter any key to continue...
                """);
        readLine();
    }

}