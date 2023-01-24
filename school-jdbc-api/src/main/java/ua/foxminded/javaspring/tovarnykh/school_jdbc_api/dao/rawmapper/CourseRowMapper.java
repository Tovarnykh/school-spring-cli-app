package ua.foxminded.javaspring.tovarnykh.school_jdbc_api.dao.rawmapper;

import org.springframework.jdbc.core.RowMapper;

import ua.foxminded.javaspring.tovarnykh.school_jdbc_api.dao.entity.Course;

public class CourseRowMapper {
    
    public static RowMapper<Course> allTableColums = (rs, rowNum) -> {
        Course course = new Course();

        course.setId(rs.getInt("course_id"));
        course.setName(rs.getString("course_name"));
        course.setDescription(rs.getString("course_description"));
        return course;
    };

}