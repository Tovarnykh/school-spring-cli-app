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

import ua.foxminded.javaspring.tovarnykh.school_jdbc_api.dao.entity.Group;

@SpringBootTest
@Testcontainers
@ActiveProfiles("test-containers")
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class JdbcGroupDaoIntegrationTest {

    private GroupDao groupDao;

    @Autowired
    JdbcGroupDaoIntegrationTest(GroupDao groupDao) {
        this.groupDao = groupDao;
    }

    @Test
    @Order(1)
    void add_CheckIsGroupSaved_True() {
        Group testGroup = new Group("test");

        groupDao.add(testGroup);

        Group groupDb = groupDao.read(1);
        assertNotNull(groupDb);
        assertEquals(testGroup.getName(), groupDb.getName());
    }

    @Test
    @Order(2)
    void addAll_CheckIsManyGroupsSaves_True() {
        List<Group> groups = List.of(new Group("te-11"), new Group("te-22"));

        groupDao.addAll(groups);

        List<Group> groupsDb = groupDao.readAll();
        assertNotNull(groupsDb);
        assertEquals(3, groupsDb.size());
    }

    @Test
    @Order(3)
    void read_CheckIsSuchGroupExist_True() {
        Group groupsDb = groupDao.read(1);

        assertNotNull(groupsDb);
        assertEquals("test", groupsDb.getName());
    }

    @Test
    @Order(4)
    void readAll_TryToResolveAllRows_True() {
        List<Group> groupsDb = groupDao.readAll();

        assertNotNull(groupsDb);
        assertEquals(3, groupsDb.size());
    }

    @Test
    @Order(5)
    void update_CheckIsRowUpdated_True() {
        Group testGroup = new Group(1, "tt-00");

        groupDao.update(testGroup);

        Group testGroupDb = groupDao.read(1);
        assertEquals(testGroup.getName(), testGroupDb.getName());
    }

    @Test
    @Order(6)
    void delete_IsRowDeleted_True() {
        Group testCourse = new Group(2, "lv-00");
        groupDao.delete(testCourse);

        assertThrows(EmptyResultDataAccessException.class, () -> groupDao.read(2));
    }

}