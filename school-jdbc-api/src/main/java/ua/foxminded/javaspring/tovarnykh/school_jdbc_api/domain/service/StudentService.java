package ua.foxminded.javaspring.tovarnykh.school_jdbc_api.domain.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import ua.foxminded.javaspring.tovarnykh.school_jdbc_api.dao.StudentDao;
import ua.foxminded.javaspring.tovarnykh.school_jdbc_api.dao.entity.Student;
import ua.foxminded.javaspring.tovarnykh.school_jdbc_api.domain.generator.Generator;

@Service
public class StudentService {

    private static final String MESSAGE_POPULATE_EXCEPTION = "Error: Problem with populating students,"
            + " try to populate groups first";
    private static final String MESSAGE_GET_EXCEPTION = "Error: Problem with receiving student";
    private static final String MESSAGE_ADD_EXCEPTION = "Error: Problem with adding studet";
    private static final String MESSAGE_UPDATE_EXCEPTION = "Error: Problem with updating student";
    private static final String MESSAGE_DELETE_EXCEPTION = "Error: No such student to delete";

    private StudentDao studentDao;
    private Generator<Student> generator;

    public StudentService(StudentDao studentDao, @Qualifier("studentGenerator") Generator<Student> generator) {
        this.studentDao = studentDao;
        this.generator = generator;
    }

    public void generateData() {
        try {
            List<Student> students = generator.generate();
            studentDao.addAll(students);
        } catch (DataIntegrityViolationException e) {
            System.out.println(MESSAGE_POPULATE_EXCEPTION);
        }
    }

    public void add(int groupId, String firstName, String lastName) {
        try {
            studentDao.add(new Student(groupId, firstName, lastName));
        } catch (DataIntegrityViolationException e) {
            System.out.println(MESSAGE_ADD_EXCEPTION);
        }
    }

    public Student get(int studentId) {
        try {
            return studentDao.read(studentId);
        } catch (EmptyResultDataAccessException e) {
            System.out.println(MESSAGE_GET_EXCEPTION);
            return new Student();
        }
    }

    public List<Student> getAll() {
        try {
            return studentDao.readAll();
        } catch (EmptyResultDataAccessException e) {
            System.out.println(MESSAGE_GET_EXCEPTION);
            return List.of();
        }
    }

    public void update(int studentId, int groupId, String firstName, String lastName) {
        try {
            studentDao.update(new Student(studentId, groupId, firstName, lastName));
        } catch (DataIntegrityViolationException e) {
            System.out.println(MESSAGE_UPDATE_EXCEPTION);
        }
    }

    public void delete(int studentId) {
        try {
            Student student = studentDao.read(studentId);
            studentDao.delete(student);
        } catch (EmptyResultDataAccessException e) {
            System.out.println(MESSAGE_DELETE_EXCEPTION);
        }
    }

}