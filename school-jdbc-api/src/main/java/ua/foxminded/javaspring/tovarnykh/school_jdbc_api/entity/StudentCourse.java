package ua.foxminded.javaspring.tovarnykh.school_jdbc_api.entity;

import java.util.Objects;

public class StudentCourse {
    
    private int studentId;
    private int courseID;

    public StudentCourse() {

    }

    public StudentCourse(int studentId, int courseID) {
        super();
        this.studentId = studentId;
        this.courseID = courseID;
    }

    public int getCourseID() {
        return courseID;
    }

    public void setCourseID(int courseID) {
        this.courseID = courseID;
    }

    public int getStudentId() {
        return studentId;
    }

    public void setStudentId(int studentId) {
        this.studentId = studentId;
    }

    @Override
    public int hashCode() {
        return Objects.hash(courseID, studentId);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        StudentCourse other = (StudentCourse) obj;
        return courseID == other.courseID && studentId == other.studentId;
    }

    @Override
    public String toString() {
        return "StudentCourse [studentId=" + studentId + ", courseID=" + courseID + "]";
    }

}