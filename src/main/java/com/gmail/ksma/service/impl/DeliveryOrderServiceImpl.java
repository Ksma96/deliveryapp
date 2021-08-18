package com.gmail.ksma.service.impl;

import com.gmail.ksma.dao.DeliveryOrderDao;
import com.gmail.ksma.dao.impl.DeliveryOrderDaoImpl;
import com.gmail.ksma.entity.DeliveryOrder;
import com.gmail.ksma.exception.DaoException;
import com.gmail.ksma.exception.ServiceException;
import com.gmail.ksma.service.DeliveryOrderService;

import java.util.List;

public class DeliveryOrderServiceImpl implements DeliveryOrderService {
    private DeliveryOrderDao deliveryOrderDao;

    public DeliveryOrderServiceImpl() {
        this.deliveryOrderDao = new DeliveryOrderDaoImpl();
    }

    @Override
    public List<DeliveryOrder> findAll() throws ServiceException {
        try {
            return deliveryOrderDao.findAll();
        } catch (DaoException e) {
            throw new ServiceException("Unable to find all delivery_order");
        }
    }

    @Override
    public List<DeliveryOrder> findAllLimit(int first, int max) throws ServiceException {
        try {
            return deliveryOrderDao.findAllLimit(first, max);
        } catch (DaoException e) {
            throw new ServiceException("Unable to find all limit delivery_order");
        }
    }

    @Override
    public DeliveryOrder findById(int id) throws ServiceException {
        try {
            return deliveryOrderDao.findById(id);
        } catch (DaoException e) {
            throw new ServiceException("Unable to find by id delivery_order");
        }

    }

    @Override
    public void insert(DeliveryOrder deliveryOrder) throws ServiceException {
        try {
            deliveryOrderDao.insert(deliveryOrder);
        } catch (DaoException e) {
            throw new ServiceException("Unable to insert delivery_order");
        }

    }

    @Override
    public void update(DeliveryOrder deliveryOrder) throws ServiceException {
        try {
            deliveryOrderDao.update(deliveryOrder);
        } catch (DaoException e) {
            throw new ServiceException("Unable to update delivery_order");
        }

    }

    @Override
    public void remove(DeliveryOrder deliveryOrder) throws ServiceException {
        try {
            deliveryOrderDao.remove(deliveryOrder);
        } catch (DaoException e) {
            throw new ServiceException("Unable to remove delivery_order");
        }

    }

    @Override
    public int count() throws ServiceException {
        try {
            return deliveryOrderDao.count();
        } catch (DaoException e) {
            throw new ServiceException("Unable to count delivery_order");
        }
    }

    @Override
    public void truncate() throws ServiceException {
        try {
             deliveryOrderDao.truncate();
        } catch (DaoException e) {
            throw new ServiceException("Unable to truncate delivery_order");
        }
    }
}
