package ua.foxminded.javaspring.tovarnykh.school_jdbc_api.domain.service;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import ua.foxminded.javaspring.tovarnykh.school_jdbc_api.IntegrationTest;
import ua.foxminded.javaspring.tovarnykh.school_jdbc_api.dao.StudentDao;
import ua.foxminded.javaspring.tovarnykh.school_jdbc_api.dao.entity.Student;

@SpringBootTest
class StudentServiceIntegrationTest extends IntegrationTest {

    @Mock
    private StudentDao studentDao;

    @InjectMocks
    private StudentService studentService;

    @Test
    void add_CheckIsStudentAdd_True() {
        studentService.add(1, "Adam", "Adamson");

        verify(studentDao, times(1)).add(new Student(1, "Adam", "Adamson"));
    }

    @Test
    void get_CanGetStudent_True() {
        when(studentDao.read(1)).thenReturn(new Student(1, "Adam", "Adamson"));

        Student studentDb = studentService.get(1);

        assertNotNull(studentDb);
        assertEquals("Adam" ,studentDb.getFirstName());
    }

    @Test
    void getAll_CheckCanGetAllList_True() {
        when(studentDao.readAll()).thenReturn(List.of(new Student(1, "Adam", "Adamson"), new Student(1, "John", "Johnson")));

        List<Student> students = studentService.getAll();

        assertNotNull(students);
        assertEquals(2, students.size());
    }

    @Test
    void update_IsRowUpdatedOnDb_False() {
        studentDao.update(new Student(1, "Adam", "Adamson"));

        verify(studentDao).update(new Student(1, "Adam", "Adamson"));
    }

    @Test
    void delete_IsRowDeleted_True() {
        when(studentDao.read(1)).thenReturn(new Student(1, "Adam", "Adamson"));
        
        studentService.delete(1);

        verify(studentDao).delete(new Student(1, "Adam", "Adamson"));
    }

}
