package com.gmail.ksma.dao;

import com.gmail.ksma.entity.Role;
import com.gmail.ksma.entity.User;
import com.gmail.ksma.exception.DaoException;

import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

public interface UserDao extends GenericDao<User> {

    User findByEmail(String email) throws DaoException;

    List<User> findByRole(Role role) throws DaoException;
}