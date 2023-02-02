package ua.foxminded.javaspring.tovarnykh.school_jdbc_api.cli.menuitem;

import java.util.List;

import org.springframework.stereotype.Component;

import ua.foxminded.javaspring.tovarnykh.school_jdbc_api.dao.entity.Student;
import ua.foxminded.javaspring.tovarnykh.school_jdbc_api.domain.service.StudentService;

@Component
public class StudentMenuItem extends MenuItem implements Item {

    private static final String MENU_ITEM = """
            ╔═════════════════════════════════════════╗
            ║ Choose command                          ║
            ╠─────────────────────────────────────────╣
            ║                                         ║
            ║   1 - Add new Student to Table          ║
            ║                                         ║
            ║   2 - Get Student                       ║
            ║                                         ║
            ║   3 - Get All Student                   ║
            ║                                         ║
            ║   4 - Update Student                    ║
            ║                                         ║
            ║   5 - Delete Student                    ║
            ║                                         ║
            ║   other number - to went Back           ║
            ║                                         ║
            ╚═════════════════════════════════════════╝
            """;
    private static final String STUDENT_FORMAT = " %6s | %-10s | %-10s %n";
    private static final String STUDENT_OUT_FORMAT = " %6d | %-10s | %-10s %n";

    private StudentService studentService;
    private final String itemName;

    public StudentMenuItem(StudentService studentService) {
        this.studentService = studentService;
        itemName = "Student";
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
        System.out.println("Please, enter groupId, then first name, last name:");
        int groupId = readNumber();
        String firstName = readLine();
        String lastName = readLine();

        studentService.add(groupId, firstName, lastName);
    }

    void getSection() {
        System.out.println("Please, enter studentId:");
        System.out.print("""
                ╔════════════════════════════════════════╗
                ║                 Student                ║
                ╟────────────────────────────────────────╢
                 in:
                 """);
        int studentId = readNumber();
        Student student = studentService.get(studentId);

        System.out.printf(STUDENT_FORMAT, "id", "Name", "Second Name");
        System.out.println(DELIMITER);
        if (student.getId() != 0) {
            System.out.printf(STUDENT_OUT_FORMAT, student.getGroupId(), student.getFirstName(), student.getLastName());
        }
        closeSection();
    }

    void getAllSection() {
        System.out.print("""
                ╔════════════════════════════════════════╗
                ║                Students                ║
                ╟────────────────────────────────────────╢
                 """);
        System.out.printf(STUDENT_FORMAT, "id", "Name", "Second Name");
        System.out.println(DELIMITER);

        List<Student> students = studentService.getAll();

        students.forEach(student -> System.out.printf(STUDENT_OUT_FORMAT,
                student.getGroupId(), 
                student.getFirstName(),
                student.getLastName()));
        closeSection();
    }

    void updateSection() {
        System.out.println("Please, enter studentId, then groupId, first name, last name:");
        int studentId = readNumber();
        int groupId = readNumber();
        String firstName = readLine();
        String lastName = readLine();

        studentService.update(studentId, groupId, firstName, lastName);
    }

    void deleteSection() {
        System.out.println("Please, enter studentId:");
        int studentId = readNumber();

        studentService.delete(studentId);
    }
}