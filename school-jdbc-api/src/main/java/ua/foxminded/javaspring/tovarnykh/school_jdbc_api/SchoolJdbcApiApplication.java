package ua.foxminded.javaspring.tovarnykh.school_jdbc_api;

import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import ua.foxminded.javaspring.tovarnykh.school_jdbc_api.cli.Menu;
import ua.foxminded.javaspring.tovarnykh.school_jdbc_api.domain.service.CourseService;
import ua.foxminded.javaspring.tovarnykh.school_jdbc_api.domain.service.GroupService;
import ua.foxminded.javaspring.tovarnykh.school_jdbc_api.domain.service.StudentCourseService;
import ua.foxminded.javaspring.tovarnykh.school_jdbc_api.domain.service.StudentService;

@SpringBootApplication
public class SchoolJdbcApiApplication implements ApplicationRunner {

    private Menu consoleMenu;

    private GroupService groupService;
    private StudentService studentService;
    private CourseService courseService;
    private StudentCourseService studentCourseService;

    public SchoolJdbcApiApplication(Menu consoleMenu, GroupService groupService, StudentService studentService,
            CourseService courseService, StudentCourseService studentCourseService) {
        this.consoleMenu = consoleMenu;
        this.groupService = groupService;
        this.studentService = studentService;
        this.courseService = courseService;
        this.studentCourseService = studentCourseService;
    }

    public static void main(String... args) {
        SpringApplication application = new SpringApplication(SchoolJdbcApiApplication.class);

        application.run(args);
    }

    @Override
    public void run(ApplicationArguments args) throws Exception {
        populateDatabase();
        consoleMenu.initMenu();
    }

    private void populateDatabase() {
        if (groupService.getAll().isEmpty()) {
            groupService.generateData();
        }
        if (studentService.getAll().isEmpty()) {
            studentService.generateData();
        }
        if (courseService.getAll().isEmpty()) {
            courseService.generateData();
        }
        if (studentCourseService.getAllEnrolledStudents().isEmpty()) {
            studentCourseService.generateData();
        }
    }

}