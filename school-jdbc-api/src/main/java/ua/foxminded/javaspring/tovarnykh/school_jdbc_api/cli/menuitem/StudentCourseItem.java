package ua.foxminded.javaspring.tovarnykh.school_jdbc_api.cli.menuitem;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import ua.foxminded.javaspring.tovarnykh.school_jdbc_api.cli.CommandLineInterface;
import ua.foxminded.javaspring.tovarnykh.school_jdbc_api.domain.service.CourseService;
import ua.foxminded.javaspring.tovarnykh.school_jdbc_api.domain.service.StudentCourseService;
import ua.foxminded.javaspring.tovarnykh.school_jdbc_api.entity.Course;
import ua.foxminded.javaspring.tovarnykh.school_jdbc_api.entity.Student;
import ua.foxminded.javaspring.tovarnykh.school_jdbc_api.entity.StudentCourse;

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
            ║   3 - Get Students By Course Name       ║
            ║                                         ║
            ║   4 - Expel Student                     ║
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

    @Autowired
    private StudentCourseService studentCourseService;
    
    @Autowired
    private CourseService courseService;

    @Override
    public void draw() {
        System.out.println(MENU_ITEM);
        int choice = readNumber();

        if (choice == 1) {
            enrollSection();
        } else if (choice == 2) {
            getAllEnrolledStudents();
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

    private void getAllEnrolledStudents() {
        System.out.println(STUDENTS_HEAD_SECTION);
        List<StudentCourse> studentCourses = studentCourseService.getAllEnrolledStudents();
        
        System.out.printf(STUDENT_COURSE_FORMAT, "Full Name", "Group Name");
        System.out.println(DELIMITER);
        if (!studentCourses.isEmpty()) {
            studentCourses.forEach(entrolledStudent -> System.out.printf(STUDENT_COURSE_FORMAT, 
                    entrolledStudent.getStudentFullName(),
                    entrolledStudent.getCourseName()));
        }
        closeSection();
    }

    private void getStudentsByCourseName() {
        System.out.println("Please, select course from folowing:");
        List<Course> courses = courseService.getAll();
        
        courses.forEach(course -> {
            System.out.println(course.getName());
        });
        
        String courseName = readLine();

        System.out.print(STUDENTS_HEAD_SECTION);
        List<Student> students = studentCourseService.getStudentsByCourseName(courseName);

        System.out.printf(STUDENT_COURSE_FORMAT, "groupId", "Full Name");
        System.out.println(DELIMITER);
        if (!students.isEmpty()) {
            students.forEach(student -> System.out.printf(STUDENT_COURSE_FORMAT, 
                    student.getGroupId(),
                    student.getFirstName(), student.getLastName()));
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