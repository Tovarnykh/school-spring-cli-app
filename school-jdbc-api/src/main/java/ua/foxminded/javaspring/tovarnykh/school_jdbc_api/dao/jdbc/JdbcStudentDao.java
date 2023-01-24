package ua.foxminded.javaspring.tovarnykh.school_jdbc_api.dao.jdbc;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.BatchPreparedStatementSetter;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import ua.foxminded.javaspring.tovarnykh.school_jdbc_api.dao.StudentDao;
import ua.foxminded.javaspring.tovarnykh.school_jdbc_api.dao.entity.Student;
import ua.foxminded.javaspring.tovarnykh.school_jdbc_api.dao.rawmapper.StudentRowMapper;

@Repository
public class JdbcStudentDao implements StudentDao {

    private static final String PROPERTY_STUDENT_ADD = "INSERT INTO students (group_id, first_name, last_name) VALUES (?, ?, ?)";
    private static final String PROPERTY_STUDENT_DELETE = "DELETE FROM students WHERE student_id = (?)";
    private static final String PROPERTY_STUDENT_GET_WHERE = "SELECT student_id, group_id, first_name, last_name FROM students WHERE student_id = (?)";
    private static final String PROPERTY_STUDENT_GET = "SELECT student_id, group_id, first_name, last_name FROM students";
    private static final String PROPERTY_STUDENT_UPDATE = """
            UPDATE students
            SET group_id = (?),
            first_name = (?),
            last_name = (?)
            WHERE student_id = (?);
            """;

    private JdbcTemplate jdbcTemplate;

    public JdbcStudentDao(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public void add(Student student) {
        jdbcTemplate.update(PROPERTY_STUDENT_ADD, student.getGroupId(), student.getFirstName(), student.getLastName());
    }

    @Override
    public void addAll(List<Student> students) {
        jdbcTemplate.batchUpdate(PROPERTY_STUDENT_ADD, new BatchPreparedStatementSetter() {

            @Override
            public void setValues(PreparedStatement ps, int i) throws SQLException {
                Student student = students.get(i);

                ps.setInt(1, student.getGroupId());
                ps.setString(2, student.getFirstName());
                ps.setString(3, student.getLastName());
            }

            @Override
            public int getBatchSize() {
                return students.size();
            }

        });
    }

    @Override
    public Student read(int id) throws EmptyResultDataAccessException {
        return jdbcTemplate.queryForObject(PROPERTY_STUDENT_GET_WHERE, StudentRowMapper.allTableColumns, id);
    }

    @Override
    public List<Student> readAll() throws EmptyResultDataAccessException {
        return jdbcTemplate.query(PROPERTY_STUDENT_GET, StudentRowMapper.allTableColumns);
    }

    @Override
    public void update(Student student) {
        jdbcTemplate.update(PROPERTY_STUDENT_UPDATE, student.getGroupId(), student.getFirstName(),
                student.getLastName(), student.getId());
    }

    @Override
    public void delete(int studentId) {
        jdbcTemplate.update(PROPERTY_STUDENT_DELETE, studentId);
    }

}
