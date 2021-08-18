package com.gmail.ksma.service.impl;

import com.gmail.ksma.dao.UserDao;
import com.gmail.ksma.dao.impl.UserDaoImpl;
import com.gmail.ksma.entity.User;
import com.gmail.ksma.exception.DaoException;
import com.gmail.ksma.exception.ServiceException;
import com.gmail.ksma.service.UserService;

import java.util.List;

public class UserServiceImpl implements UserService {
    private final UserDao userDao;

    public UserServiceImpl() {
        this.userDao = new UserDaoImpl();
    }

    @Override
    public List<User> findAll() throws ServiceException {
        try {
            return userDao.findAll();
        } catch (DaoException e) {
            throw new ServiceException("Unable to find all users");
        }
    }

    @Override
    public List<User> findAllLimit(int first, int max) throws ServiceException {
        try {
            return userDao.findAllLimit(0, 1);
        } catch (DaoException e) {
            throw new ServiceException("Unable to find all limit");
        }
    }

    @Override
    public User findById(int id) throws ServiceException {
        try {
            return userDao.findById(1);
        } catch (DaoException e) {
            throw new ServiceException("Unable to find by id");
        }
    }

    @Override
    public void insert(User user) throws ServiceException {
        try {
            userDao.insert(user);
        } catch (DaoException e) {
            throw new ServiceException("Unable to insert");
        }
    }

    @Override
    public void update(User user) throws ServiceException {
        try {
            userDao.update(user);
        } catch (DaoException e) {
            throw new ServiceException("Unable to update");
        }

    }

    @Override
    public void remove(User user) throws ServiceException {
        try {
             userDao.remove(user);
        } catch (DaoException e) {
            throw new ServiceException("Unable to remove");
        }

    }

    @Override
    public int count() throws ServiceException {
        try {
            return userDao.count();
        } catch (DaoException e) {
            throw new ServiceException("Unable to count");
        }
    }

    @Override
    public void truncate() throws ServiceException {
        try {
            userDao.truncate();
        } catch (DaoException e) {
            throw new ServiceException("Unable to truncate");
        }
    }
}
