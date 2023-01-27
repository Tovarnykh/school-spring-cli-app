package ua.foxminded.javaspring.tovarnykh.school_jdbc_api.dao;

import static org.junit.jupiter.api.Assertions.*;

import java.util.List;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.ContextConfiguration;
import org.testcontainers.junit.jupiter.Testcontainers;

import ua.foxminded.javaspring.tovarnykh.school_jdbc_api.AbstractContainerBaseTest;
import ua.foxminded.javaspring.tovarnykh.school_jdbc_api.dao.entity.Course;

@Testcontainers
@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@ContextConfiguration(initializers = JdbcCourseDaoIntegrationTest.DataSourceInitializer.class)
class JdbcCourseDaoIntegrationTest extends AbstractContainerBaseTest {

    @Autowired
    private CourseDao courseDao;

    @DisplayName("JUnit test for save course operation")
    @Test
    void givenEmployeeObject_whenSave_thenReturnSavedEmployee() {

        courseDao.add(new Course("Test"));
        List<Course> course = courseDao.readAll();

        assertFalse(course.isEmpty());
    }

}