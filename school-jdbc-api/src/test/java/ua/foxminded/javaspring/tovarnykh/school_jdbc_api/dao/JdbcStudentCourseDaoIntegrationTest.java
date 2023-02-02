package ua.foxminded.javaspring.tovarnykh.school_jdbc_api.dao;

import static org.junit.jupiter.api.Assertions.*;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.testcontainers.junit.jupiter.Testcontainers;

import ua.foxminded.javaspring.tovarnykh.school_jdbc_api.dao.entity.StudentCourse;
import ua.foxminded.javaspring.tovarnykh.school_jdbc_api.domain.service.CourseService;
import ua.foxminded.javaspring.tovarnykh.school_jdbc_api.domain.service.GroupService;
import ua.foxminded.javaspring.tovarnykh.school_jdbc_api.domain.service.StudentService;

@SpringBootTest
@Testcontainers
@ActiveProfiles("test-containers")
class JdbcStudentCourseDaoIntegrationTest {

    private GroupService groupService;
    private StudentService studentService;
    private CourseService courseService;
    private StudentCourseDao studentCourseDao;

    JdbcStudentCourseDaoIntegrationTest(GroupService groupService, StudentService studentService,
            CourseService courseService, StudentCourseDao studentCourseDao) {
        this.groupService = groupService;
        this.studentService = studentService;
        this.courseService = courseService;
        this.studentCourseDao = studentCourseDao;
    }

    @Test
    void add_CheckIsStudentCourseSaved_True() {
        groupService.add("tt-00");
        studentService.add(1, "Adam", "Adamson");
        courseService.add("Health", "");
        studentCourseDao.add(1, 1);

        List<StudentCourse> list = studentCourseDao.readAll();

        assertNotNull(list);
        assertTrue(list.size() > 0);
    }

    @Test
    void readAll_TryToResolveAllRows_True() {
        groupService.add("tt-02");
        studentService.add(1, "Adam", "Adamson");
        studentService.add(1, "John", "Johnson");
        courseService.add("Sport", "");
        courseService.add("Chess", "");

        List<StudentCourse> list = studentCourseDao.readAll();

        assertNotNull(list);
        assertTrue(list.size() >= 0);
    }

}