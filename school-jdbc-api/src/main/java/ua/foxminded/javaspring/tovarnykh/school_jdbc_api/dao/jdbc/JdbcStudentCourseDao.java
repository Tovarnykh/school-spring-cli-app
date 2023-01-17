package ua.foxminded.javaspring.tovarnykh.school_jdbc_api.dao.jdbc;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BatchPreparedStatementSetter;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import ua.foxminded.javaspring.tovarnykh.school_jdbc_api.dao.StudentCourseDao;
import ua.foxminded.javaspring.tovarnykh.school_jdbc_api.entity.Student;
import ua.foxminded.javaspring.tovarnykh.school_jdbc_api.entity.StudentCourse;
import ua.foxminded.javaspring.tovarnykh.school_jdbc_api.exception.DAOException;

@Repository
public class JdbcStudentCourseDao implements StudentCourseDao {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    private static final String PROPERTY_STUDENTCOURSE_ADD = "INSERT INTO students_courses(student_id, course_id) VALUES (?,?);";
    private static final String PROPERTY_STUDENTCOURSE_GET = "SELECT student_id, course_id FROM students_courses";
    private static final String PROPERTY_STUDENTCOURSE_GET_WHERE = """
            SELECT student_id, course_id FROM students_courses
            WHERE student_id = (?) AND course_id = (?)
            """;
    private static final String PROPERTY_STUDENTCOURSE_GET_STUDENTS_BY_COURSE_NAME = """
            SELECT first_name, last_name
            FROM students_courses sc
            JOIN students s ON s.student_id = sc.student_id
            JOIN courses c ON c.course_id = sc.course_id
            WHERE course_name = (?)
            ORDER BY first_name
            """;;
    private static final String PROPERTY_STUDENTCOURSE_DELETE = """
            DELETE FROM students_courses
            WHERE student_id = (?) AND course_id = (?)
            """;;

    private RowMapper<StudentCourse> rowMapper = (rs, rowNum) -> {
        StudentCourse studentCourse = new StudentCourse();

        studentCourse.setStudentId(rs.getInt("student_id"));
        studentCourse.setCourseID(rs.getInt("course_id"));
        return studentCourse;
    };

    @Override
    public void add(int studentId, int courseId) {
        jdbcTemplate.update(PROPERTY_STUDENTCOURSE_ADD, studentId, courseId);
    }

    @Override
    public void addAll(List<StudentCourse> studentCourses) {
        jdbcTemplate.batchUpdate(PROPERTY_STUDENTCOURSE_ADD, new BatchPreparedStatementSetter() {

            @Override
            public void setValues(PreparedStatement ps, int i) throws SQLException {
                StudentCourse studentCourse = studentCourses.get(i);

                ps.setInt(1, studentCourse.getStudentId());
                ps.setInt(2, studentCourse.getCourseID());
            }

            @Override
            public int getBatchSize() {
                return studentCourses.size();
            }

        });
    }

    @Override
    public StudentCourse getById(int studentId, int courseId) {
        return jdbcTemplate.queryForObject(PROPERTY_STUDENTCOURSE_GET_WHERE, rowMapper, studentId, courseId);
    }

    @Override
    public List<StudentCourse> getAll() {
        return jdbcTemplate.query(PROPERTY_STUDENTCOURSE_GET, rowMapper);
    }

    @Override
    public List<Student> getStudents(String courseName) throws DAOException {
        return jdbcTemplate.query(PROPERTY_STUDENTCOURSE_GET_STUDENTS_BY_COURSE_NAME, (rs, rowNum) -> {
            Student student = new Student();

            student.setFirstName(rs.getString("first_name"));
            student.setLastName(rs.getString("last_name"));
            return student;
        });
    }

    @Override
    public void delete(int studentId, int courseId) {
        jdbcTemplate.update(PROPERTY_STUDENTCOURSE_DELETE, studentId, courseId);
    }

}