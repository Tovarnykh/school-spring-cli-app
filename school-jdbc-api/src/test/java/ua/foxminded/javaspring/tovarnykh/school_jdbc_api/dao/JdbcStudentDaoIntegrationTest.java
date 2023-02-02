package ua.foxminded.javaspring.tovarnykh.school_jdbc_api.dao;

import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.*;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.test.context.ActiveProfiles;
import org.testcontainers.junit.jupiter.Testcontainers;

import ua.foxminded.javaspring.tovarnykh.school_jdbc_api.dao.entity.Group;
import ua.foxminded.javaspring.tovarnykh.school_jdbc_api.dao.entity.Student;

@SpringBootTest
@Testcontainers
@ActiveProfiles("test-containers")
class JdbcStudentDaoIntegrationTest {

    private StudentDao studentDao;
    private GroupDao groupDao;
    
    JdbcStudentDaoIntegrationTest(StudentDao studentDao, GroupDao groupDao) {
        this.studentDao = studentDao;
        this.groupDao = groupDao;
    }

    @Test
    void add_CheckIsStudentSaved_True() {
        List<Group> groups = List.of(new Group("ew-21"), new Group("bk-94"));
        groupDao.addAll(groups);
        Student testStudent = new Student(1, "Adam", "Adamson");
        studentDao.add(testStudent);

        Student studentDb = studentDao.read(1);
        assertNotNull(studentDb);
    }

    @Test
    void addAll_CheckIsManyStudentsSaves_True() {

        List<Student> students = List.of(new Student(1, "Ivan", "Ivanov"), new Student(1, "John", "Johnson"));
        studentDao.addAll(students);

        List<Student> studentsDb = studentDao.readAll();
        assertNotNull(studentsDb);
        assertTrue(studentsDb.size() >= 2);
    }

    @Test
    void read_CheckIsSuchStudentExist_True() {
        Student testStudent = new Student(1, "Ivan", "Adamson");
        studentDao.add(testStudent);

        Student studentsDb = studentDao.read(1);

        assertNotNull(studentsDb);
    }

    @Test
    void readAll_TryToResolveAllRows_True() {
        List<Student> students = List.of(new Student(1, "Theodor", "Miller"), new Student(1, "Robert", "Martin"));
        studentDao.addAll(students);
        List<Student> studentsDb = studentDao.readAll();

        assertNotNull(studentsDb);
        assertTrue(studentsDb.size() > 0);
    }

    @Test
    void update_CheckIsRowUpdated_True() {
        Student testStudent = new Student(1, 1, "Daniel", "Gerbertson");
        Student testStudent2 = new Student(1, 1, "Theodor", "Adamson");

        studentDao.add(testStudent);

        studentDao.update(testStudent2);

        Student testStudentDb = studentDao.read(1);
        assertEquals(testStudent2.getId(), testStudentDb.getId());
    }

    @Test
    void delete_IsRowDeleted_True() {
        Student testStudent = new Student(2, 1, "Daniel", "Ivanov");
        studentDao.add(testStudent);

        studentDao.delete(testStudent);

        assertThrows(EmptyResultDataAccessException.class, () -> studentDao.read(2));
    }

}