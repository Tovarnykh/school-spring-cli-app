package ua.foxminded.javaspring.tovarnykh.school_jdbc_api.domain.service;

import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.*;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.testcontainers.junit.jupiter.Testcontainers;

import ua.foxminded.javaspring.tovarnykh.school_jdbc_api.dao.entity.Group;

@SpringBootTest
@Testcontainers
@ActiveProfiles("test-containers")
class GroupServiceIT {

    @Autowired
    private GroupService groupService;

    @Test
    void add_CheckIsGroupAdd_True() {
        groupService.add("ge-32");

        Group groupDb = groupService.get(1);

        assertNotNull(groupDb);
        assertEquals("ge-32", groupDb.getName());
    }

    @Test
    void get_CanGetGroup_True() {
        groupService.add("bw-61");

        Group groupDb = groupService.get(1);

        assertNotNull(groupDb);
        assertFalse(groupDb.getName().isEmpty());
    }

    @Test
    void getAll_CheckCanGetAllList_True() {
        groupService.add("mw-00");

        List<Group> group = groupService.getAll();

        assertNotNull(group);
        assertTrue(group.size() > 0);
    }

    @Test
    void update_IsRowUpdatedOnDb_False() {
        groupService.add("tt-11");

        groupService.update(1, "tt-22");

        Group groupDb = groupService.get(1);
        assertNotEquals("tt-11", groupDb.getName());
    }

    @Test
    void delete_IsRowDeleted_False() {
        groupService.add("de-99");

        groupService.delete(1);

        Group groupDb = groupService.get(1);
        assertNotEquals("de-99", groupDb.getName());
    }

}