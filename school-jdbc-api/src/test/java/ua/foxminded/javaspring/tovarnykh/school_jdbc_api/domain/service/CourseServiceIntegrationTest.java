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
import ua.foxminded.javaspring.tovarnykh.school_jdbc_api.IntegrationTest;
import ua.foxminded.javaspring.tovarnykh.school_jdbc_api.dao.CourseDao;
import ua.foxminded.javaspring.tovarnykh.school_jdbc_api.dao.entity.Course;

@SpringBootTest
class CourseServiceIntegrationTest extends IntegrationTest {
    
    @Mock
    private CourseDao courseDao;
    
    @InjectMocks
    private CourseService coursService;

    @Test
    void add_CheckIsCourseAdd_True() {
        coursService.add("Health", "");

        verify(courseDao, times(1)).add(new Course(0, "Health", ""));
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