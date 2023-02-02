package ua.foxminded.javaspring.tovarnykh.school_jdbc_api.cli.menuitem;

import java.util.List;

import org.springframework.stereotype.Component;

import ua.foxminded.javaspring.tovarnykh.school_jdbc_api.cli.CommandLineInterface;
import ua.foxminded.javaspring.tovarnykh.school_jdbc_api.dao.entity.StudentCourse;
import ua.foxminded.javaspring.tovarnykh.school_jdbc_api.domain.service.StudentCourseService;

@Component
public class StudentCourseItem extends CommandLineInterface implements Item {

    private static final String MENU_ITEM = """
            ╔═════════════════════════════════════════╗
            ║ Choose command                          ║
            ╠─────────────────────────────────────────╣
            ║                                         ║
            ║   1 - Enroll Student to Course          ║
            ║                                         ║
            ║   2 - Print all enrolled students       ║
            ║                                         ║
            ║   3 - Expel Student                     ║
            ║                                         ║
            ║   other number - to went Back           ║
            ║                                         ║
            ╚═════════════════════════════════════════╝
            """;
    private static final String STUDENTS_HEAD_SECTION = """
            ╔════════════════════════════════════════╗
            ║                 Students               ║
            ╟────────────────────────────────────────╢
            """;
    private static final String STUDENT_COURSE_FORMAT = " %-19s | %5s %n";

    private final String itemName;
    
    private StudentCourseService studentCourseService;

    public StudentCourseItem(StudentCourseService studentCourseService) {
        this.studentCourseService = studentCourseService;
        itemName = "StudentCourse";
    }

    @Override
    public void draw() {
        System.out.println(MENU_ITEM);
        int choice = readNumber();

        if (choice == 1) {
            enrollSection();
        } else if (choice == 2) {
            getAllEnrolledStudents();
        } else if (choice == 3) {
            expelSection();
        }
    }

    @Override
    public String getName() {
        return itemName;
    }

    private void enrollSection() {
        System.out.println("Please, enter studentId, then courseId:");
        int studentId = readNumber();
        int courseId = readNumber();

        studentCourseService.enrollStudent(studentId, courseId);
    }

    private void getAllEnrolledStudents() {
        System.out.println(STUDENTS_HEAD_SECTION);
        List<StudentCourse> studentCourses = studentCourseService.getAllEnrolledStudents();

        System.out.printf(STUDENT_COURSE_FORMAT, "Full Name", "Group Name");
        System.out.println(DELIMITER);
        if (!studentCourses.isEmpty()) {
            studentCourses.forEach(entrolledStudent -> System.out.printf(STUDENT_COURSE_FORMAT,
                    entrolledStudent.getStudentFullName(), entrolledStudent.getCourseName()));
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