package ua.foxminded.javaspring.tovarnykh.school_jdbc_api.dao;

import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.*;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.test.context.ActiveProfiles;
import org.testcontainers.junit.jupiter.Testcontainers;

import ua.foxminded.javaspring.tovarnykh.school_jdbc_api.dao.entity.Course;

@SpringBootTest
@Testcontainers
@ActiveProfiles("test-containers")
class JdbcCourseDaoIntegrationTest {

    private CourseDao courseDao;
    
    JdbcCourseDaoIntegrationTest(CourseDao courseDao) {
        this.courseDao = courseDao;
    }
    
    @Test
    void add_CheckIsCourseSaved_True() {
        Course testCourse = new Course("Test");
        courseDao.add(testCourse);

        List<Course> coursesDB = courseDao.readAll();
        assertNotNull(coursesDB);
        assertTrue(coursesDB.size() > 0);
    }

    @Test
    void addAll_CheckIsManyCoursesSaves_True() {

        List<Course> courses = List.of(new Course("Math"), new Course("Art"));
        courseDao.addAll(courses);

        List<Course> coursesDB = courseDao.readAll();
        assertNotNull(coursesDB);
        assertTrue(coursesDB.size() >= 2);
    }

    @Test
    void read_CheckIsSuchCourseExist_True() {
        Course testCourse = new Course("Music");
        courseDao.add(testCourse);

        Course courseDb = courseDao.read(1);

        assertNotNull(courseDb);
        assertNotNull(courseDb.getName());
    }

    @Test
    void readAll_TryToResolveAllRows_True() {
        List<Course> courses = List.of(new Course("Health"), new Course("Dance"));
        courseDao.addAll(courses);
        List<Course> coursesDb = courseDao.readAll();

        assertNotNull(coursesDb);
        assertTrue(coursesDb.size() > 0);
    }

    @Test
    void update_CheckIsRowUpdated_True() {
        Course testCourse = new Course(1, "Robotics", "");
        Course testCourse2 = new Course(1, "Testing", "");

        courseDao.add(testCourse);

        courseDao.update(testCourse2);

        Course testCourseDb = courseDao.read(1);
        assertEquals(testCourse2.getName(), testCourseDb.getName());
    }

    @Test
    void delete_IsRowDeleted_True() {
        Course testCourse = new Course(2, "Cooking", "");
        courseDao.add(testCourse);

        courseDao.delete(testCourse);

        assertThrows(EmptyResultDataAccessException.class, () -> courseDao.read(2));
    }

}