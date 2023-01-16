package ua.foxminded.javaspring.tovarnykh.school_jdbc_api.dao;

import java.util.List;

import ua.foxminded.javaspring.tovarnykh.school_jdbc_api.entity.Student;
import ua.foxminded.javaspring.tovarnykh.school_jdbc_api.exception.DAOException;

public interface StudentCourse {
    
    List<Student> getStudentsWithCourseName(String courseName) throws DAOException;

}