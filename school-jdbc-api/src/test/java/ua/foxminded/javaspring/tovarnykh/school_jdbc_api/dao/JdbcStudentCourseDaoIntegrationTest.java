package ua.foxminded.javaspring.tovarnykh.school_jdbc_api.dao;

import static org.junit.jupiter.api.Assertions.*;

import java.util.List;

import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.testcontainers.junit.jupiter.Testcontainers;

import ua.foxminded.javaspring.tovarnykh.school_jdbc_api.dao.entity.Student;
import ua.foxminded.javaspring.tovarnykh.school_jdbc_api.dao.entity.StudentCourse;
import ua.foxminded.javaspring.tovarnykh.school_jdbc_api.domain.service.CourseService;
import ua.foxminded.javaspring.tovarnykh.school_jdbc_api.domain.service.GroupService;
import ua.foxminded.javaspring.tovarnykh.school_jdbc_api.domain.service.StudentService;

@SpringBootTest
@Testcontainers
@ActiveProfiles("test-containers")
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class JdbcStudentCourseDaoIntegrationTest {

    private GroupService groupService;
    private StudentService studentService;
    private CourseService courseService;
    private StudentCourseDao studentCourseDao;

    @Autowired
    JdbcStudentCourseDaoIntegrationTest(GroupService groupService, StudentService studentService,
            CourseService courseService, StudentCourseDao studentCourseDao) {
        this.groupService = groupService;
        this.studentService = studentService;
        this.courseService = courseService;
        this.studentCourseDao = studentCourseDao;
    }

    @Test
    @Order(1)
    void add_CheckIsStudentCourseSaved_True() {
        groupService.add("tt-00");
        studentService.add(1, "Adam", "Adamson");
        courseService.add("Health", "");
        studentCourseDao.add(1, 1);

        List<StudentCourse> studentCoursesDB = studentCourseDao.readAll();

        assertNotNull(studentCoursesDB);
        assertEquals(1, studentCoursesDB.size());
    }

    @Test
    @Order(2)
    void addAll_CanAddToDBMultiplyValues_True() {
        studentService.add(1, "Adam", "Adamson");
        courseService.add("Chess", "");
        List<StudentCourse> studentCourses = List.of(new StudentCourse(1, 2), new StudentCourse(2, 1));

        studentCourseDao.addAll(studentCourses);

        List<StudentCourse> studentCoursesDB = studentCourseDao.readAll();
        assertNotNull(studentCoursesDB);
        assertEquals(3, studentCoursesDB.size());
    }

    @Test
    @Order(3)
    void readAll_TryToResolveAllRows_True() {
        List<StudentCourse> studentCourseDb = studentCourseDao.readAll();

        assertNotNull(studentCourseDb);
        assertEquals(3, studentCourseDb.size());
    }

    @Test
    @Order(4)
    void getStudents_CanGetStudents_True() {
        List<Student> studentsDb = studentCourseDao.getStudents("Chess");

        assertNotNull(studentsDb);
    }

    @Test
    @Order(5)
    void delete_IsRowDeleted_True() {
        studentCourseDao.delete(1, 1);

        List<StudentCourse> studentCourseDb = studentCourseDao.readAll();
        assertTrue(studentCourseDb.size() < 3);
    }

}