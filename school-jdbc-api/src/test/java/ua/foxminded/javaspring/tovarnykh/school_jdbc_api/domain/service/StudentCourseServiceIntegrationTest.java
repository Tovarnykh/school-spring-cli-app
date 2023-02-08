package ua.foxminded.javaspring.tovarnykh.school_jdbc_api.domain.service;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import ua.foxminded.javaspring.tovarnykh.school_jdbc_api.IntegrationTest;
import ua.foxminded.javaspring.tovarnykh.school_jdbc_api.dao.StudentCourseDao;
import ua.foxminded.javaspring.tovarnykh.school_jdbc_api.dao.entity.Student;
import ua.foxminded.javaspring.tovarnykh.school_jdbc_api.dao.entity.StudentCourse;

@SpringBootTest
@ActiveProfiles("test")
class StudentCourseServiceIntegrationTest extends IntegrationTest {

    @Mock
    private StudentCourseDao studentCourseDao;
    
    @InjectMocks
    private StudentCourseService studentCourseService;

    @Test
    void enrollStudent_CheckIsStudentCourseCourseAdd_True() {
        studentCourseService.enrollStudent(1, 1);

        verify(studentCourseDao, times(1)).add(1, 1);
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