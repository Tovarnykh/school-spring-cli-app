package ua.foxminded.javaspring.tovarnykh.school_jdbc_api.cli.menuitem;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import ua.foxminded.javaspring.tovarnykh.school_jdbc_api.cli.CommandLineInterface;
import ua.foxminded.javaspring.tovarnykh.school_jdbc_api.domain.service.StudentService;
import ua.foxminded.javaspring.tovarnykh.school_jdbc_api.entity.Student;

@Component
public class StudentMenuItem extends CommandLineInterface implements Item {

    private static final String MENU_ITEM = """
            ╔═════════════════════════════════════════╗
            ║ Choose command                          ║
            ╠─────────────────────────────────────────╣
            ║                                         ║
            ║   1 - Populate Student Table with       ║
            ║       random data                       ║
            ║                                         ║
            ║   2 - Add new Student to Table          ║
            ║                                         ║
            ║   3 - Get Student                       ║
            ║                                         ║
            ║   4 - Update Student                    ║
            ║                                         ║
            ║   5 - Delete Student                    ║
            ║                                         ║
            ║   other number - to went Back           ║
            ║                                         ║
            ╚═════════════════════════════════════════╝
            """;

    @Autowired
    private StudentService studentService;

    @Override
    public void draw() {
        System.out.println(MENU_ITEM);
        int choice = readNumber();

        if (choice == 1) {
            studentService.generateData();
        } else if (choice == 2) {
            addSection();
        } else if (choice == 3) {
            getSection();
        } else if (choice == 4) {
            updateSection();
        } else if (choice == 5) {
            deleteSection();
        }
    }

    private void addSection() {
        System.out.println("Please, enter groupId, then first name, last name:");
        int groupId = readNumber();
        String firstName = readLine();
        String lastName = readLine();

        studentService.add(groupId, firstName, lastName);
    }

    private void getSection() {
        System.out.println("Please, enter studentId:");
        System.out.print("""
                ╔════════════════════════════════════════╗
                ║                 Student                ║
                ╟────────────────────────────────────────╢
                 in:
                 """);
        int studentId = readNumber();
        Student student = studentService.get(studentId);

        System.out.printf(" %12s - %s - %s %n", "id", "Name", "Second Name");
        System.out.println(DELIMITER);
        if (student.getId() != 0) {
            System.out.printf(" %12d | %s | %s %n", 
                    student.getGroupId(), 
                    student.getFirstName(),
                    student.getLastName());
        }
        closeSection();
    }

    private void updateSection() {
        System.out.println("Please, enter studentId, then groupId, first name, last name:");
        int studentId = readNumber();
        int groupId = readNumber();
        String firstName = readLine();
        String lastName = readLine();

        studentService.update(studentId, groupId, firstName, lastName);
    }

    private void deleteSection() {
        System.out.println("Please, enter studentId:");
        int studentId = readNumber();

        studentService.delete(studentId);
    }
}