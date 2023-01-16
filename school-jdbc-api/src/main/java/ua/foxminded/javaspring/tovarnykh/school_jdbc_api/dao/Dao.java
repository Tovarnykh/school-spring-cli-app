package ua.foxminded.javaspring.tovarnykh.school_jdbc_api.dao;

import java.util.List;
import ua.foxminded.javaspring.tovarnykh.school_jdbc_api.exception.DAOException;

public interface Dao<T> {

    void add(T entity) throws DAOException;

    T getById(int id) throws DAOException;

    List<T> getAll() throws DAOException;

    void update(T entity) throws DAOException;

    void delete(int id) throws DAOException;

}