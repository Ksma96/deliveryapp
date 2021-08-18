package com.gmail.ksma.controller;

import com.gmail.ksma.dao.UserDao;
//import com.gmail.ksma.dao.impl.UserDaoImpl;
import com.gmail.ksma.entity.User;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;


/**
 * @author Vitalii Hainulin
 * Date: 29.07.2021
 * Time: 18:43
 */
@WebServlet("/")
public class UserServlet extends HttpServlet {
//    private UserDao userDao = new UserDaoImpl();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
//        HttpSession session = req.getSession();
//
//        List<User> users = userDao.findAll();
//
//        session.setAttribute("users", users);
//
//        RequestDispatcher requestDispatcher = getServletContext().getRequestDispatcher("/index");
//        requestDispatcher.forward(req, resp);
    }
}

