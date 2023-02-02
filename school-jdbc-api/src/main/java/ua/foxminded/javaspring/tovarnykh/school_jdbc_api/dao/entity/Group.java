package ua.foxminded.javaspring.tovarnykh.school_jdbc_api.dao.entity;

import java.util.Objects;

public class Group {

    private int id;
    private String name;
    private int inscribedStudents;

    public Group() {

    }

    public Group(String name) {
        this.name = name;
    }

    public Group(int id, String name) {
        this.id = id;
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getInscribedStudents() {
        return inscribedStudents;
    }

    public void setInscribedStudents(int inscribedStudents) {
        this.inscribedStudents = inscribedStudents;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, inscribedStudents, name);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        Group other = (Group) obj;
        return id == other.id && inscribedStudents == other.inscribedStudents && Objects.equals(name, other.name);
    }

    @Override
    public String toString() {
        return "Group [id=" + id + ", name=" + name + ", inscribedStudents=" + inscribedStudents + "]";
    }

}