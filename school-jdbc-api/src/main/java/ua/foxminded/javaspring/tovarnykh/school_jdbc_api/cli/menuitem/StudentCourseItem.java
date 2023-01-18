package ua.foxminded.javaspring.tovarnykh.school_jdbc_api.cli.menuitem;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import ua.foxminded.javaspring.tovarnykh.school_jdbc_api.cli.CommandLineInterface;
import ua.foxminded.javaspring.tovarnykh.school_jdbc_api.domain.service.StudentCourseService;
import ua.foxminded.javaspring.tovarnykh.school_jdbc_api.entity.Student;

@Component
public class StudentCourseItem extends CommandLineInterface implements Item {

    private static final String MENU_ITEM = """
            ╔═════════════════════════════════════════╗
            ║ Choose command                          ║
            ╠─────────────────────────────────────────╣
            ║                                         ║
            ║   1 - Auto enroll students to courses   ║
            ║                                         ║
            ║   2 - Enroll Student to Course          ║
            ║                                         ║
            ║   3 - Get Students By Course Name       ║
            ║                                         ║
            ║   4 - Expel Student                     ║
            ║                                         ║
            ║   other number - to went Back           ║
            ║                                         ║
            ╚═════════════════════════════════════════╝
            """;

    @Autowired
    private StudentCourseService studentCourseService;

    @Override
    public void draw() {
        System.out.println(MENU_ITEM);
        int choice = readNumber();

        if (choice == 1) {
            studentCourseService.generateData();
        } else if (choice == 2) {
            enrollSection();
        } else if (choice == 3) {
            getStudentsByCourseName();
        } else if (choice == 4) {
            expelSection();
        }
    }

    private void enrollSection() {
        System.out.println("Please, enter studentId, then courseId:");
        int studentId = readNumber();
        int courseId = readNumber();

        studentCourseService.enrollStudent(studentId, courseId);
    }

    private void getStudentsByCourseName() {
        System.out.println("Please, enter course name:");
        String courseName = readLine();

        System.out.print("""
                ╔════════════════════════════════════════╗
                ║                 Students               ║
                ╟────────────────────────────────────────╢
                 in:
                 """);
        List<Student> students = studentCourseService.getStudentsByCourseName(courseName);
        
        System.out.printf(" %14s | %s %n", "groupId", "Full Name");
        System.out.println(DELIMITER);
        if (!students.isEmpty()) {
            students.forEach(student -> System.out.printf(" %14d | %s %s %n", 
                    student.getGroupId(),
                    student.getFirstName(), 
                    student.getLastName()));
        }
        closeSection();
    }

    private void expelSection() {
        System.out.println("Please, enter studentId, then courseId:");
        int studentId = readNumber();
        int courseId = readNumber();

        studentCourseService.expelStudent(studentId, courseId);
    }

}