package ua.foxminded.javaspring.tovarnykh.school_jdbc_api.dao;

import java.util.List;

import ua.foxminded.javaspring.tovarnykh.school_jdbc_api.dao.entity.Group;

public interface GroupDao extends Dao<Group> {
    
    List<Group> getGroupsWithLessEqualsStudentCount(int studentCount);

}