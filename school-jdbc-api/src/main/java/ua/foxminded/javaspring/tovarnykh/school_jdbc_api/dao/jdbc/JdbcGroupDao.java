package ua.foxminded.javaspring.tovarnykh.school_jdbc_api.dao.jdbc;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.BatchPreparedStatementSetter;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import ua.foxminded.javaspring.tovarnykh.school_jdbc_api.dao.GroupDao;
import ua.foxminded.javaspring.tovarnykh.school_jdbc_api.dao.entity.Group;
import ua.foxminded.javaspring.tovarnykh.school_jdbc_api.dao.rawmapper.GroupRowMapper;

@Repository
public class JdbcGroupDao implements GroupDao {

    private static final String PROPERTY_GROUP_ADD = "INSERT INTO groups (group_name) VALUES (?)";
    private static final String PROPERTY_GROUP_DELETE = "DELETE FROM groups WHERE group_id = (?)";
    private static final String PROPERTY_GROUP_GET_WHERE = "SELECT group_id, group_name FROM groups WHERE group_id = (?)";
    private static final String PROPERTY_GROUP_GET = "SELECT group_id, group_name FROM groups";
    private static final String PROPERTY_GROUP_GET_WITH_CONDITION = """
            SELECT g.group_id, g.group_name, COUNT(s.student_id) AS inscribed_students
            FROM students s
            JOIN groups g ON g.group_id = s.group_id
            GROUP BY g.group_name, g.group_id
            HAVING COUNT(student_id) <= (?)
            ORDER BY inscribed_students;
            """;
    private static final String PROPERTY_GROUP_UPDATE = """
            UPDATE groups
            SET group_name = (?)
            WHERE group_id = (?);
            """;

    private JdbcTemplate jdbcTemplate;

    public JdbcGroupDao(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public void add(Group group) {
        jdbcTemplate.update(PROPERTY_GROUP_ADD, group.getName());
    }

    @Override
    public void addAll(List<Group> groups) {
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
    public Group read(int id) throws EmptyResultDataAccessException {
        return jdbcTemplate.queryForObject(PROPERTY_GROUP_GET_WHERE, GroupRowMapper.allTableColumns, id);
    }

    @Override
    public List<Group> readAll() throws EmptyResultDataAccessException {
        return jdbcTemplate.query(PROPERTY_GROUP_GET, GroupRowMapper.allTableColumns);
    }

    @Override
    public void update(Group group) {
        jdbcTemplate.update(PROPERTY_GROUP_UPDATE, group.getName(), group.getId());
    }

    @Override
    public void delete(int id) {
        jdbcTemplate.update(PROPERTY_GROUP_DELETE, id);
    }

    @Override
    public List<Group> getGroupsWithLessEqualsStudentCount(int studentCount) throws EmptyResultDataAccessException {
        return jdbcTemplate.query(PROPERTY_GROUP_GET_WITH_CONDITION, GroupRowMapper.inscribedStudentsTableColumn,
                studentCount);
    }

}