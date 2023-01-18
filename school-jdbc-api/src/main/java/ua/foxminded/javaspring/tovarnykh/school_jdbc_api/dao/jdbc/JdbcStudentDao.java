package ua.foxminded.javaspring.tovarnykh.school_jdbc_api.dao.jdbc;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.BatchPreparedStatementSetter;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import ua.foxminded.javaspring.tovarnykh.school_jdbc_api.dao.StudentDao;
import ua.foxminded.javaspring.tovarnykh.school_jdbc_api.entity.Student;
import ua.foxminded.javaspring.tovarnykh.school_jdbc_api.exception.DAOException;

@Repository
public class JdbcStudentDao implements StudentDao {

    @Autowired
    private JdbcTemplate jdbcTemplate;

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

    private RowMapper<Student> rowMapper = (rs, rowNum) -> {
        Student student = new Student();

        student.setId(rs.getInt("student_id"));
        student.setGroupId(rs.getInt("group_id"));
        student.setFirstName(rs.getString("first_name"));
        student.setLastName(rs.getString("last_name"));
        return student;
    };

    @Override
    public void add(Student student) throws DAOException {
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
    public Student read(int id) throws DAOException, EmptyResultDataAccessException {
        return jdbcTemplate.queryForObject(PROPERTY_STUDENT_GET_WHERE, rowMapper, id);
    }

    @Override
    public List<Student> readAll() throws DAOException, EmptyResultDataAccessException {
        return jdbcTemplate.query(PROPERTY_STUDENT_GET, rowMapper);
    }

    @Override
    public void update(Student student) throws DAOException {
        jdbcTemplate.update(PROPERTY_STUDENT_UPDATE, student.getGroupId(), student.getFirstName(),
                student.getLastName(), student.getId());
    }

    @Override
    public void delete(int studentId) throws DAOException {
        jdbcTemplate.update(PROPERTY_STUDENT_DELETE, studentId);
    }

}
