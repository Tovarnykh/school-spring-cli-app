package ua.foxminded.javaspring.tovarnykh.school_jdbc_api.dao.jdbc;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BatchPreparedStatementSetter;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import ua.foxminded.javaspring.tovarnykh.school_jdbc_api.dao.GroupDao;
import ua.foxminded.javaspring.tovarnykh.school_jdbc_api.entity.Group;
import ua.foxminded.javaspring.tovarnykh.school_jdbc_api.exception.DAOException;

@Repository
public class JdbcGroupDao implements GroupDao {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    private final String PROPERTY_GROUP_ADD = "INSERT INTO groups (group_name) VALUES (?)";
    private final String PROPERTY_GROUP_DELETE = "DELETE FROM groups WHERE group_id = (?)";
    private final String PROPERTY_GROUP_GET_WHERE = "SELECT group_id, group_name FROM groups WHERE course_id = (?)";
    private final String PROPERTY_GROUP_GET = "SELECT group_id, group_name FROM groups";
    private final String PROPERTY_GROUP_GET_WITH_CONDITION = """
            SELECT g.group_name,  COUNT(s.student_id) AS inscribed_students
            FROM students s
            JOIN groups g ON g.group_id = s.group_id
            GROUP BY g.group_name
            HAVING COUNT(student_id) <= (?)
            ORDER BY inscribed_students
            """;
    private final String PROPERTY_GROUP_UPDATE = """
            UPDATE groups
            SET group_name = (?)
            WHERE course_id = (?);
            """;

    private RowMapper<Group> rowMapper = (rs, rowNum) -> {
        Group group = new Group();

        group.setId(rs.getInt("group_id"));
        group.setName(rs.getString("group_name"));
        return group;
    };

    @Override
    public void add(Group group) throws DAOException {
        jdbcTemplate.update(PROPERTY_GROUP_ADD, group.getName());
    }

    public void add(List<Group> groups) {
        jdbcTemplate.batchUpdate(PROPERTY_GROUP_ADD, new BatchPreparedStatementSetter() {

            @Override
            public void setValues(PreparedStatement ps, int i) throws SQLException {
                Group group = groups.get(i);

                ps.setString(1, group.getName());
            }

            @Override
            public int getBatchSize() {
                return groups.size();
            }

        });
    }

    @Override
    public Group getById(int id) throws DAOException {
        return jdbcTemplate.queryForObject(PROPERTY_GROUP_GET_WHERE, rowMapper, id);
    }

    @Override
    public List<Group> getAll() throws DAOException {
        return jdbcTemplate.query(PROPERTY_GROUP_GET, rowMapper);
    }

    @Override
    public void update(Group group) throws DAOException {
        jdbcTemplate.update(PROPERTY_GROUP_UPDATE, group.getName(), group.getId());
    }

    @Override
    public void delete(int id) throws DAOException {
        jdbcTemplate.update(PROPERTY_GROUP_DELETE, id);
    }

    @Override
    public List<Group> getGroupsWithLessEqualsStudentCount(int studentCount) throws DAOException {
        return jdbcTemplate.query(PROPERTY_GROUP_GET_WITH_CONDITION, (rs, rowNum) -> {
            Group group = new Group();
            group.setId(rs.getInt("group_id"));
            group.setName(rs.getString("group_name"));
            group.setInscribedStudents(rs.getInt("inscribed_students"));
            return group;
        });
    }

}