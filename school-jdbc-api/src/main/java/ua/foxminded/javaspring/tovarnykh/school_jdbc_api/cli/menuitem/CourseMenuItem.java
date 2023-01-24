package ua.foxminded.javaspring.tovarnykh.school_jdbc_api.cli.menuitem;

import java.util.List;
import org.springframework.stereotype.Component;

import ua.foxminded.javaspring.tovarnykh.school_jdbc_api.cli.CommandLineInterface;
import ua.foxminded.javaspring.tovarnykh.school_jdbc_api.dao.entity.Course;
import ua.foxminded.javaspring.tovarnykh.school_jdbc_api.domain.service.CourseService;

@Component
public class CourseMenuItem extends CommandLineInterface implements Item {

    private static final String MENU_ITEM = """
            ╔═════════════════════════════════════════╗
            ║ Choose command                          ║
            ╠─────────────────────────────────────────╣
            ║                                         ║
            ║   1 - Add new Course to Table           ║
            ║                                         ║
            ║   2 - Get Course                        ║
            ║                                         ║
            ║   3 - Get All Courses                   ║
            ║                                         ║
            ║   4 - Update Course                     ║
            ║                                         ║
            ║   5 - Delete Course                     ║
            ║                                         ║
            ║   other number - to went Back           ║
            ║                                         ║
            ╚═════════════════════════════════════════╝
            """;
    private static final String COURSE_FORMAT = " %12s | %s %n";

    private final String itemName;

    private CourseService courseService;

    public CourseMenuItem(CourseService courseService) {
        this.courseService = courseService;
        itemName = "Course";
    }

    @Override
    public void draw() {
        System.out.println(MENU_ITEM);
        int choice = readNumber();

        if (choice == 1) {
            addSection();
        } else if (choice == 2) {
            getSection();
        } else if (choice == 3) {
            getAllSection();
        } else if (choice == 4) {
            updateSection();
        } else if (choice == 5) {
            deleteSection();
        }
    }

    @Override
    public String getName() {
        return String.valueOf(itemName);
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
            System.out.printf(COURSE_FORMAT, "Id", "Name");
            System.out.println(DELIMITER);
            System.out.printf(COURSE_FORMAT, course.getId(), course.getName());
        }
        closeSection();
    }

    private void getAllSection() {
        System.out.print("""
                ╔════════════════════════════════════════╗
                ║                Courses                 ║
                ╟────────────────────────────────────────╢
                 """);
        System.out.printf(COURSE_FORMAT, "Id", "Name");
        System.out.println(DELIMITER);

        List<Course> courses = courseService.getAll();

        courses.forEach(course -> {
            System.out.printf(COURSE_FORMAT, course.getId(), course.getName());
        });

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
