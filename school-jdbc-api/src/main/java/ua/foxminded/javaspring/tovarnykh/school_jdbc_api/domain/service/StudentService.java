package ua.foxminded.javaspring.tovarnykh.school_jdbc_api.domain.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import ua.foxminded.javaspring.tovarnykh.school_jdbc_api.dao.StudentDao;
import ua.foxminded.javaspring.tovarnykh.school_jdbc_api.domain.generator.Generator;
import ua.foxminded.javaspring.tovarnykh.school_jdbc_api.entity.Student;
import ua.foxminded.javaspring.tovarnykh.school_jdbc_api.exception.DAOException;

@Service
public class StudentService {

    private static final String MESSAGE_POPULATE_EXCEPTION = "Error: Problem with populating students";
    private static final String MESSAGE_GET_EXCEPTION = "Error: Problem with receiving student";
    private static final String MESSAGE_ADD_EXCEPTION = "Error: Problem with adding studet";
    private static final String MESSAGE_UPDATE_EXCEPTION = "Error: Problem with updating student";
    private static final String MESSAGE_DELETE_EXCEPTION = "Error: Problem with deleting student";
    private static final String MESSAGE_TABLE_NOT_EMPTY = "Can`t generate data, table is not empty";

    @Autowired
    private StudentDao studentDao;

    @Autowired
    @Qualifier("studentGenerator")
    private Generator<Student> generator;

    public void generateData() {
        try {
            if (studentDao.readAll().isEmpty()) {
                List<Student> students = generator.generate();
                studentDao.addAll(students);
            } else {
                System.out.println(MESSAGE_TABLE_NOT_EMPTY);
            }
        } catch (DAOException e) {
            System.out.println(MESSAGE_POPULATE_EXCEPTION);
        }
    }

    public void add(int groupId, String firstName, String lastName) {
        try {
            studentDao.add(new Student(groupId, firstName, lastName));
        } catch (DAOException | DataIntegrityViolationException e) {
            System.out.println(MESSAGE_ADD_EXCEPTION);
        }
    }

    public Student get(int studentId) {
        try {
            return studentDao.read(studentId);
        } catch (DAOException | EmptyResultDataAccessException e) {
            System.out.println(MESSAGE_GET_EXCEPTION);
            return new Student();
        }
    }

    public void update(int studentId, int groupId, String firstName, String lastName) {
        try {
            studentDao.update(new Student(studentId, groupId, firstName, lastName));
        } catch (DAOException | DataIntegrityViolationException e) {
            System.out.println(MESSAGE_UPDATE_EXCEPTION);
        }
    }

    public void delete(int studentId) {
        try {
            studentDao.delete(studentId);
        } catch (DAOException e) {
            System.out.println(MESSAGE_DELETE_EXCEPTION);
        }
    }

}