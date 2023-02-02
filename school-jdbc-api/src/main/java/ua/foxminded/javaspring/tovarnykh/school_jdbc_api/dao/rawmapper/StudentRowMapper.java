package ua.foxminded.javaspring.tovarnykh.school_jdbc_api.dao.rawmapper;

import org.springframework.jdbc.core.RowMapper;

import ua.foxminded.javaspring.tovarnykh.school_jdbc_api.dao.entity.Student;

public class StudentRowMapper {
    
    public static final RowMapper<Student> allTableColumns = (rs, rowNum) -> {
        Student student = new Student();

        student.setId(rs.getInt("student_id"));
        student.setGroupId(rs.getInt("group_id"));
        student.setFirstName(rs.getString("first_name"));
        student.setLastName(rs.getString("last_name"));
        return student;
    };

}