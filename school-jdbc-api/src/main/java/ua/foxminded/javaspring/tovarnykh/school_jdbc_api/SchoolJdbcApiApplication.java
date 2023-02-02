package ua.foxminded.javaspring.tovarnykh.school_jdbc_api;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

import ua.foxminded.javaspring.tovarnykh.school_jdbc_api.cli.Menu;

@SpringBootApplication
public class SchoolJdbcApiApplication {

    public static void main(String... args) {
        ConfigurableApplicationContext applicationContext = SpringApplication.run(SchoolJdbcApiApplication.class);
        Menu menu = applicationContext.getBean(Menu.class);
        menu.initMenu();
    }

}