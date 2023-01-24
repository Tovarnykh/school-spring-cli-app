package ua.foxminded.javaspring.tovarnykh.school_jdbc_api.domain.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import ua.foxminded.javaspring.tovarnykh.school_jdbc_api.dao.StudentCourseDao;
import ua.foxminded.javaspring.tovarnykh.school_jdbc_api.dao.entity.Student;
import ua.foxminded.javaspring.tovarnykh.school_jdbc_api.dao.entity.StudentCourse;
import ua.foxminded.javaspring.tovarnykh.school_jdbc_api.domain.generator.Generator;

@Service
public class StudentCourseService {

    private static final String MESSAGE_POPULATE_EXCEPTION = "Error: Problem with populating student-courses";
    private static final String MESSAGE_GET_EXCEPTION = "Error: Problem with receiving student-course";
    private static final String MESSAGE_ADD_EXCEPTION = "Error: Problem with adding student-course";

    private StudentCourseDao studentCourseDao;
    private Generator<StudentCourse> generator;

    public StudentCourseService(StudentCourseDao studentCourseDao,
            @Qualifier("studentCourseGenerator") Generator<StudentCourse> generator) {
        this.studentCourseDao = studentCourseDao;
        this.generator = generator;
    }

    public void generateData() {
        try {
            List<StudentCourse> studentCourse = generator.generate();
            studentCourseDao.addAll(studentCourse);
        } catch (DataIntegrityViolationException e) {
            System.out.println(MESSAGE_POPULATE_EXCEPTION);
        }
    }

    public void enrollStudent(int studentId, int courseId) {
        try {
            studentCourseDao.add(studentId, courseId);
        } catch (DataIntegrityViolationException e) {
            System.out.println(MESSAGE_ADD_EXCEPTION);
        }
    }

    public List<StudentCourse> getAllEnrolledStudents() {
        try {
            return studentCourseDao.readAll();
        } catch (EmptyResultDataAccessException e) {
            System.out.println(MESSAGE_GET_EXCEPTION);
            return List.of();
        }
    }

    public List<Student> getStudentsByCourseName(String courseName) {
        try {
            return studentCourseDao.getStudents(courseName);
        } catch (EmptyResultDataAccessException e) {
            System.out.println(MESSAGE_GET_EXCEPTION);
            return List.of();
        }
    }

    public void expelStudent(int studentId, int courseId) {
        studentCourseDao.delete(studentId, courseId);
    }

}