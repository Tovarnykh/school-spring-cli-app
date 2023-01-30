package ua.foxminded.javaspring.tovarnykh.school_jdbc_api.domain.service;

import static org.junit.jupiter.api.Assertions.*;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.testcontainers.junit.jupiter.Testcontainers;

import ua.foxminded.javaspring.tovarnykh.school_jdbc_api.dao.entity.Student;
import ua.foxminded.javaspring.tovarnykh.school_jdbc_api.dao.entity.StudentCourse;

@SpringBootTest
@Testcontainers
@ActiveProfiles("test-containers")
class StudentCourseServiceIntegrationTest {

    @Autowired
    private GroupService GroupService;

    @Autowired
    private CourseService CourseService;

    @Autowired
    private StudentService studentService;

    @Autowired
    private StudentCourseService studentCourseService;

    @Test
    void add_CheckIsStudentCourseCourseAdd_True() {
        GroupService.add("test");
        CourseService.add("Test", "");
        studentService.add(1, "Adam", "Adamson");
        studentCourseService.enrollStudent(1, 1);

        List<StudentCourse> studentCoursesDb = studentCourseService.getAllEnrolledStudents();

        assertNotNull(studentCoursesDb);
        assertTrue(studentCoursesDb.size() > 0);
    }
    
    @Test
    void getStudentsByCourseName_CanGetStudent_True() {
        GroupService.add("tt-00");
        CourseService.add("Math", "");
        studentService.add(1, "John", "Johnson");
        studentCourseService.enrollStudent(1, 1);

        List<Student> students = studentCourseService.getStudentsByCourseName("Math");

        assertNotNull(students);
    }

}