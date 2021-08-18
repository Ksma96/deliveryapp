package com.gmail.ksma.dao.impl;

import com.gmail.ksma.dao.ConnectionFactory;
import com.gmail.ksma.dao.DeliveryOrderDao;
import com.gmail.ksma.dao.UserDao;
import com.gmail.ksma.entity.DeliveryOrder;
import com.gmail.ksma.entity.Role;
import com.gmail.ksma.entity.User;
import com.gmail.ksma.exception.DaoException;

import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.*;

/**
 * @author Maxim Melanich
 * Date: 04.08.2021
 * Time: 17:39
 */
public class DeliveryOrderDaoImpl implements DeliveryOrderDao {
    private final Properties properties;
    public DeliveryOrderDaoImpl()
    {
        this.properties = new Properties();
        try {
            properties.load(new FileInputStream("src/main/resources/queries.properties"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    @Override
    public List<DeliveryOrder> findAll() throws DaoException {
        Connection connection = ConnectionFactory.getConnection();
        List<DeliveryOrder> deliveryOrders = new ArrayList<>();
        try (PreparedStatement preparedStatement = connection.prepareStatement(properties.getProperty("delivery-order.find-all"))) {
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                DeliveryOrder deliveryOrder = extractDeliveryOrderFromResultSet(resultSet);
                deliveryOrders.add(deliveryOrder);
            }
        } catch (SQLException e) {
            throw new DaoException("Unable to find all deliveryOrder", e);
        }
        return deliveryOrders;
    }

    @Override
    public List<DeliveryOrder> findAllLimit(int first, int max) throws DaoException {
        Connection connection = ConnectionFactory.getConnection();
        List<DeliveryOrder> result = new ArrayList<>();
        try (PreparedStatement statement = connection.prepareStatement(properties.getProperty("delivery-order.find-all-limit"))) {
            statement.setInt(1, first);
            statement.setInt(2, max);
            try (ResultSet resultSet = statement.executeQuery()) {
                while (resultSet.next()) {
                    DeliveryOrder deliveryOrder = extractDeliveryOrderFromResultSet(resultSet);
                    result.add(deliveryOrder);
                }
            }
        } catch (SQLException e) {
            throw new DaoException("Unable to find all limit deliveryOrder", e);
        }
        return result;
    }

    @Override
    public DeliveryOrder findById(int id) throws DaoException {
        DeliveryOrder deliveryOrder = null;
        Connection connection = ConnectionFactory.getConnection();
        try (PreparedStatement statement = connection.prepareStatement(properties.getProperty("delivery-order.find-by-id"))) {
            statement.setInt(1, deliveryOrder.getId());
            try (ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    deliveryOrder = extractDeliveryOrderFromResultSet(resultSet);
                }
                if (deliveryOrder == null) {
                    throw new DaoException("Unable to get cargo type with id " + id);
                }
                return deliveryOrder;
            }
        } catch (SQLException e) {
            throw new DaoException("Unable to find by id deliveryOrder", e);
        }
    }

    @Override
    public void insert(DeliveryOrder deliveryOrder) throws DaoException {
        Connection connection = ConnectionFactory.getConnection();
        try (PreparedStatement statement = connection.prepareStatement(properties.getProperty("delivery-order.insert"))){
            statement.setInt(1, deliveryOrder.getId());
            statement.setObject(2,deliveryOrder.getDeliveryTime());
            statement.setString(3,deliveryOrder.getRole().name());
            statement.setInt(4,deliveryOrder.getUser().getId());
            statement.setLong(6,deliveryOrder.getVolume());
            statement.setLong(7,deliveryOrder.getWeight());
            statement.setString(8,deliveryOrder.getCargoType().getName());

        } catch (SQLException e) {
            throw new DaoException("Unable to insert deliveryOrder", e);
        }

    }

    @Override
    public void update(DeliveryOrder deliveryOrder) throws DaoException {
        Connection connection = ConnectionFactory.getConnection();
        try (PreparedStatement statement = connection.prepareStatement(properties.getProperty("delivery-order.update"))){
            statement.setInt(1, deliveryOrder.getId());
            statement.setObject(2,deliveryOrder.getDeliveryTime());
            statement.setString(3,deliveryOrder.getRole().name());
            statement.setObject(4,deliveryOrder.getUser());
            statement.setLong(6,deliveryOrder.getVolume());
            statement.setLong(7,deliveryOrder.getWeight());
            statement.setString(8,deliveryOrder.getCargoType().getName());

        } catch (SQLException e) {
            throw new DaoException("Unable to update deliveryOrder", e);
        }

    }

    @Override
    public void remove(DeliveryOrder deliveryOrder) throws DaoException {
        Connection connection = ConnectionFactory.getConnection();
        try (PreparedStatement statement = connection.prepareStatement(properties.getProperty("delivery-order.remove"))){
            statement.setInt(1, deliveryOrder.getId());
            statement.executeUpdate();
        } catch (SQLException e) {
            throw new DaoException("Unable to remove deliveryOrder", e);
        }

    }

    @Override
    public int count() throws DaoException {
        Connection connection = ConnectionFactory.getConnection();
        try (PreparedStatement preparedStatement = connection.prepareStatement(properties.getProperty("delivery-order.count"))) {
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                if (resultSet.next()) {
                    return resultSet.getInt(1);
                }
            }
        } catch (SQLException e) {
            throw new DaoException("Unable to count deliveryOrder", e);
        }
        return 0;
    }

    @Override
    public void truncate() throws DaoException {
        Connection connection = ConnectionFactory.getConnection();
        try (PreparedStatement preparedStatement = connection.prepareStatement(properties.getProperty("delivery-order.truncate"))) {
            preparedStatement.execute();
        } catch (SQLException e) {
            throw new DaoException();
        }

    }

    private DeliveryOrder extractDeliveryOrderFromResultSet(ResultSet resultSet) throws SQLException {
        DeliveryOrder deliveryOrder = new DeliveryOrder();
        deliveryOrder.setId(resultSet.getInt("id"));
        deliveryOrder.setRole(Role.valueOf(resultSet.getString("role")));
        deliveryOrder.setUser((User) resultSet.getObject("user"));
        deliveryOrder.setDeliveryTime((LocalDate) resultSet.getObject("deliveryTime"));
        deliveryOrder.setVolume(resultSet.getLong("Volume"));
        deliveryOrder.setWeight(resultSet.getLong("Weight"));
        return deliveryOrder;
    }
}
