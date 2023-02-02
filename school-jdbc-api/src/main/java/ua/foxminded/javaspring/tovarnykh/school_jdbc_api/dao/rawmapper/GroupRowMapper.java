package ua.foxminded.javaspring.tovarnykh.school_jdbc_api.dao.rawmapper;

import org.springframework.jdbc.core.RowMapper;

import ua.foxminded.javaspring.tovarnykh.school_jdbc_api.dao.entity.Group;

public class GroupRowMapper {

    public static final RowMapper<Group> allTableColumns = (rs, rowNum) -> {
        Group group = new Group();

        group.setId(rs.getInt("group_id"));
        group.setName(rs.getString("group_name"));
        return group;
    };

    public static final RowMapper<Group> inscribedStudentsTableColumn = (rs, rowNum) -> {
        Group group = new Group();
        group.setId(rs.getInt("group_id"));
        group.setName(rs.getString("group_name"));
        group.setInscribedStudents(rs.getInt("inscribed_students"));
        return group;
    };

}