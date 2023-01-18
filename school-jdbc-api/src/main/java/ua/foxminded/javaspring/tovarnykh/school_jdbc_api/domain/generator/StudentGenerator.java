package ua.foxminded.javaspring.tovarnykh.school_jdbc_api.domain.generator;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;
import java.util.stream.Stream;

import org.springframework.stereotype.Component;

import ua.foxminded.javaspring.tovarnykh.school_jdbc_api.entity.Student;

@Component
public class StudentGenerator implements Generator<Student> {

    private static final int STUDENTS_TO_GENERATE = 200;
    private static final int MIN_GROUP_SIZE = 10;
    private static final int MAX_GROUP_SIZE = 30;

    private final List<String> FIRST_NAMES = List.of("Liam", "Olivia", "Noah", "Emma", "Oliver", "Charlotte", "Elijah",
            "Amelia", "James", "Ava", "William", "Sophia", "Benjamin", "Isabella", "Lucas", "Mia", "Henry", "Evelyn",
            "Theodore", "Harper");
    private final List<String> LAST_NAMES = List.of("Smith", "Johnson", "Williams", "Brown", "Jones", "Garcia",
            "Miller", "Davis", "Rodriguez", "Martinez", "Hernandez", "Lopez", "Gonzalez", "Wilson", "Anderson",
            "Thomas", "Taylor", "Moore", "Jackson", "Martin");

    @Override
    public List<Student> generate() {
        List<Student> students = new ArrayList<>();
        int firstNameIndex = 0;
        int lasttNameIndex = 1;
        Random random = new Random();

        for (int i = 0; i < STUDENTS_TO_GENERATE; i++) {
            String[] names = createStudentNames();
            Student student = new Student(names[firstNameIndex], names[lasttNameIndex]);
            
            student.setGroupId(random.nextInt(1, 11));
            students.add(student);
        }
        return splitStudentsToGroups(students);
    }

    private String[] createStudentNames() {
        String[] studentFullNames = new String[2];
        int firstNameIndex = 0;
        int lasttNameIndex = 1;

        studentFullNames[firstNameIndex] = FIRST_NAMES.get(getRandom()
                .nextInt(FIRST_NAMES.size()));
        studentFullNames[lasttNameIndex] = LAST_NAMES.get(getRandom()
                .nextInt(LAST_NAMES.size()));

        return studentFullNames;
    }

    private List<Student> splitStudentsToGroups(List<Student> students) {
        int numberStudents = students.size();
        int[] sizeGroups = calculateSizeGroups(numberStudents);

        for (Student student : students) {
            for (int i = 0; i < sizeGroups.length; i++) {
                if (sizeGroups[i] != 0) {
                    student.setGroupId(i + 1);
                    sizeGroups[i]--;
                    break;
                }
            }
        }

        return students;
    }

    private int[] calculateSizeGroups(int numberStudents) {
        int[] numberStudentInGroups = new int[10];
        List<Integer> variantSizes = Stream
                .iterate(0, n -> n + 1)
                .limit(MAX_GROUP_SIZE)
                .filter(n -> n == 0 || n > MIN_GROUP_SIZE)
                .toList();
        for (int i = 0; i < numberStudentInGroups.length; i++) {
            if (numberStudents > 10) {
                numberStudentInGroups[i] = variantSizes
                        .get(getRandom()
                                .nextInt(variantSizes.size()));
            } else {
                numberStudentInGroups[i] = 0;
            }
            numberStudents -= numberStudentInGroups[i];
        }
        
        if (Arrays.stream(numberStudentInGroups)
                .anyMatch(groupSize -> groupSize == 0)) {
            numberStudentInGroups = calculateSizeGroups(numberStudents);
        }
        
        return numberStudentInGroups;
    }

}
