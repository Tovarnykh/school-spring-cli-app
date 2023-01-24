package ua.foxminded.javaspring.tovarnykh.school_jdbc_api.dao.jdbc;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.BatchPreparedStatementSetter;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import ua.foxminded.javaspring.tovarnykh.school_jdbc_api.dao.CourseDao;
import ua.foxminded.javaspring.tovarnykh.school_jdbc_api.dao.entity.Course;
import ua.foxminded.javaspring.tovarnykh.school_jdbc_api.dao.rawmapper.CourseRowMapper;

@Repository
public class JdbcCourseDao implements CourseDao {

    private static final String PROPERTY_COURSE_ADD = "INSERT INTO courses (course_name, course_description) VALUES (?, ?)";
    private static final String PROPERTY_COURSE_GET_WHERE = "SELECT course_id, course_name, course_description FROM courses WHERE course_id = (?)";
    private static final String PROPERTY_COURSE_GET = "SELECT course_id, course_name, course_description FROM courses";
    private static final String PROPERTY_COURSE_DELETE = "DELETE FROM courses WHERE course_id = (?)";
    private static final String PROPERTY_COURSE_UPDATE = """
            UPDATE courses
            SET course_name = (?),
            course_description = (?)
            WHERE course_id = (?);
            """;

    private JdbcTemplate jdbcTemplate;

    public JdbcCourseDao(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public void add(Course course) throws EmptyResultDataAccessException {
        jdbcTemplate.update(PROPERTY_COURSE_ADD, course.getName(), course.getDescription());
    }

    @Override
    public void addAll(List<Course> courses) throws EmptyResultDataAccessException {
        jdbcTemplate.batchUpdate(PROPERTY_COURSE_ADD, new BatchPreparedStatementSetter() {

            @Override
            public void setValues(PreparedStatement ps, int i) throws SQLException {
                Course course = courses.get(i);

                ps.setString(1, course.getName());
                ps.setString(2, course.getDescription());
            }

            @Override
            public int getBatchSize() {
                return courses.size();
            }

        });
    }

    @Override
    public Course read(int id) throws EmptyResultDataAccessException {
        return jdbcTemplate.queryForObject(PROPERTY_COURSE_GET_WHERE, CourseRowMapper.allTableColums, id);
    }

    @Override
    public List<Course> readAll() throws EmptyResultDataAccessException {
        return jdbcTemplate.query(PROPERTY_COURSE_GET, CourseRowMapper.allTableColums);
    }

    @Override
    public void update(Course course) {
        jdbcTemplate.update(PROPERTY_COURSE_UPDATE, course.getName(), course.getDescription(), course.getId());
    }

    @Override
    public void delete(int id) {
        jdbcTemplate.update(PROPERTY_COURSE_DELETE, id);
    }

}