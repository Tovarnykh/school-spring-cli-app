package ua.foxminded.javaspring.tovarnykh.school_jdbc_api.dao;

import java.util.List;

import ua.foxminded.javaspring.tovarnykh.school_jdbc_api.entity.Student;
import ua.foxminded.javaspring.tovarnykh.school_jdbc_api.entity.StudentCourse;
import ua.foxminded.javaspring.tovarnykh.school_jdbc_api.exception.DAOException;

public interface StudentCourseDao{
    
    public void add(int studentId, int courseId) throws DAOException;

    public void addAll(List<StudentCourse> studentCourses) throws DAOException;

    public List<StudentCourse> getAll() throws DAOException;

    List<Student> getStudents(String courseName) throws DAOException;

    void delete(int studentId, int courseId) throws DAOException;

}