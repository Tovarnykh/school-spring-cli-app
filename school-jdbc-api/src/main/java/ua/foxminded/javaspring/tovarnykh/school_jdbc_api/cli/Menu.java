package ua.foxminded.javaspring.tovarnykh.school_jdbc_api.cli;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import ua.foxminded.javaspring.tovarnykh.school_jdbc_api.cli.menuitem.CourseMenuItem;
import ua.foxminded.javaspring.tovarnykh.school_jdbc_api.cli.menuitem.GroupMenuItem;
import ua.foxminded.javaspring.tovarnykh.school_jdbc_api.cli.menuitem.StudentCourseItem;
import ua.foxminded.javaspring.tovarnykh.school_jdbc_api.cli.menuitem.StudentMenuItem;

@Component
public class Menu extends CommandLineInterface {

    private static final String EXIT = "exit";
    private static final String MAIN_MENU = """
            ╔═════════════════════════════════════════╗
            ║ Welcome To School Console Application!  ║
            ║       Choose which Item to Interact     ║
            ╠─────────────────────────────────────────╣
            ║                                         ║
            ║   1 - Students Table                    ║
            ║                                         ║
            ║   2 - Courses Table                     ║
            ║                                         ║
            ║   3 - Groups Table                      ║
            ║                                         ║
            ║   4 - Students-Courses Table            ║
            ║                                         ║
            ║   exit - to Exit                        ║
            ║                                         ║
            ╚═════════════════════════════════════════╝
            """;

    @Autowired
    private StudentMenuItem studentMenuItem;

    @Autowired
    private CourseMenuItem courseMenuItem;

    @Autowired
    private GroupMenuItem groupMenuItem;
    
    @Autowired
    private StudentCourseItem studentCourseItem;

    public void initMenu() {
        String choice = "";

        do {
            System.out.println(MAIN_MENU);
            try {
                choice = readLine();
                if ((choice.chars().allMatch(Character::isDigit)) && (!choice.isEmpty())) {
                    int option = Integer.parseInt(choice);

                    if (option == 1) {
                        studentMenuItem.draw();
                    } else if (option == 2) {
                        courseMenuItem.draw();
                    } else if (option == 3) {
                        groupMenuItem.draw();
                    } else if (option == 4) {
                        studentCourseItem.draw();
                    }
                } else if (EXIT.equalsIgnoreCase(choice)) {
                    break;
                }
            } catch (NumberFormatException e) {
                System.out.println("Bad input");
            }
        } while (!EXIT.equalsIgnoreCase(choice));

        scanner.close();
    }

}