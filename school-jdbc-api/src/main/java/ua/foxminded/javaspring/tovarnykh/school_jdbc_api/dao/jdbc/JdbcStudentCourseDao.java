package ua.foxminded.javaspring.tovarnykh.school_jdbc_api.dao.jdbc;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.BatchPreparedStatementSetter;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;
import ua.foxminded.javaspring.tovarnykh.school_jdbc_api.dao.StudentCourseDao;
import ua.foxminded.javaspring.tovarnykh.school_jdbc_api.dao.entity.Student;
import ua.foxminded.javaspring.tovarnykh.school_jdbc_api.dao.entity.StudentCourse;
import ua.foxminded.javaspring.tovarnykh.school_jdbc_api.dao.rawmapper.StudentCourseRowMapper;

@Component
public class JdbcStudentCourseDao implements StudentCourseDao {

    private static final String PROPERTY_STUDENTCOURSE_ADD = "INSERT INTO students_courses(student_id, course_id) VALUES (?,?);";
    private static final String PROPERTY_STUDENTCOURSE_GET_ALL = """
            SELECT CONCAT(first_name, ' ' , last_name) AS full_name, course_name
            FROM students_courses sc
            JOIN students s  ON s.student_id = sc.student_id
            JOIN courses c ON c.course_id = sc.course_id
            """;
    private static final String PROPERTY_STUDENTCOURSE_GET_STUDENTS_BY_COURSE_NAME = """
            SELECT group_id, first_name, last_name
            FROM students_courses sc
            JOIN students s ON s.student_id = sc.student_id
            JOIN courses c ON c.course_id = sc.course_id
            WHERE course_name = (?)
            ORDER BY first_name
            """;
    private static final String PROPERTY_STUDENTCOURSE_DELETE = """
            DELETE FROM students_courses
            WHERE student_id = (?) AND course_id = (?)
            """;

    private JdbcTemplate jdbcTemplate;

    public JdbcStudentCourseDao(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public void add(int studentId, int courseId) {
        jdbcTemplate.update(PROPERTY_STUDENTCOURSE_ADD, studentId, studentId);
    }

    @Override
    public void addAll(List<StudentCourse> studentCourses) {
        jdbcTemplate.batchUpdate(PROPERTY_STUDENTCOURSE_ADD, new BatchPreparedStatementSetter() {

            @Override
            public void setValues(PreparedStatement ps, int i) throws SQLException {
                StudentCourse studentCourse = studentCourses.get(i);

                ps.setInt(1, studentCourse.getStudentId());
                ps.setInt(2, studentCourse.getCourseId());
            }

            @Override
            public int getBatchSize() {
                return studentCourses.size();
            }

        });
    }

    @Override
    public List<StudentCourse> readAll() throws EmptyResultDataAccessException {
        return jdbcTemplate.query(PROPERTY_STUDENTCOURSE_GET_ALL, StudentCourseRowMapper.nameAndCourse);
    }

    @Override
    public List<Student> getStudents(String courseName) throws EmptyResultDataAccessException {
        return jdbcTemplate.query(PROPERTY_STUDENTCOURSE_GET_STUDENTS_BY_COURSE_NAME,
                StudentCourseRowMapper.nameAndGroup, courseName);
    }

    @Override
    public void delete(int studentId, int courseId) {
        jdbcTemplate.update(PROPERTY_STUDENTCOURSE_DELETE, studentId, courseId);
    }

}