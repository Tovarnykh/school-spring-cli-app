package ua.foxminded.javaspring.tovarnykh.school_jdbc_api.dao;

import java.util.List;

import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;

import ua.foxminded.javaspring.tovarnykh.school_jdbc_api.dao.entity.Student;
import ua.foxminded.javaspring.tovarnykh.school_jdbc_api.dao.entity.StudentCourse;

public interface StudentCourseDao {

    void add(int studentId, int courseId) throws DataIntegrityViolationException;

    void addAll(List<StudentCourse> studentCourses) throws DataIntegrityViolationException;

    List<StudentCourse> readAll() throws EmptyResultDataAccessException;

    List<Student> getStudents(String courseName) throws EmptyResultDataAccessException;

    void delete(int studentId, int courseId);

}