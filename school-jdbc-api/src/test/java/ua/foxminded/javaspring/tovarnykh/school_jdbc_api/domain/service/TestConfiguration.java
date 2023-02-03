package ua.foxminded.javaspring.tovarnykh.school_jdbc_api.domain.service;

import org.mockito.Mockito;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.context.annotation.Profile;

import ua.foxminded.javaspring.tovarnykh.school_jdbc_api.dao.CourseDao;
import ua.foxminded.javaspring.tovarnykh.school_jdbc_api.dao.GroupDao;
import ua.foxminded.javaspring.tovarnykh.school_jdbc_api.dao.StudentCourseDao;
import ua.foxminded.javaspring.tovarnykh.school_jdbc_api.dao.StudentDao;

@Profile("test")
@Configuration
public class TestConfiguration {

    @Bean
    @Primary
    CourseDao courseDao() {
        return Mockito.mock(CourseDao.class);
    }

    @Bean
    @Primary
    GroupDao groupDao() {
        return Mockito.mock(GroupDao.class);
    }

    @Bean
    @Primary
    StudentDao studentDao() {
        return Mockito.mock(StudentDao.class);
    }

    @Bean
    @Primary
    StudentCourseDao studentCourseDao() {
        return Mockito.mock(StudentCourseDao.class);
    }

}