package com.gmail.ksma.dao.impl;

import com.gmail.ksma.dao.UserDao;
import com.gmail.ksma.entity.Role;
import com.gmail.ksma.entity.User;
import com.gmail.ksma.exception.DaoException;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class UserDaoImplTest {
    private UserDao userDao;
    @BeforeEach
    void setUp() throws IOException{
        userDao = new UserDaoImpl();
    }
    @AfterEach
    void afterAll() throws DaoException {
        userDao.truncate();
    }
   @Test
    void insert() throws DaoException{
        User user = new User();
        user.setFirstName("test");
        user.setLastName("test2");
        user.setPassword("poss");
        user.setEmail("email");
        user.setRole(Role.MANAGER);
        userDao.insert(user);

        User actual = userDao.findById(user.getId());
        assertEquals(user, actual);
    }

}