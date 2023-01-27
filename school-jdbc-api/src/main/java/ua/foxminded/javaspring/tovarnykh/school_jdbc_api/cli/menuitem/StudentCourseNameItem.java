package ua.foxminded.javaspring.tovarnykh.school_jdbc_api.cli.menuitem;

import java.util.List;

import org.springframework.stereotype.Component;

import ua.foxminded.javaspring.tovarnykh.school_jdbc_api.cli.CommandLineInterface;
import ua.foxminded.javaspring.tovarnykh.school_jdbc_api.dao.entity.Course;
import ua.foxminded.javaspring.tovarnykh.school_jdbc_api.dao.entity.Student;
import ua.foxminded.javaspring.tovarnykh.school_jdbc_api.domain.service.CourseService;
import ua.foxminded.javaspring.tovarnykh.school_jdbc_api.domain.service.StudentCourseService;

@Component
public class StudentCourseNameItem extends CommandLineInterface implements Item {
    
    private static final String STUDENTS_HEAD_SECTION = """
            ╔════════════════════════════════════════╗
            ║                 Students               ║
            ╟────────────────────────────────────────╢
            """;
    private static final String STUDENT_COURSE_FORMAT = " %-19s | %5s %n";
    
    private final String itemName;
    
    private CourseService courseService;
    private StudentCourseService studentCourseService;
    
    StudentCourseNameItem(CourseService courseService, StudentCourseService studentCourseService) {
        this.courseService = courseService;
        this.studentCourseService = studentCourseService;
        this.itemName = "Get Student by Course Name";
    }
 
    @Override
    public void draw() {
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
            students.forEach(student -> System.out.printf(STUDENT_COURSE_FORMAT, student.getGroupId(),
                    student.getFirstName()+ " " + student.getLastName()));
        }
        closeSection();
    }

    @Override
    public String getName() {
        return String.valueOf(itemName);
    }

}