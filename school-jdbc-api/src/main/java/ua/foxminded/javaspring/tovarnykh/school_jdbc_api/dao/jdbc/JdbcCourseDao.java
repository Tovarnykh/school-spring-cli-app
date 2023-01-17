package ua.foxminded.javaspring.tovarnykh.school_jdbc_api.dao.jdbc;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BatchPreparedStatementSetter;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import ua.foxminded.javaspring.tovarnykh.school_jdbc_api.dao.CourseDao;
import ua.foxminded.javaspring.tovarnykh.school_jdbc_api.entity.Course;
import ua.foxminded.javaspring.tovarnykh.school_jdbc_api.exception.DAOException;

@Repository
public class JdbcCourseDao implements CourseDao {

    @Autowired
    private JdbcTemplate jdbcTemplate;

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

    private RowMapper<Course> rowMapper = (rs, rowNum) -> {
        Course course = new Course();

        course.setId(rs.getInt("course_id"));
        course.setName(rs.getString("course_name"));
        course.setDescription(rs.getString("course_description"));
        return course;
    };

    @Override
    public void add(Course course) throws DAOException {
        jdbcTemplate.update(PROPERTY_COURSE_ADD, course.getName(), course.getDescription());
    }

    @Override
    public void addAll(List<Course> courses) {
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
    public Course read(int id) throws DAOException {
        return jdbcTemplate.queryForObject(PROPERTY_COURSE_GET_WHERE, rowMapper, id);
    }

    @Override
    public List<Course> readAll() throws DAOException {
        return jdbcTemplate.query(PROPERTY_COURSE_GET, rowMapper);
    }

    @Override
    public void update(Course course) throws DAOException {
        jdbcTemplate.update(PROPERTY_COURSE_UPDATE, course.getName(), course.getDescription(), course.getId());
    }

    @Override
    public void delete(int id) throws DAOException {
        jdbcTemplate.update(PROPERTY_COURSE_DELETE, id);
    }

}