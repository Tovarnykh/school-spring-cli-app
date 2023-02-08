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
import ua.foxminded.javaspring.tovarnykh.school_jdbc_api.dao.GroupDao;
import ua.foxminded.javaspring.tovarnykh.school_jdbc_api.dao.entity.Group;

@SpringBootTest
class GroupServiceIntegrationTest extends IntegrationTest {
    
    @Mock
    private GroupDao groupDao;
    
    @InjectMocks
    private GroupService groupService;

    @Test
    void add_CheckIsGroupAdd_True() {
        groupService.add("tt-00");

        verify(groupDao, times(1)).add(new Group("tt-00"));
    }

    @Test
    void get_CanGetGroup_True() {
        when(groupDao.read(1)).thenReturn(new Group("tt-00"));
        
        Group groupDb = groupService.get(1);

        assertNotNull(groupDb);
        assertEquals("tt-00", groupDb.getName());
    }

    @Test
    void getAll_CheckCanGetAllList_True() {
        when(groupDao.readAll()).thenReturn(List.of(new Group("tt-00"), new Group("tt-01")));

        List<Group> group = groupService.getAll();

        assertNotNull(group);
        assertEquals(2, group.size());
    }

    @Test
    void update_IsRowUpdatedOnDb_False() {
        groupService.update(1, "tt-22");

        verify(groupDao).update(new Group(1, "tt-22"));
    }

    @Test
    void delete_IsRowDeleted_False() {
        when(groupDao.read(1)).thenReturn(new Group("tt-00"));
        groupService.delete(1);

        verify(groupDao).delete(new Group("tt-00"));
    }

}