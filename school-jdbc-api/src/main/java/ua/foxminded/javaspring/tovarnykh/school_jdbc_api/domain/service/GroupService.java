package ua.foxminded.javaspring.tovarnykh.school_jdbc_api.domain.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import ua.foxminded.javaspring.tovarnykh.school_jdbc_api.dao.GroupDao;
import ua.foxminded.javaspring.tovarnykh.school_jdbc_api.domain.generator.Generator;
import ua.foxminded.javaspring.tovarnykh.school_jdbc_api.entity.Group;
import ua.foxminded.javaspring.tovarnykh.school_jdbc_api.exception.DAOException;

@Service
public class GroupService {

    private static final String MESSAGE_POPULATE_EXCEPTION = "Error: Problem with populating groups";
    private static final String MESSAGE_GET_EXCEPTION = "Error: Problem with receiving group";
    private static final String MESSAGE_ADD_EXCEPTION = "Error: Problem with adding group";
    private static final String MESSAGE_UPDATE_EXCEPTION = "Error: Problem with updating group";
    private static final String MESSAGE_DELETE_EXCEPTION = "Error: Problem with deleting group";

    @Autowired
    private GroupDao groupDao;

    @Autowired
    @Qualifier("groupGenerator")
    private Generator<Group> generator;

    public void generateData() {
        try {
                List<Group> students = generator.generate();
                groupDao.addAll(students);
        } catch (DAOException | DataIntegrityViolationException e) {
            System.out.println(MESSAGE_POPULATE_EXCEPTION);
        }
    }

    public void add(String name) {
        try {
            groupDao.add(new Group(name));
        } catch (DAOException | DataIntegrityViolationException e) {
            System.out.println(MESSAGE_ADD_EXCEPTION);
        }
    }

    public Group get(int groupId) {
        try {
            return groupDao.read(groupId);
        } catch (DAOException | EmptyResultDataAccessException e) {
            System.out.println(MESSAGE_GET_EXCEPTION);
            return new Group();
        }
    }

    public List<Group> getAll() {
        try {
            return groupDao.readAll();
        } catch (DAOException | EmptyResultDataAccessException e) {
            System.out.println(MESSAGE_GET_EXCEPTION);
            return List.of();
        }
    }

    public void update(int groupId, String name) {
        try {
            groupDao.update(new Group(groupId, name));
        } catch (DAOException | DataIntegrityViolationException e) {
            System.out.println(MESSAGE_UPDATE_EXCEPTION);
        }
    }

    public void delete(int groupId) {
        try {
            groupDao.delete(groupId);
        } catch (DAOException e) {
            System.out.println(MESSAGE_DELETE_EXCEPTION);
        }
    }

    public List<Group> getGroupsWithLessStudents(int numberOfStudents) {
        try {
            return groupDao.getGroupsWithLessEqualsStudentCount(numberOfStudents);
        } catch (DAOException | EmptyResultDataAccessException e) {
            System.out.println(MESSAGE_GET_EXCEPTION);
            return List.of();
        }
    }

}