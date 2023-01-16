package ua.foxminded.javaspring.tovarnykh.school_jdbc_api.dao;

import java.util.List;

import ua.foxminded.javaspring.tovarnykh.school_jdbc_api.entity.Group;
import ua.foxminded.javaspring.tovarnykh.school_jdbc_api.exception.DAOException;

public interface GroupDao extends Dao<Group> {
    
    List<Group> getGroupsWithLessEqualsStudentCount(int studentCount) throws DAOException;

}