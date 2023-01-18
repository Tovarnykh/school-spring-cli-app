package ua.foxminded.javaspring.tovarnykh.school_jdbc_api.domain.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Component;

import ua.foxminded.javaspring.tovarnykh.school_jdbc_api.dao.GroupDao;
import ua.foxminded.javaspring.tovarnykh.school_jdbc_api.domain.generator.Generator;
import ua.foxminded.javaspring.tovarnykh.school_jdbc_api.entity.Group;
import ua.foxminded.javaspring.tovarnykh.school_jdbc_api.exception.DAOException;

@Component
public class GroupService {

    @Autowired
    private GroupDao groupDao;

    @Autowired
    @Qualifier("groupGenerator")
    private Generator<Group> generator;

    private static final String MESSAGE_GET_EXCEPTION = "Error: Problem with receiving group";
    private static final String MESSAGE_ADD_EXCEPTION = "Error: Problem with adding group";

    public void generateData() {
        try {
            if (groupDao.readAll().isEmpty()) {
                List<Group> students = generator.generate();
                groupDao.addAll(students);
            } else {
                System.out.println("Can`t generate data, table is not empty.");
            }
        } catch (DAOException e) {
            System.out.println(MESSAGE_GET_EXCEPTION);
        }
    }

    public void add(String name) {
        try {
            groupDao.add(new Group(name));
        } catch (Exception e) {
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

    public void update(int groupId, String name) {
        try {
            groupDao.update(new Group(groupId, name));
        } catch (Exception e) {
            System.out.println(MESSAGE_GET_EXCEPTION);
        }
    }

    public void delete(int groupId) {
        try {
            groupDao.delete(groupId);
        } catch (DAOException | EmptyResultDataAccessException e) {
            System.out.println(MESSAGE_GET_EXCEPTION);
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