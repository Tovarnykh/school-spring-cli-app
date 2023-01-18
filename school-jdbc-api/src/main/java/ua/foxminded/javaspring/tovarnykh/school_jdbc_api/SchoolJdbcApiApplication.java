package ua.foxminded.javaspring.tovarnykh.school_jdbc_api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import ua.foxminded.javaspring.tovarnykh.school_jdbc_api.cli.Menu;

@SpringBootApplication
public class SchoolJdbcApiApplication implements CommandLineRunner {

    @Autowired
    private Menu consoleMenu;

    public static void main(String... args) {
        SpringApplication application = new SpringApplication(SchoolJdbcApiApplication.class);

        application.run(args);
    }

    @Override
    public void run(String... args) throws Exception {
        System.out.println();
        consoleMenu.initMenu();
    }

}