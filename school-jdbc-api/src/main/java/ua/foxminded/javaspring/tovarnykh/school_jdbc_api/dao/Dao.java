package ua.foxminded.javaspring.tovarnykh.school_jdbc_api.dao;

import java.util.List;

import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;

public interface Dao<T> {

    void add(T entity) throws DataIntegrityViolationException;
    
    void addAll(List <T> entity) throws DataIntegrityViolationException;

    T read(int id) throws EmptyResultDataAccessException;

    List<T> readAll() throws EmptyResultDataAccessException;

    void update(T entity) throws DataIntegrityViolationException;

    void delete(T entity) throws EmptyResultDataAccessException;

}