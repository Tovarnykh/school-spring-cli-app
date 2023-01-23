package ua.foxminded.javaspring.tovarnykh.school_jdbc_api.domain.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import ua.foxminded.javaspring.tovarnykh.school_jdbc_api.dao.StudentCourseDao;
import ua.foxminded.javaspring.tovarnykh.school_jdbc_api.domain.generator.Generator;
import ua.foxminded.javaspring.tovarnykh.school_jdbc_api.entity.Student;
import ua.foxminded.javaspring.tovarnykh.school_jdbc_api.entity.StudentCourse;
import ua.foxminded.javaspring.tovarnykh.school_jdbc_api.exception.DAOException;

@Service
public class StudentCourseService {

    @Autowired
    private StudentCourseDao studentCourseDao;

    @Autowired
    @Qualifier("studentCourseGenerator")
    private Generator<StudentCourse> generator;

    private static final String MESSAGE_POPULATE_EXCEPTION = "Error: Problem with populating student-courses";
    private static final String MESSAGE_GET_EXCEPTION = "Error: Problem with receiving student-course";
    private static final String MESSAGE_ADD_EXCEPTION = "Error: Problem with adding student-course";
    private static final String MESSAGE_DELETE_EXCEPTION = "Error: Problem with deleting student-course";

    public void generateData() {
        try {
                List<StudentCourse> studentCourse = generator.generate();
                studentCourseDao.addAll(studentCourse);
        } catch (DAOException | DataIntegrityViolationException e) {
            System.out.println(MESSAGE_POPULATE_EXCEPTION);
        }
    }

    public void enrollStudent(int studentId, int courseId) {
        try {
            studentCourseDao.add(studentId, courseId);
        } catch (DAOException | DataIntegrityViolationException e) {
            System.out.println(MESSAGE_ADD_EXCEPTION);
        }
    }

    public List<StudentCourse> getAllEnrolledStudents() {
        try {
            return studentCourseDao.readAll();
        } catch (DAOException | EmptyResultDataAccessException e) {
            System.out.println(MESSAGE_GET_EXCEPTION);
            return List.of();
        }
    }

    public List<Student> getStudentsByCourseName(String courseName) {
        try {
            return studentCourseDao.getStudents(courseName);
        } catch (DAOException | EmptyResultDataAccessException e) {
            System.out.println(MESSAGE_GET_EXCEPTION);
            return List.of();
        }
    }

    public void expelStudent(int studentId, int courseId) {
        try {
            studentCourseDao.delete(studentId, courseId);
        } catch (DAOException e) {
            System.out.println(MESSAGE_DELETE_EXCEPTION);
        }
    }

}