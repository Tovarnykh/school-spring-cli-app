package ua.foxminded.javaspring.tovarnykh.school_jdbc_api.dao;

import java.util.List;

import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;

import ua.foxminded.javaspring.tovarnykh.school_jdbc_api.entity.Student;
import ua.foxminded.javaspring.tovarnykh.school_jdbc_api.entity.StudentCourse;
import ua.foxminded.javaspring.tovarnykh.school_jdbc_api.exception.DAOException;

public interface StudentCourseDao{
    
    public void add(int studentId, int courseId) throws DAOException, DataIntegrityViolationException;

    public void addAll(List<StudentCourse> studentCourses) throws DAOException, DataIntegrityViolationException;

    public List<StudentCourse> readAll() throws DAOException, EmptyResultDataAccessException;

    List<Student> getStudents(String courseName) throws DAOException, EmptyResultDataAccessException;

    void delete(int studentId, int courseId) throws DAOException;

}