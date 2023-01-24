package ua.foxminded.javaspring.tovarnykh.school_jdbc_api.cli;

import java.util.LinkedHashMap;
import java.util.Map;

import org.springframework.stereotype.Component;

import ua.foxminded.javaspring.tovarnykh.school_jdbc_api.cli.menuitem.CourseMenuItem;
import ua.foxminded.javaspring.tovarnykh.school_jdbc_api.cli.menuitem.GroupMenuItem;
import ua.foxminded.javaspring.tovarnykh.school_jdbc_api.cli.menuitem.StudentCourseItem;
import ua.foxminded.javaspring.tovarnykh.school_jdbc_api.cli.menuitem.StudentMenuItem;
import ua.foxminded.javaspring.tovarnykh.school_jdbc_api.cli.menuitem.Item;

@Component
public class Menu extends CommandLineInterface {

    private static final String EXIT = "exit";
    private static final String MAIN_MENU_HEAD_SECTION = """
            ╔═════════════════════════════════════════╗
            ║ Welcome To School Console Application!  ║
            ║       Choose which Item to Interact     ║
            ╠─────────────────────────────────────────╣""";
    private static final String MENU_FORMAT = "║%5s - %-33s║%n";
    private static final String EXCEPTION_MESSAGE = "Such command not provided, try agan";

    private StudentMenuItem studentMenuItem;
    private CourseMenuItem courseMenuItem;
    private GroupMenuItem groupMenuItem;
    private StudentCourseItem studentCourseItem;
    
    private Map<Integer, Item> menuItems;

    public Menu(StudentMenuItem studentMenuItem,
            CourseMenuItem courseMenuItem, GroupMenuItem groupMenuItem,
            StudentCourseItem studentCourseItem) {
        this.studentMenuItem = studentMenuItem;
        this.courseMenuItem = courseMenuItem;
        this.groupMenuItem = groupMenuItem;
        this.studentCourseItem = studentCourseItem;
        this.menuItems = new LinkedHashMap<>();
        populateMenuItems();
    }

    public void initMenu() {
        String choice = "";

        do {
            printMenu();
            try {
                choice = readLine();
                if ((choice.chars().allMatch(Character::isDigit)) && (!choice.isEmpty())) {
                    int option = Integer.parseInt(choice);

                    menuItems.get(option).draw();
                } else if (EXIT.equalsIgnoreCase(choice)) {
                    break;
                }
            } catch (NumberFormatException | NullPointerException e) {
                System.out.println(EXCEPTION_MESSAGE);
            }
        } while (!EXIT.equalsIgnoreCase(choice));

        scanner.close();
    }

    private void populateMenuItems() {
        menuItems.put(1, groupMenuItem);
        menuItems.put(2, studentMenuItem);
        menuItems.put(3, courseMenuItem);
        menuItems.put(4, studentCourseItem);
    }

    private void printMenu() {
        System.out.println(MAIN_MENU_HEAD_SECTION);

        menuItems.forEach((index, menuItem) -> {
            System.out.printf(MENU_FORMAT, index, menuItem.getName() + "Table");
        });
        System.out.printf(MENU_FORMAT, EXIT, "to Exit");
        System.out.println(MENU_CLOSE_SECTION);
    }

}