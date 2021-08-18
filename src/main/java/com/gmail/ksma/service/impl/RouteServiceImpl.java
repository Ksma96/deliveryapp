package com.gmail.ksma.service.impl;

import com.gmail.ksma.dao.DeliveryPointDao;
import com.gmail.ksma.dao.RouteDao;
import com.gmail.ksma.dao.impl.DeliveryPointDaoImpl;
import com.gmail.ksma.dao.impl.RouteDaoImpl;
import com.gmail.ksma.entity.Route;
import com.gmail.ksma.exception.DaoException;
import com.gmail.ksma.exception.ServiceException;
import com.gmail.ksma.service.RouteService;

import java.util.List;

public class RouteServiceImpl implements RouteService {
    private final RouteDao routeDao;
    private final DeliveryPointDao deliveryPointDao;

    public RouteServiceImpl() {
        this.deliveryPointDao = new DeliveryPointDaoImpl();
        this.routeDao = new RouteDaoImpl(deliveryPointDao);
    }

    @Override
    public List<Route> findAll() throws ServiceException {
        try {
            return routeDao.findAll();
        } catch (DaoException e) {
            throw new ServiceException("Unable to find all route");
        }
    }

    @Override
    public List<Route> findAllLimit(int first, int max) throws ServiceException {
        try {
            return routeDao.findAllLimit(first, max);
        } catch (DaoException e) {
            throw new ServiceException("Unable to find all limit route");
        }
    }

    @Override
    public Route findById(int id) throws ServiceException {
        try {
            return routeDao.findById(id);
        } catch (DaoException e) {
            throw new ServiceException("Unable to find route by id");
        }
    }

    @Override
    public void insert(Route route) throws ServiceException {
        try {
            routeDao.insert(route);
        } catch (DaoException e) {
            throw new ServiceException("Unable to insert route");
        }
    }

    @Override
    public void update(Route route) throws ServiceException {
        try {
            routeDao.update(route);
        } catch (DaoException e) {
            throw new ServiceException("Unable to update route");
        }
    }

    @Override
    public void remove(Route route) throws ServiceException {
        try {
            routeDao.remove(route);
        } catch (DaoException e) {
            throw new ServiceException("Unable to remove route");
        }
    }

    @Override
    public int count() throws ServiceException {
        try {
            return routeDao.count();
        } catch (DaoException e) {
            throw new ServiceException("Unable to count route");
        }
    }

    @Override
    public void truncate() throws ServiceException {
        try {
            routeDao.truncate();
        } catch (DaoException e) {
            throw new ServiceException("Unable to truncate route");
        }
    }
}
