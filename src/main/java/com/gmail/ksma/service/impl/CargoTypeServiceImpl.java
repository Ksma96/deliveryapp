package com.gmail.ksma.service.impl;

import com.gmail.ksma.dao.CargoTypeDao;
import com.gmail.ksma.dao.impl.CargoTypeDaoImpl;
import com.gmail.ksma.entity.CargoType;
import com.gmail.ksma.exception.DaoException;
import com.gmail.ksma.exception.ServiceException;
import com.gmail.ksma.service.CargoTypeService;

import java.util.List;

public class CargoTypeServiceImpl implements CargoTypeService {
    private final CargoTypeDao cargoTypeDao;

    public CargoTypeServiceImpl() {
        this.cargoTypeDao = new CargoTypeDaoImpl();
    }

    @Override
    public List<CargoType> findAll() throws ServiceException {
        try {
            return cargoTypeDao.findAll();
        } catch (DaoException e) {
            throw new ServiceException("Unable to find all cargo_type");
        }
    }

    @Override
    public List<CargoType> findAllLimit(int first, int max) throws ServiceException {
        try {
            return cargoTypeDao.findAllLimit(first, max);
        } catch (DaoException e) {
            throw new ServiceException("Unable to find all limit cargo_type");
        }
    }

    @Override
    public CargoType findById(int id) throws ServiceException {
        try {
            return cargoTypeDao.findById(id);
        } catch (DaoException e) {
            throw new ServiceException("Unable to find by id cargo_type");
        }    }

    @Override
    public void insert(CargoType cargoType) throws ServiceException {
        try {
            cargoTypeDao.insert(cargoType);
        } catch (DaoException e) {
            throw new ServiceException("Unable to insert cargo_type");
        }
    }

    @Override
    public void update(CargoType cargoType) throws ServiceException {
        try {
            cargoTypeDao.update(cargoType);
        } catch (DaoException e) {
            throw new ServiceException("Unable to update cargo_type");
        }
    }

    @Override
    public void remove(CargoType cargoType) throws ServiceException {
        try {
            cargoTypeDao.remove(cargoType);
        } catch (DaoException e) {
            throw new ServiceException("Unable to remove cargo_type");
        }
    }

    @Override
    public int count() throws ServiceException {
        try {
            return  cargoTypeDao.count();
        } catch (DaoException e) {
            throw new ServiceException("Unable to count cargo_type");
        }
    }

    @Override
    public void truncate() throws ServiceException {
        try {
             cargoTypeDao.count();
        } catch (DaoException e) {
            throw new ServiceException("Unable to truncate cargo_type");
        }
    }
}
