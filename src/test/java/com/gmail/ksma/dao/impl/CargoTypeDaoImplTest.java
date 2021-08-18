package com.gmail.ksma.dao.impl;

import com.gmail.ksma.dao.CargoTypeDao;
import com.gmail.ksma.entity.CargoType;
import com.gmail.ksma.exception.DaoException;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.List;
import java.util.Properties;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

/**
 * @author Maxim Melanich
 * Date: 08.08.2021
 * Time: 11:10
 */
class CargoTypeDaoImplTest {
    private CargoTypeDao cargoTypeDao;

    @BeforeEach
    void setUp() throws IOException {
        Properties properties = new Properties();
        properties.load(new FileInputStream("src/main/resources/queries.properties"));
        cargoTypeDao = new CargoTypeDaoImpl();
    }

    @AfterEach
    void afterAll() throws DaoException {
        cargoTypeDao.truncate();
    }

    @Test
    void insert() throws DaoException {
        CargoType cargoType = new CargoType("test");
        cargoTypeDao.insert(cargoType);

        CargoType actual = cargoTypeDao.findById(cargoType.getId());
        assertEquals(cargoType, actual);
    }

    @Test
    void findAllLimit() throws DaoException {
        insertTwoCargoTypes();
        List<CargoType> actual = cargoTypeDao.findAllLimit(0, 2);
        assertEquals(2, actual.size());
    }

    @Test
    void remove() throws DaoException {
        CargoType cargoType = new CargoType("Test");
        cargoTypeDao.insert(cargoType);
        CargoType actual = cargoTypeDao.findById(cargoType.getId());
        assertEquals(cargoType, actual);
        cargoTypeDao.remove(cargoType);
        DaoException daoException = assertThrows(DaoException.class, () -> cargoTypeDao.findById(cargoType.getId()));
        String expectedMessage = "Unable to get cargo type with id " + cargoType.getId();
        assertEquals(expectedMessage, daoException.getMessage());


    }

    private void insertTwoCargoTypes() throws DaoException {
        CargoType cargoType1 = new CargoType(1, "test");
        CargoType cargoType2 = new CargoType(2, "test2");
        cargoTypeDao.insert(cargoType1);
        cargoTypeDao.insert(cargoType2);
    }
}