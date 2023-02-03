package ua.foxminded.javaspring.tovarnykh.school_jdbc_api.domain.service;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import ua.foxminded.javaspring.tovarnykh.school_jdbc_api.dao.CourseDao;
import ua.foxminded.javaspring.tovarnykh.school_jdbc_api.dao.entity.Course;

@ActiveProfiles("test")
@SpringBootTest
class CourseServiceIntegrationTest {

    private CourseService coursService;
    private CourseDao courseDao;

    @Autowired
    CourseServiceIntegrationTest(CourseService coursService, CourseDao courseDao) {
        this.coursService = coursService;
        this.courseDao = courseDao;
    }

    @Test
    void add_CheckIsCourseAdd_True() {
        coursService.add("Health", "");

        verify(courseDao).add(new Course(0, "Health", ""));
    }

    @Test
    void get_CanGetCourse_True() {
        when(courseDao.read(1)).thenReturn(new Course("MockedCourse"));

        Course courseDb = coursService.get(1);

        assertNotNull(courseDb);
        assertEquals("MockedCourse", courseDb.getName());
    }

    @Test
    void getAll_CheckCanGetAllList_True() {
        when(courseDao.readAll()).thenReturn(List.of(new Course("Health"), new Course("Art")));

        List<Course> courses = coursService.getAll();

        assertNotNull(courses);
        assertEquals(2, courses.size());
    }

    @Test
    void update_IsRowUpdated_True() {
        coursService.update(1, "Health", "");

        verify(courseDao).update(new Course(1, "Health", ""));
    }

    @Test
    void delete_IsRowDeleted_False() {
        when(courseDao.read(1)).thenReturn(new Course("Art"));
        coursService.delete(1);

        verify(courseDao).delete(new Course("Art"));
    }

}