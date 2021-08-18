package com.gmail.ksma.dao.impl;

import com.gmail.ksma.dao.ConnectionFactory;
import com.gmail.ksma.dao.DeliveryPointDao;
import com.gmail.ksma.entity.DeliveryPoint;
import com.gmail.ksma.exception.DaoException;

import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

/**
 * @author Maxim Melanich
 * Date: 04.08.2021
 * Time: 17:38
 */
public class DeliveryPointDaoImpl implements DeliveryPointDao {
    private Properties properties;

    public DeliveryPointDaoImpl() {
        this.properties = new Properties();
        try {
            properties.load(new FileInputStream("src/main/resources/queries.properties"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public List<DeliveryPoint> findAll() throws DaoException {
        Connection connection = ConnectionFactory.getConnection();
        List<DeliveryPoint> deliveryPoints = new ArrayList<>();
        try (PreparedStatement preparedStatement = connection.prepareStatement(properties.getProperty("deliveryPoint.find-all"))) {
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                DeliveryPoint deliveryPoint = extractDeliveryPointFromResultSet(resultSet);
                deliveryPoints.add(deliveryPoint);
            }
        } catch (SQLException e) {
            throw new DaoException("Unable to find all delivery point", e);
        }

        return deliveryPoints;
    }

    @Override
    public List<DeliveryPoint> findAllLimit(int first, int max) throws DaoException {
        Connection connection = ConnectionFactory.getConnection();
        List<DeliveryPoint> result = new ArrayList<>();
        try (PreparedStatement statement = connection.prepareStatement(properties.getProperty("deliveryPoint.find-all-limit"))) {
            statement.setInt(1, first);
            statement.setInt(2, max);
            try (ResultSet resultSet = statement.executeQuery()) {
                while (resultSet.next()) {
                    DeliveryPoint deliveryPoint = extractDeliveryPointFromResultSet(resultSet);
                    result.add(deliveryPoint);
                }
            }
        } catch (SQLException e) {
            throw new DaoException("Unable to find all limit user", e);
        }

        return result;
    }

    @Override
    public DeliveryPoint findById(int id) throws DaoException {
        DeliveryPoint deliveryPoint = null;
        Connection connection = ConnectionFactory.getConnection();
        try (PreparedStatement statement = connection.prepareStatement(properties.getProperty("deliveryPoint.find-by-id"))) {
            statement.setInt(1, id);
            try (ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    deliveryPoint = extractDeliveryPointFromResultSet(resultSet);
                }
                if (deliveryPoint == null) {
                    throw new DaoException("Unable to get deliveryPoint with id " + id);
                }
                return deliveryPoint;
            }
        } catch (SQLException e) {
            throw new DaoException("Unable to find by id deliveryPoint", e);
        }
    }

    @Override
    public void insert(DeliveryPoint deliveryPoint) throws DaoException {
        Connection connection = ConnectionFactory.getConnection();
        try (PreparedStatement statement = connection.prepareStatement(properties.getProperty("deliveryPoint.insert"))) {
            statement.setString(1, deliveryPoint.getAddress());
            statement.setInt(2, deliveryPoint.getId());
            statement.executeUpdate();
        } catch (SQLException e) {
            throw new DaoException("Unable to insert deliveryPoint", e);
        }
    }

    @Override
    public void update(DeliveryPoint deliveryPoint) throws DaoException {
        Connection connection = ConnectionFactory.getConnection();
        try (PreparedStatement statement = connection.prepareStatement(properties.getProperty("deliveryPoint.update"))) {
            statement.setInt(1, deliveryPoint.getId());
            statement.setString(2, deliveryPoint.getAddress());
            statement.executeUpdate();
        } catch (SQLException e) {
            throw new DaoException("Unable to update deliveryPoint", e);
        }

    }

    @Override
    public void remove(DeliveryPoint deliveryPoint) throws DaoException {
        Connection connection = ConnectionFactory.getConnection();
        try (PreparedStatement statement = connection.prepareStatement(properties.getProperty("deliveryPoint.remove"))) {
            statement.setInt(1, deliveryPoint.getId());
            statement.executeUpdate();
        } catch (SQLException e) {
            throw new DaoException("Unable to remove deliveryPoint", e);
        }

    }

    @Override
    public int count() throws DaoException {
        Connection connection = ConnectionFactory.getConnection();
        try (PreparedStatement preparedStatement = connection.prepareStatement(properties.getProperty("deliveryPoint.count"))) {
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                if (resultSet.next()) {
                    return resultSet.getInt(1);
                }
            }
        } catch (SQLException e) {
            throw new DaoException("Unable to count deliveryPoint", e);
        }
        return 0;
    }

    @Override
    public void truncate() throws DaoException {
        Connection connection = ConnectionFactory.getConnection();
        try (PreparedStatement preparedStatement = connection.prepareStatement(properties.getProperty("deliveryPoint.truncate"))) {
            preparedStatement.execute();
        } catch (SQLException e) {
            throw new DaoException("Unable to truncate deliveryPoint", e);
        }
    }

    private DeliveryPoint extractDeliveryPointFromResultSet(ResultSet resultSet) throws SQLException {

        DeliveryPoint deliveryPoint = new DeliveryPoint();
        deliveryPoint.setId(resultSet.getInt("id"));
        deliveryPoint.setAddress(resultSet.getString("address"));
        return deliveryPoint;
    }
}
