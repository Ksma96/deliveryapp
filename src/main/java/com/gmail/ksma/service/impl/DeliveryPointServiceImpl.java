package com.gmail.ksma.service.impl;

import com.gmail.ksma.dao.DeliveryPointDao;
import com.gmail.ksma.dao.impl.DeliveryPointDaoImpl;
import com.gmail.ksma.entity.DeliveryPoint;
import com.gmail.ksma.exception.DaoException;
import com.gmail.ksma.exception.ServiceException;
import com.gmail.ksma.service.DeliveryPointService;

import java.util.List;

public class DeliveryPointServiceImpl implements DeliveryPointService {
    private DeliveryPointDao deliveryPointDao;

    public DeliveryPointServiceImpl() {
        this.deliveryPointDao = new DeliveryPointDaoImpl();
    }

    @Override
    public List<DeliveryPoint> findAll() throws ServiceException {
        try {
            return deliveryPointDao.findAll();
        } catch (DaoException e) {
            throw new ServiceException("Unable to find all delivery_point");
        }
    }

    @Override
    public List<DeliveryPoint> findAllLimit(int first, int max) throws ServiceException {
        try {
            return deliveryPointDao.findAllLimit(first, max);
        } catch (DaoException e) {
            throw new ServiceException("Unable to find all limit delivery_point");
        }
    }

    @Override
    public DeliveryPoint findById(int id) throws ServiceException {
        try {
            return deliveryPointDao.findById(id);
        } catch (DaoException e) {
            throw new ServiceException("Unable to find by id delivery_point");
        }
    }

    @Override
    public void insert(DeliveryPoint deliveryPoint) throws ServiceException {
        try {
            deliveryPointDao.insert(deliveryPoint);
        } catch (DaoException e) {
            throw new ServiceException("Unable to insert delivery_point");
        }
    }

    @Override
    public void update(DeliveryPoint deliveryPoint) throws ServiceException {
        try {
            deliveryPointDao.update(deliveryPoint);
        } catch (DaoException e) {
            throw new ServiceException("Unable to update delivery_point");
        }
    }

    @Override
    public void remove(DeliveryPoint deliveryPoint) throws ServiceException {
        try {
            deliveryPointDao.remove(deliveryPoint);
        } catch (DaoException e) {
            throw new ServiceException("Unable to remove delivery_point");
        }
    }

    @Override
    public int count() throws ServiceException {
        try {
            return deliveryPointDao.count();
        } catch (DaoException e) {
            throw new ServiceException("Unable to count delivery_point");
        }
    }

    @Override
    public void truncate() throws ServiceException {
        try {
            deliveryPointDao.truncate();
        } catch (DaoException e) {
            throw new ServiceException("Unable to truncate delivery_point");
        }
    }
}
