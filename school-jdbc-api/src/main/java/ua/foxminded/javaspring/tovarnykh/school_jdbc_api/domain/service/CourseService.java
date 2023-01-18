package ua.foxminded.javaspring.tovarnykh.school_jdbc_api.domain.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Component;

import ua.foxminded.javaspring.tovarnykh.school_jdbc_api.dao.CourseDao;
import ua.foxminded.javaspring.tovarnykh.school_jdbc_api.domain.generator.Generator;
import ua.foxminded.javaspring.tovarnykh.school_jdbc_api.entity.Course;
import ua.foxminded.javaspring.tovarnykh.school_jdbc_api.exception.DAOException;

@Component
public class CourseService {

    @Autowired
    private CourseDao courseDao;

    @Autowired
    @Qualifier("courseGenerator")
    private Generator<Course> generator;

    private static final String MESSAGE_GET_EXCEPTION = "Error: Problem with receiving courses";
    private static final String MESSAGE_ADD_EXCEPTION = "Error: Problem with adding courses";

    public void generateData() {
        try {
            if (courseDao.readAll().isEmpty()) {
                List<Course> courses = generator.generate();
                courseDao.addAll(courses);
            } else {
                System.out.println("Can`t generate data, table is not empty.");
            }
        } catch (DAOException e) {
            System.out.println(MESSAGE_GET_EXCEPTION);
        }
    }

    public void add(String name, String description) {
        try {
            courseDao.add(new Course(name, description));
        } catch (Exception e) {
            System.out.println(MESSAGE_ADD_EXCEPTION);
        }
    }

    public Course get(int courseId) {
        try {
            return courseDao.read(courseId);
        } catch (DAOException | EmptyResultDataAccessException e) {
            System.out.println(MESSAGE_GET_EXCEPTION);
            return new Course();
        }
    }

    public void update(int courseId, String name, String description) {
        try {
            courseDao.update(new Course(courseId, name, description));
        } catch (DAOException e) {
            System.out.println(MESSAGE_GET_EXCEPTION);
        }
    }

    public void delete(int courseId) {
        try {
            courseDao.delete(courseId);
        } catch (DAOException e) {
            System.out.println(MESSAGE_GET_EXCEPTION);
        }
    }

}