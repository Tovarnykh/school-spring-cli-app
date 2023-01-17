package ua.foxminded.javaspring.tovarnykh.school_jdbc_api.dao;

import java.util.List;
import ua.foxminded.javaspring.tovarnykh.school_jdbc_api.exception.DAOException;

public interface Dao<T> {

    void add(T entity) throws DAOException;
    
    void addAll(List <T> entity) throws DAOException;

    T read(int id) throws DAOException;

    List<T> readAll() throws DAOException;

    void update(T entity) throws DAOException;

    void delete(int id) throws DAOException;

}