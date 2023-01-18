package ua.foxminded.javaspring.tovarnykh.school_jdbc_api.cli.menuitem;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import ua.foxminded.javaspring.tovarnykh.school_jdbc_api.cli.CommandLineInterface;
import ua.foxminded.javaspring.tovarnykh.school_jdbc_api.domain.service.CourseService;
import ua.foxminded.javaspring.tovarnykh.school_jdbc_api.entity.Course;

@Component
public class CourseMenuItem extends CommandLineInterface implements Item {

    private static final String MENU_ITEM = """
            ╔═════════════════════════════════════════╗
            ║ Choose command                          ║
            ╠─────────────────────────────────────────╣
            ║                                         ║
            ║   1 - Populate Course Table with        ║
            ║       random data                       ║
            ║                                         ║
            ║   2 - Add new Course to Table           ║
            ║                                         ║
            ║   3 - Get Course                        ║
            ║                                         ║
            ║   4 - Update Course                     ║
            ║                                         ║
            ║   5 - Delete Course                     ║
            ║                                         ║
            ║   other number - to went Back           ║
            ║                                         ║
            ╚═════════════════════════════════════════╝
            """;

    @Autowired
    private CourseService courseService;

    @Override
    public void draw() {
        System.out.println(MENU_ITEM);
        int choice = readNumber();

        if (choice == 1) {
            courseService.generateData();
        } else if (choice == 2) {
            addSection();
        } else if (choice == 3) {
            getSection();
        } else if (choice == 4) {
            updateSection();
        } else if (choice == 5) {
            deleteSection();
        }
    }

    private void addSection() {
        System.out.println("Please, enter new course name, then description:");
        String name = readLine();
        String description = readLine();

        courseService.add(name, description);
    }

    private void getSection() {
        System.out.println("Please, enter courseId:");
        int courseId = readNumber();

        System.out.print("""
                ╔════════════════════════════════════════╗
                ║                 Course                 ║
                ╟────────────────────────────────────────╢
                 in:
                 """);
        Course course = courseService.get(courseId);

        if (course.getId() != 0) {
            System.out.printf(" %18s | %s %n", "Id", "Name");
            System.out.println(DELIMITER);
            System.out.printf(" %18d | %s %n", course.getId(), course.getName());
        }
        closeSection();
    }

    private void updateSection() {
        System.out.println("Please, enter courseId, then course name, course description:");
        int courseId = readNumber();
        String name = readLine();
        String description = readLine();

        courseService.update(courseId, name, description);
    }

    private void deleteSection() {
        System.out.println("Please, enter courseId:");
        int courseId = readNumber();

        courseService.delete(courseId);
    }

}
