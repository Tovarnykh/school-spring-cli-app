package ua.foxminded.javaspring.tovarnykh.school_jdbc_api.dao;

import java.util.List;

import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;

import ua.foxminded.javaspring.tovarnykh.school_jdbc_api.exception.DAOException;

public interface Dao<T> {

    void add(T entity) throws DAOException, DataIntegrityViolationException;
    
    void addAll(List <T> entity) throws DAOException, DataIntegrityViolationException;

    T read(int id) throws DAOException, EmptyResultDataAccessException;

    List<T> readAll() throws DAOException, EmptyResultDataAccessException;

    void update(T entity) throws DAOException, DataIntegrityViolationException;

    void delete(int id) throws DAOException;

}