package ua.foxminded.javaspring.tovarnykh.school_jdbc_api.domain.service;

import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.*;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.testcontainers.junit.jupiter.Testcontainers;

import ua.foxminded.javaspring.tovarnykh.school_jdbc_api.dao.entity.Student;

@SpringBootTest
@Testcontainers
@ActiveProfiles("test-containers")
class StudentServiceIntegrationTest {

    @Autowired
    private StudentService studentService;

    @Autowired
    private GroupService groupService;

    @Test
    void add_CheckIsStudentAdd_True() {
        groupService.add("ge-32");
        studentService.add(1, "Adam", "Adamson");

        Student studentDb = studentService.get(1);

        assertNotNull(studentDb);
        assertNotNull(studentDb.getFirstName());
    }

    @Test
    void get_CanGetStudent_True() {
        groupService.add("ve-12");
        studentService.add(1, "John", "Adamson");

        Student studentDb = studentService.get(1);

        assertNotNull(studentDb);
        assertFalse(studentDb.getFirstName().isEmpty());
    }

    @Test
    void getAll_CheckCanGetAllList_True() {
        groupService.add("qw-98");
        studentService.add(1, "Adam", "Johnson");

        List<Student> students = studentService.getAll();

        assertNotNull(students);
        assertTrue(students.size() > 0);
    }

    @Test
    void update_IsRowUpdatedOnDb_False() {
        groupService.add("bv-11");
        studentService.add(1, "Test", "Tester");

        studentService.update(1, 1, "Robert", "Martin");

        Student studentDb = studentService.get(1);
        assertNotEquals("Test", studentDb.getFirstName());
    }

    @Test
    void delete_IsRowDeleted_True() {
        groupService.add("dd-00");
        studentService.add(1, "ToBe", "Deleted");

        groupService.delete(1);

        Student studentDb = studentService.get(1);
        assertNotEquals("Deleted", studentDb.getLastName());
    }

}
