package ua.foxminded.javaspring.tovarnykh.school_jdbc_api;


import static org.junit.Assert.*;

import java.io.ByteArrayInputStream;
import java.io.InputStream;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.DynamicPropertyRegistry;
import org.springframework.test.context.DynamicPropertySource;
import org.testcontainers.containers.PostgreSQLContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;

import ua.foxminded.javaspring.tovarnykh.school_jdbc_api.domain.service.StudentService;

@Testcontainers
@SpringBootTest
class SchoolJdbcApiApplicationTests {
    
//    @Container
//    public static PostgreSQLContainer<?> container = new PostgreSQLContainer<>("postgres 16")
//    .withUsername("guest")
//    .withPassword("foxminded")
//    .withDatabaseName("school");
//    
//    @DynamicPropertySource
//    static void poperties(DynamicPropertyRegistry registry) {
//        registry.add("spring.datasource.url", container::getJdbcUrl);
//        registry.add("spring.datasource.password", container::getPassword);
//        registry.add("spring.datasource.username", container::getUsername);
//    }
//    
    @Autowired
    StudentService studentService;
    
	@Test
	void contextLoads() {
	    
	    studentService.add(1, "V", "v");
	    
	    //assertNotEquals(studentService.get(200).getId(), 0);
	    
	}

}
