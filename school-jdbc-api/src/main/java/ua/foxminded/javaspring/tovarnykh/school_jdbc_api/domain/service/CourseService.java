package ua.foxminded.javaspring.tovarnykh.school_jdbc_api.domain.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import ua.foxminded.javaspring.tovarnykh.school_jdbc_api.dao.CourseDao;
import ua.foxminded.javaspring.tovarnykh.school_jdbc_api.dao.entity.Course;
import ua.foxminded.javaspring.tovarnykh.school_jdbc_api.domain.generator.Generator;

@Service
public class CourseService {

    private static final String MESSAGE_POPULATE_EXCEPTION = "Error: Problem with populating courses";
    private static final String MESSAGE_GET_EXCEPTION = "Error: Problem with receiving course";
    private static final String MESSAGE_ADD_EXCEPTION = "Error: Problem with adding course";
    private static final String MESSAGE_UPDATE_EXCEPTION = "Error: Problem with updating course";

    private CourseDao courseDao;
    private Generator<Course> generator;

    public CourseService(CourseDao courseDao, @Qualifier("courseGenerator") Generator<Course> generator) {
        this.courseDao = courseDao;
        this.generator = generator;
    }

    public void generateData() {
        try {
            List<Course> courses = generator.generate();
            courseDao.addAll(courses);
        } catch (DataIntegrityViolationException e) {
            System.out.println(MESSAGE_POPULATE_EXCEPTION);
        }
    }

    public void add(String name, String description) {
        try {
            courseDao.add(new Course(name, description));
        } catch (DataIntegrityViolationException e) {
            System.out.println(MESSAGE_ADD_EXCEPTION);
        }
    }

    public Course get(int courseId) {
        try {
            return courseDao.read(courseId);
        } catch (EmptyResultDataAccessException e) {
            System.out.println(MESSAGE_GET_EXCEPTION);
            return new Course();
        }
    }

    public List<Course> getAll() {
        try {
            return courseDao.readAll();
        } catch (EmptyResultDataAccessException e) {
            System.out.println(MESSAGE_GET_EXCEPTION);
            return List.of();
        }
    }

    public void update(int courseId, String name, String description) {
        try {
            courseDao.update(new Course(courseId, name, description));
        } catch (DataIntegrityViolationException e) {
            System.out.println(MESSAGE_UPDATE_EXCEPTION);
        }
    }

    public void delete(int courseId) {
        courseDao.delete(courseId);
    }

}