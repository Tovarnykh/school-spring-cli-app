package ua.foxminded.javaspring.tovarnykh.school_jdbc_api.domain.service;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import ua.foxminded.javaspring.tovarnykh.school_jdbc_api.dao.StudentCourseDao;
import ua.foxminded.javaspring.tovarnykh.school_jdbc_api.dao.entity.Student;
import ua.foxminded.javaspring.tovarnykh.school_jdbc_api.dao.entity.StudentCourse;

@SpringBootTest
@ActiveProfiles("test")
class StudentCourseServiceIntegrationTest {

    private StudentCourseService studentCourseService;
    private StudentCourseDao studentCourseDao;

    @Autowired
    StudentCourseServiceIntegrationTest(StudentCourseService studentCourseService, StudentCourseDao studentCourseDao) {
        this.studentCourseService = studentCourseService;
        this.studentCourseDao = studentCourseDao;
    }

    @Test
    void enrollStudent_CheckIsStudentCourseCourseAdd_True() {
        studentCourseService.enrollStudent(1, 1);

        verify(studentCourseDao).add(1, 1);
    }

    @Test
    void getAllEnrolledStudents_CanReadAllRows_True() {
        when(studentCourseDao.readAll()).thenReturn(List.of(new StudentCourse(2, 1), new StudentCourse(1, 2)));

        List<StudentCourse> studentCourse = studentCourseService.getAllEnrolledStudents();
        
        assertNotNull(studentCourse);
        assertEquals(2, studentCourse.size());
    }

    @Test
    void getStudentsByCourseName_CanGetStudent_True() {
        when(studentCourseDao.getStudents("Art")).thenReturn(List.of(new Student(1, "Adam", "Adamson"), new Student(1, "John", "Johnson")));

        List<Student> students = studentCourseService.getStudentsByCourseName("Art");

        assertNotNull(students);
        assertEquals(2, students.size());
    }

    @Test
    void expelStudent_CheckIsStudentCourseCourseAdd_True() {
        studentCourseService.expelStudent(1, 1);

        verify(studentCourseDao).delete(1, 1);
    }

}