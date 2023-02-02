package ua.foxminded.javaspring.tovarnykh.school_jdbc_api.domain.service;

import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.*;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.testcontainers.junit.jupiter.Testcontainers;

import ua.foxminded.javaspring.tovarnykh.school_jdbc_api.dao.entity.Course;

@SpringBootTest
@Testcontainers
@ActiveProfiles("test-containers")
class CourseServiceIntegrationTest {

    private CourseService coursService;
    
    CourseServiceIntegrationTest(CourseService coursService) {
        this.coursService = coursService;
    }

    @Test
    void add_CheckIsCourseAdd_True() {
        coursService.add("Health", "");

        Course courseDb = coursService.get(1);

        assertNotNull(courseDb);
        assertNotNull(courseDb.getName());
    }

    @Test
    void get_CanGetCourse_True() {
        coursService.add("Math", "");

        Course courseDb = coursService.get(1);

        assertNotNull(courseDb);
        assertFalse(courseDb.getName().isEmpty());
    }

    @Test
    void getAll_CheckCanGetAllList_True() {
        coursService.add("Music", "");

        List<Course> courses = coursService.getAll();

        assertNotNull(courses);
        assertTrue(courses.size() > 0);
    }

    @Test
    void update_IsRowUpdatedOnDb_True() {
        coursService.add("Test", "");

        coursService.update(1, "RealCourse", "");

        Course courseDb = coursService.get(1);
        assertEquals("RealCourse", courseDb.getName());
    }

}