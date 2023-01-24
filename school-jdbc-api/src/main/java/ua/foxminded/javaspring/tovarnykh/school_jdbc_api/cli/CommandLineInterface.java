package ua.foxminded.javaspring.tovarnykh.school_jdbc_api.cli;

import java.util.Scanner;

public class CommandLineInterface {
    
    protected static final String DELIMITER = " ----------------------------------------";
    protected static final String MENU_CLOSE_SECTION = "╚═════════════════════════════════════════╝";

    protected static Scanner scanner = new Scanner(System.in);

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