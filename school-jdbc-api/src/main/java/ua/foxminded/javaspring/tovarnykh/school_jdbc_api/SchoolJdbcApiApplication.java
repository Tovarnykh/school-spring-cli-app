package ua.foxminded.javaspring.tovarnykh.school_jdbc_api;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class SchoolJdbcApiApplication implements CommandLineRunner {

    public static void main(String... args) {
        SpringApplication application = new SpringApplication(SchoolJdbcApiApplication.class);

        application.run(args);
    }

    @Override
    public void run(String... args) throws Exception {

    }

}