package ua.foxminded.javaspring.tovarnykh.school_jdbc_api.domain.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import ua.foxminded.javaspring.tovarnykh.school_jdbc_api.dao.GroupDao;
import ua.foxminded.javaspring.tovarnykh.school_jdbc_api.dao.entity.Group;
import ua.foxminded.javaspring.tovarnykh.school_jdbc_api.domain.generator.Generator;

@Service
public class GroupService {

    private static final String MESSAGE_POPULATE_EXCEPTION = "Error: Problem with populating groups";
    private static final String MESSAGE_GET_EXCEPTION = "Error: Problem with receiving group";
    private static final String MESSAGE_ADD_EXCEPTION = "Error: Problem with adding group";
    private static final String MESSAGE_UPDATE_EXCEPTION = "Error: Problem with updating group";

    private GroupDao groupDao;
    private Generator<Group> generator;

    public GroupService(GroupDao groupDao, @Qualifier("groupGenerator") Generator<Group> generator) {
        this.groupDao = groupDao;
        this.generator = generator;
    }

    public void generateData() {
        try {
            List<Group> students = generator.generate();
            groupDao.addAll(students);
        } catch (DataIntegrityViolationException e) {
            System.out.println(MESSAGE_POPULATE_EXCEPTION);
        }
    }

    public void add(String name) {
        try {
            groupDao.add(new Group(name));
        } catch (DataIntegrityViolationException e) {
            System.out.println(MESSAGE_ADD_EXCEPTION);
        }
    }

    public Group get(int groupId) {
        try {
            return groupDao.read(groupId);
        } catch (EmptyResultDataAccessException e) {
            System.out.println(MESSAGE_GET_EXCEPTION);
            return new Group();
        }
    }

    public List<Group> getAll() {
        try {
            return groupDao.readAll();
        } catch (EmptyResultDataAccessException e) {
            System.out.println(MESSAGE_GET_EXCEPTION);
            return List.of();
        }
    }

    public void update(int groupId, String name) {
        try {
            groupDao.update(new Group(groupId, name));
        } catch (DataIntegrityViolationException e) {
            System.out.println(MESSAGE_UPDATE_EXCEPTION);
        }
    }

    public void delete(int groupId) {
        groupDao.delete(groupId);
    }

    public List<Group> getGroupsWithLessStudents(int numberOfStudents) {
        try {
            return groupDao.getGroupsWithLessEqualsStudentCount(numberOfStudents);
        } catch (EmptyResultDataAccessException e) {
            System.out.println(MESSAGE_GET_EXCEPTION);
            return List.of();
        }
    }

}