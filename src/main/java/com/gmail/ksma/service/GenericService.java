package com.gmail.ksma.service;

import com.gmail.ksma.exception.ServiceException;

import java.util.List;

public interface GenericService<T> {

    List<T> findAll() throws ServiceException;

    List<T> findAllLimit(int first, int max) throws ServiceException;

    T findById(int id) throws ServiceException;

    void insert(T t) throws ServiceException;

    void update(T t) throws ServiceException;

    void remove(T t) throws ServiceException;

    int count() throws ServiceException;

    void truncate() throws ServiceException;
}
