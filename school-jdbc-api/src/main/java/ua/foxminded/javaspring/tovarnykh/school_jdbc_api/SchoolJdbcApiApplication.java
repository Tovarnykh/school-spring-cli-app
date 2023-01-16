package ua.foxminded.javaspring.tovarnykh.school_jdbc_api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import ua.foxminded.javaspring.tovarnykh.school_jdbc_api.dao.jdbc.JdbcCourseDao;

@SpringBootApplication
public class SchoolJdbcApiApplication implements CommandLineRunner {

    public static void main(String[] args) {
        SpringApplication application = new SpringApplication(SchoolJdbcApiApplication.class);

        application.run(args);
    }

    @Autowired
    JdbcCourseDao jdbcCourseDao;

    @Override
    public void run(String... args) throws Exception {
        jdbcCourseDao.delete(4);
    }

}