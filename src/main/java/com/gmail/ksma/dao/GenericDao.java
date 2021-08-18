package com.gmail.ksma.dao;

import com.gmail.ksma.entity.Recipe;
import com.gmail.ksma.exception.DaoException;

import java.sql.ResultSet;
import java.util.List;
import java.util.Optional;

/**
 * @author Maxim Melanich
 * Date: 29.07.2021
 * Time: 18:36
 */
public interface GenericDao<T> {
    List<T> findAll() throws DaoException;

    List<T> findAllLimit(int first, int max) throws DaoException;

    T findById(int id) throws DaoException;

    void insert(T t) throws DaoException;

    void update(T t) throws DaoException;

    void remove(T t) throws DaoException;

    int count() throws DaoException;

    void truncate() throws DaoException;
}

