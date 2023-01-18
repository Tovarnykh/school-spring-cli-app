DROP TABLE IF EXISTS groups CASCADE;
CREATE TABLE groups
(
    group_id SERIAL PRIMARY KEY,
    group_name character varying(5) NOT NULL,
    UNIQUE(group_name)
);

DROP TABLE IF EXISTS students CASCADE;
CREATE TABLE students
(
    student_id SERIAL PRIMARY KEY,
    group_id integer,
    first_name character varying(20) NOT NULL,
    last_name character varying(25) NOT NULL,
    FOREIGN KEY (group_id) 
    REFERENCES groups(group_id) ON DELETE SET NULL
);

DROP TABLE IF EXISTS courses CASCADE;
CREATE TABLE courses
(
    course_id SERIAL PRIMARY KEY,
    course_name character varying(20) NOT NULL,
    course_description TEXT,
    UNIQUE (course_name)
);

DROP TABLE IF EXISTS students_courses CASCADE;
CREATE TABLE students_courses
(
    student_id INTEGER NOT NULL,
    course_id INTEGER NOT NULL,
    FOREIGN KEY(student_id) REFERENCES students(student_id) ON DELETE CASCADE,
    FOREIGN KEY(course_id) REFERENCES courses(course_id),
    PRIMARY KEY(student_id, course_id)
);