# Java CRUD Console application "School"
This is a training project of the mentoring program of the FoxmindEd training center.

According to the program, the developing of this application is consisted of several milestones. At each stage, a new application layer or functionality with required library for implementation it is added. In the final, the application is converted to Web application and some libraries is replaced.

## Proposal

"School" application manages the database of students, courses, groups and implements a crud functionality via comand line interface.

## Application design

### Entities

* Student - the person who is an attendee of lectures
* Course - certain academic subject
* Group - the group of Students with is assigned to the Courses

### How To Run

0. Stop existing pgsql in services;
1. Run **BASH** command to create database 'docker run --name school -e POSTGRES_USER=fox -e POSTGRES_PASSWORD=foxminded -e POSTGRES_DB=school -p 5432:5432 -d postgres';
2. Compile applications using this command: './mvnw package'
3. Launch application 'java -jar school-jdbc-api.jar'

## Application components

The application includes the following components:

* Build tool (**Maven**)
* IoC (**Spring, SpringBoot**)
* Unit tests (**JUnit5**)
* Database versioning (**Flyway**)
* Waay of interacting with DB (**JDBC API**)
* DB Tests (**Testcontainers**)

Database management system used for data storage: **Postgres** in runtime and **H2** for tests.