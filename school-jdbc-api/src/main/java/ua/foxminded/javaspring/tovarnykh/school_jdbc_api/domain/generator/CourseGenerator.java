package ua.foxminded.javaspring.tovarnykh.school_jdbc_api.domain.generator;

import java.util.List;

import org.springframework.stereotype.Component;

import ua.foxminded.javaspring.tovarnykh.school_jdbc_api.entity.Course;

@Component
public class CourseGenerator implements Generator<Course> {

    public static final List<String> COURSES = List.of("Mathematics", "Science", "Language Arts", "Health", "Handwriting",
            "Physical Education", "Art", "Music", "Instrumental Music", "Dance");

    @Override
    public List<Course> generate() {
        return COURSES.stream().map(Course::new).toList();
    }

}