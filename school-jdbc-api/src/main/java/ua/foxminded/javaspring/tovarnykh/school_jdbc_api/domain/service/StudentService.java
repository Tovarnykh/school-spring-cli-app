package ua.foxminded.javaspring.tovarnykh.school_jdbc_api.domain.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import ua.foxminded.javaspring.tovarnykh.school_jdbc_api.dao.StudentDao;
import ua.foxminded.javaspring.tovarnykh.school_jdbc_api.domain.generator.Generator;
import ua.foxminded.javaspring.tovarnykh.school_jdbc_api.entity.Student;
import ua.foxminded.javaspring.tovarnykh.school_jdbc_api.exception.DAOException;

@Component
public class StudentService {

    @Autowired
    private StudentDao studentDao;

    @Autowired
    @Qualifier("studentGenerator")
    private Generator<Student> generator;

    private static final String MESSAGE_GET_EXCEPTION = "Error: Problem with receiving students";

    public void generateData() {
        try {
            if (studentDao.readAll().isEmpty()) {
                List<Student> students = generator.generate();
                studentDao.addAll(students);
            } else {
                System.out.println("Can`t generate data, table is not empty.");
            }
        } catch (DAOException e) {
            System.out.println(MESSAGE_GET_EXCEPTION);
        }
    }

    public void add(int groupId, String firstName, String lastName) {
        try {
            studentDao.add(new Student(groupId, firstName, lastName));
        } catch (DAOException e) {
            System.out.println(MESSAGE_GET_EXCEPTION);
        }
    }

    public Student get(int studentId) {
        try {
            return studentDao.read(studentId);
        } catch (DAOException e) {
            System.out.println(MESSAGE_GET_EXCEPTION);
            return new Student();
        }
    }

    public void update(int groupId, String firstName, String lastName) {
        try {
            studentDao.update(new Student(groupId, firstName, lastName));
        } catch (DAOException e) {
            System.out.println(MESSAGE_GET_EXCEPTION);
        }
    }

    public void delete(int studentId) {
        try {
            studentDao.delete(studentId);
        } catch (DAOException e) {
            System.out.println(MESSAGE_GET_EXCEPTION);
        }
    }

}