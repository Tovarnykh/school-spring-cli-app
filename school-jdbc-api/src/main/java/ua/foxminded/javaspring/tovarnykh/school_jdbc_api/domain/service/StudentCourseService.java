package ua.foxminded.javaspring.tovarnykh.school_jdbc_api.domain.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import ua.foxminded.javaspring.tovarnykh.school_jdbc_api.dao.StudentCourseDao;
import ua.foxminded.javaspring.tovarnykh.school_jdbc_api.domain.generator.Generator;
import ua.foxminded.javaspring.tovarnykh.school_jdbc_api.entity.Student;
import ua.foxminded.javaspring.tovarnykh.school_jdbc_api.entity.StudentCourse;
import ua.foxminded.javaspring.tovarnykh.school_jdbc_api.exception.DAOException;

@Component
public class StudentCourseService {

    @Autowired
    private StudentCourseDao studentCourseDao;

    @Autowired
    @Qualifier("studentCourseGenerator")
    private Generator<StudentCourse> generator;

    private static final String MESSAGE_GET_EXCEPTION = "Error: Problem with receiving student_courses";

    public void generateData() {
        try {
            if (studentCourseDao.getAll().isEmpty()) {
                List<StudentCourse> studentCourse = generator.generate();
                studentCourseDao.addAll(studentCourse);
            } else {
                System.out.println("Can`t generate data, table is not empty.");
            }
        } catch (DAOException e) {
            System.out.println(MESSAGE_GET_EXCEPTION);
        }
    }

    public void enrollStudent(int studentId, int courseId) {
        try {
            studentCourseDao.add(studentId, courseId);
        } catch (DAOException e) {
            System.out.println(MESSAGE_GET_EXCEPTION);
        }
    }

    public StudentCourse get(int studentId, int courseId) {
        try {
            return studentCourseDao.getById(studentId, courseId);
        } catch (DAOException e) {
            System.out.println(MESSAGE_GET_EXCEPTION);
            return new StudentCourse();
        }
    }

    public List<Student> getStudentsByCourseName(String courseName) {
        try {
            return studentCourseDao.getStudents(courseName);
        } catch (DAOException e) {
            System.out.println(MESSAGE_GET_EXCEPTION);
            return List.of();
        }
    }

    public void expelStudent(int studentId, int courseId) {
        try {
            studentCourseDao.delete(studentId, courseId);
        } catch (DAOException e) {
            System.out.println(MESSAGE_GET_EXCEPTION);
        }
    }

}