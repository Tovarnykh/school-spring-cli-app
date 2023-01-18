package ua.foxminded.javaspring.tovarnykh.school_jdbc_api.cli.menuitem;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import ua.foxminded.javaspring.tovarnykh.school_jdbc_api.cli.CommandLineInterface;
import ua.foxminded.javaspring.tovarnykh.school_jdbc_api.domain.service.GroupService;
import ua.foxminded.javaspring.tovarnykh.school_jdbc_api.entity.Group;

@Component
public class GroupMenuItem extends CommandLineInterface implements Item {

    private static final String MENU_ITEM = """
            ╔═════════════════════════════════════════╗
            ║ Choose command                          ║
            ╠─────────────────────────────────────────╣
            ║                                         ║
            ║   1 - Populate Group Table with         ║
            ║       random data                       ║
            ║                                         ║
            ║   2 - Add new Group to Table            ║
            ║                                         ║
            ║   3 - Get Group                         ║
            ║                                         ║
            ║   4 - Update Group                      ║
            ║                                         ║
            ║   5 - Delete Group                      ║
            ║                                         ║
            ║   6 - Get Groups with less              ║
            ║       or equals Students                ║
            ║                                         ║
            ║   other number - to went Back           ║
            ║                                         ║
            ╚═════════════════════════════════════════╝
            """;

    @Autowired
    private GroupService groupService;

    @Override
    public void draw() {
        System.out.println(MENU_ITEM);
        int choice = readNumber();

        if (choice == 1) {
            groupService.generateData();
        } else if (choice == 2) {
            addSection();
        } else if (choice == 3) {
            getSection();
        } else if (choice == 4) {
            updateSection();
        } else if (choice == 5) {
            deleteSection();
        } else if (choice == 6) {
            getGroups();
        }
    }

    private void addSection() {
        System.out.println("Please, enter new group name:");
        String name = readLine();

        groupService.add(name);
    }

    private void getSection() {
        System.out.println("Please, enter groupId:");
        int studentId = readNumber();

        System.out.print("""
                ╔════════════════════════════════════════╗
                ║                  Group                 ║
                ╟────────────────────────────────────────╢
                 in:
                 """);
        Group group = groupService.get(studentId);

        System.out.printf(" %18s | %s %n", "id", "name");
        System.out.println(DELIMITER);
        if (group.getId() != 0) {
            System.out.printf(" %18d | %s %n", group.getId(), group.getName());
        }
        closeSection();
    }

    private void updateSection() {
        System.out.println("Please, enter groupId, then group name:");
        int groupId = readNumber();
        String name = readLine();

        groupService.update(groupId, name);
    }

    private void deleteSection() {
        System.out.println("Please, enter groupId:");
        int groupId = readNumber();

        groupService.delete(groupId);
    }

    private void getGroups() {
        System.out.println("Please, enter number of students to find groups with it`s amount:");
        int numberOfStudents = readNumber();

        System.out.print("""
                ╔════════════════════════════════════════╗
                ║                  Groups                ║
                ╟────────────────────────────────────────╢
                 in:
                 """);
        
        List<Group> groups = groupService.getGroupsWithLessStudents(numberOfStudents);
        
        System.out.printf(" %15s |  %s | %s %n", "id", "name", "inscribed");
        System.out.println();
        if (!groups.isEmpty()) {
            groups.forEach(group -> System.out.printf(" %15d | %s | %s %n", 
                    group.getId(), 
                    group.getName(),
                    group.getInscribedStudents()));
        }
        closeSection();
    }

}