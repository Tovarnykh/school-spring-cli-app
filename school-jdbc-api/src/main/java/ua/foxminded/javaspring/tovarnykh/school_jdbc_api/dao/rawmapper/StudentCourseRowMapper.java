package ua.foxminded.javaspring.tovarnykh.school_jdbc_api.dao.rawmapper;

import org.springframework.jdbc.core.RowMapper;

import ua.foxminded.javaspring.tovarnykh.school_jdbc_api.dao.entity.Student;
import ua.foxminded.javaspring.tovarnykh.school_jdbc_api.dao.entity.StudentCourse;

public class StudentCourseRowMapper {

    public static final RowMapper<StudentCourse> nameAndCourse = (rs, rowNum) -> {
        StudentCourse studentCourse = new StudentCourse();

        studentCourse.setStudentFullName(rs.getString("full_name"));
        studentCourse.setCourseName(rs.getString("course_name"));
        return studentCourse;
    };

    public static final RowMapper<Student> nameAndGroup = (rs, rowNum) -> {
        Student student = new Student();
        
        student.setGroupId(rs.getInt("group_id"));
        student.setFirstName(rs.getString("first_name"));
        student.setLastName(rs.getString("last_name"));
        return student;
    };

}