package ua.foxminded.javaspring.tovarnykh.school_jdbc_api.dao;

import static org.junit.jupiter.api.Assertions.*;
import java.util.List;

import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.test.context.ActiveProfiles;
import org.testcontainers.junit.jupiter.Testcontainers;

import ua.foxminded.javaspring.tovarnykh.school_jdbc_api.dao.entity.Course;

@SpringBootTest
@Testcontainers
@ActiveProfiles("test-containers")
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class JdbcCourseDaoIntegrationTest {

    private CourseDao courseDao;

    @Autowired
    JdbcCourseDaoIntegrationTest(CourseDao courseDao) {
        this.courseDao = courseDao;
    }

    @Test
    @Order(1)
    void add_CheckIsCourseSaved_True() {
        Course testCourse = new Course("Test");
        courseDao.add(testCourse);

        Course courseDB = courseDao.read(1);

        assertNotNull(courseDB);
        assertEquals(testCourse.getName(), courseDB.getName());
    }

    @Test
    @Order(2)
    void addAll_CheckIsManyCoursesSaves_True() {
        List<Course> courses = List.of(new Course("Test2"), new Course("Test3"));
        courseDao.addAll(courses);

        List<Course> coursesDB = courseDao.readAll();

        assertNotNull(coursesDB);
        assertEquals(3, coursesDB.size());
    }

    @Test
    @Order(3)
    void read_CheckIsSuchCourseExist_True() {
        Course courseDb = courseDao.read(2);

        assertNotNull(courseDb);
        assertEquals("Test2", courseDb.getName());
    }

    @Test
    @Order(4)
    void readAll_TryToResolveAllRows_True() {
        List<Course> coursesDb = courseDao.readAll();

        assertNotNull(coursesDb);
        assertEquals(3, coursesDb.size());
    }

    @Test
    @Order(5)
    void update_CheckIsRowUpdated_True() {
        Course testCourse = new Course(1, "Test1", "");

        courseDao.update(testCourse);

        Course testCourseDb = courseDao.read(1);
        assertEquals(testCourse.getName(), testCourseDb.getName());
    }

    @Test
    @Order(6)
    void delete_IsRowDeleted_True() {
        Course courseDb = courseDao.read(3);

        courseDao.delete(courseDb);

        assertThrows(EmptyResultDataAccessException.class, () -> courseDao.read(3));
    }

}