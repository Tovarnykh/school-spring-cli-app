package ua.foxminded.javaspring.tovarnykh.school_jdbc_api.dao;

import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.*;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.test.context.ActiveProfiles;
import org.testcontainers.junit.jupiter.Testcontainers;

import ua.foxminded.javaspring.tovarnykh.school_jdbc_api.dao.entity.Group;

@SpringBootTest
@Testcontainers
@ActiveProfiles("test-containers")
class JdbcGroupDaoIntegrationTest {

    @Autowired
    private GroupDao groupDao;

    @Test
    void add_CheckIsGroupSaved_True() {
        Group testGroup = new Group("test");
        groupDao.add(testGroup);

        Group groupDb = groupDao.read(1);
        assertNotNull(groupDb);
    }

    @Test
    void addAll_CheckIsManyGroupsSaves_True() {

        List<Group> groups = List.of(new Group("te-11"), new Group("te-22"));
        groupDao.addAll(groups);

        List<Group> groupsDb = groupDao.readAll();
        assertNotNull(groupsDb);
        assertTrue(groupsDb.size() >= 2);
    }

    @Test
    void read_CheckIsSuchGroupExist_True() {
        Group testGroup = new Group("fw-52");
        groupDao.add(testGroup);

        Group groupsDb = groupDao.read(1);

        assertNotNull(groupsDb);
    }

    @Test
    void readAll_TryToResolveAllRows_True() {
        List<Group> groups = List.of(new Group("be-42"), new Group("qq-37"));
        groupDao.addAll(groups);
        List<Group> groupsDb = groupDao.readAll();

        assertNotNull(groupsDb);
        assertTrue(groupsDb.size() > 0);
    }

    @Test
    void update_CheckIsRowUpdated_True() {
        Group testGroup = new Group(1, "pp-93");
        Group testGroup2 = new Group(1, "mr-69");

        groupDao.add(testGroup);

        groupDao.update(testGroup2);

        Group testGroupDb = groupDao.read(1);
        assertEquals(testGroup2.getName(), testGroupDb.getName());
    }

    @Test
    void delete_IsRowDeleted_True() {
        Group testCourse = new Group(2, "lv-00");
        groupDao.add(testCourse);

        groupDao.delete(testCourse);

        assertThrows(EmptyResultDataAccessException.class, () -> groupDao.read(2));
    }

}