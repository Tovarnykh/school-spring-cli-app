package ua.foxminded.javaspring.tovarnykh.school_jdbc_api.cli.menuitem;

import java.util.List;

import org.springframework.stereotype.Component;

import ua.foxminded.javaspring.tovarnykh.school_jdbc_api.dao.entity.Group;
import ua.foxminded.javaspring.tovarnykh.school_jdbc_api.domain.service.GroupService;

@Component
public class GroupMenuItem extends MenuItem implements Item {

    private static final String MENU_ITEM = """
            ╔═════════════════════════════════════════╗
            ║ Choose command                          ║
            ╠─────────────────────────────────────────╣
            ║                                         ║
            ║   1 - Add new Group to Table            ║
            ║                                         ║
            ║   2 - Get Group                         ║
            ║                                         ║
            ║   3 - Get All Groups                    ║
            ║                                         ║
            ║   3 - Update Group                      ║
            ║                                         ║
            ║   4 - Delete Group                      ║
            ║                                         ║
            ║   other number - to went Back           ║
            ║                                         ║
            ╚═════════════════════════════════════════╝
            """;
    private static final String GROUP_FORMAT = " %18s | %s %n";

    private GroupService groupService;
    private final String itemName;

    public GroupMenuItem(GroupService groupService) {
        this.groupService = groupService;
        this.itemName = "Group";
    }

    @Override
    public void draw() {
        System.out.println(MENU_ITEM);
        chooseOpetion();
    }

    @Override
    public String getName() {
        return itemName;
    }

    void addSection() {
        System.out.println("Please, enter new group name:");
        String name = readLine();

        groupService.add(name);
    }

    void getSection() {
        System.out.println("Please, enter groupId:");
        int studentId = readNumber();

        System.out.print("""
                ╔════════════════════════════════════════╗
                ║                  Group                 ║
                ╟────────────────────────────────────────╢
                 in:
                 """);
        Group group = groupService.get(studentId);

        System.out.printf(GROUP_FORMAT, "id", "name");
        System.out.println(DELIMITER);
        if (group.getId() != 0) {
            System.out.printf(" %18d | %s %n", group.getId(), group.getName());
        }
        closeSection();
    }

    void getAllSection() {
        System.out.print("""
                ╔════════════════════════════════════════╗
                ║                Courses                 ║
                ╟────────────────────────────────────────╢
                 """);
        System.out.printf(GROUP_FORMAT, "Id", "Name");
        System.out.println(DELIMITER);

        List<Group> groups = groupService.getAll();

        groups.forEach(group -> System.out.printf(GROUP_FORMAT, 
                group.getId(), 
                group.getName()));

        closeSection();
    }

    void updateSection() {
        System.out.println("Please, enter groupId, then group name:");
        int groupId = readNumber();
        String name = readLine();

        groupService.update(groupId, name);
    }

    void deleteSection() {
        System.out.println("Please, enter groupId:");
        int groupId = readNumber();

        groupService.delete(groupId);
    }

}