package com.gmail.ksma.dao.impl;

import com.gmail.ksma.dao.ConnectionFactory;
import com.gmail.ksma.dao.DeliveryPointDao;
import com.gmail.ksma.dao.RouteDao;
import com.gmail.ksma.entity.Route;
import com.gmail.ksma.exception.DaoException;

import java.io.FileInputStream;
import java.io.IOException;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

/**
 * @author Maxim Melanich
 * Date: 04.08.2021
 * Time: 17:36
 */
public class RouteDaoImpl implements RouteDao {
    private final Properties properties;
    private final DeliveryPointDao deliveryPointDao;

    public RouteDaoImpl(DeliveryPointDao deliveryPointDao ) {
        this.properties = new Properties();
        this.deliveryPointDao = deliveryPointDao;
        try {
            properties.load(new FileInputStream("src/main/resources/queries.properties"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public List<Route> findAll() throws DaoException {
        Connection connection = ConnectionFactory.getConnection();
        List<Route> routes = new ArrayList<>();
        try (PreparedStatement preparedStatement = connection.prepareStatement(properties.getProperty("route.find-all"))) {
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                Route route = extractRouteFromResultSet(resultSet);
                routes.add(route);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return routes;
    }

    @Override
    public List<Route> findAllLimit(int first, int max) throws DaoException {
        Connection connection = ConnectionFactory.getConnection();
        List<Route> routes = new ArrayList<>();
        try (PreparedStatement statement = connection.prepareStatement(properties.getProperty("route.find-all-limit"))) {
            statement.setInt(1, first);
            statement.setInt(2, max);
            try (ResultSet resultSet = statement.executeQuery()) {
                while (resultSet.next()) {
                    Route route = extractRouteFromResultSet(resultSet);
                    routes.add(route);
                }
            }
        } catch (SQLException e) {
            throw new DaoException("Unable to remove cargo type", e);
        }
        return routes;
    }


    @Override
    public Route findById(int id) throws DaoException {
        Route route = null;
        Connection connection = ConnectionFactory.getConnection();
        try (PreparedStatement statement = connection.prepareStatement(properties.getProperty("route.find-by-id"))) {
            statement.setLong(1, id);
            try (ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    route = extractRouteFromResultSet(resultSet);
                }
                if (route == null) {
                    throw new DaoException("Unable to get cargo type with id " + id);
                }
                return route;
            }
        } catch (SQLException e) {
            throw new DaoException("Unable to find by id route", e);
        }
    }



    @Override
    public void insert(Route route) throws DaoException {
        Connection connection = ConnectionFactory.getConnection();
        try (PreparedStatement statement = connection.prepareStatement(properties.getProperty("route.insert"), Statement.RETURN_GENERATED_KEYS)) {
            statement.setInt(1, route.getId());
            statement.setInt(2, route.getFrom().getId());
            statement.setInt(3, route.getTo().getId());
            statement.executeUpdate();
        } catch (SQLException e) {
            throw new DaoException("Unable to insert route", e);
        }

    }

    @Override
    public void update(Route route) throws DaoException {
        Connection connection = ConnectionFactory.getConnection();
        try (PreparedStatement statement = connection.prepareStatement(properties.getProperty("route.update"))) {
            statement.setInt(1, route.getId());
            statement.setInt(2, route.getFrom().getId());
            statement.setInt(3, route.getTo().getId());
        } catch (SQLException e) {
            throw new DaoException("Unable to update route", e);
        }
    }

    @Override
    public void remove(Route route) throws DaoException {
        Connection connection = ConnectionFactory.getConnection();
        try (PreparedStatement statement = connection.prepareStatement((properties.getProperty("route.remove")))) {
            statement.setInt(1, route.getId());
            statement.executeUpdate();
        } catch (SQLException e) {
            throw new DaoException("Unable to remove route", e);
        }

    }

    @Override
    public int count() throws DaoException {
        Connection connection = ConnectionFactory.getConnection();
        try (PreparedStatement preparedStatement = connection.prepareStatement(properties.getProperty("route.count"))) {
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                if (resultSet.next()) {
                    return resultSet.getInt(1);
                }
            }
        } catch (SQLException e) {
            throw new DaoException("Unable to count route", e);
        }
        return 0;
    }

    @Override
    public void truncate() throws DaoException {
        Connection connection = ConnectionFactory.getConnection();
        try (PreparedStatement preparedStatement = connection.prepareStatement(properties.getProperty("route.truncate"))) {
            preparedStatement.execute();
        } catch (SQLException e) {
            throw new DaoException();
        }
    }



    private Route extractRouteFromResultSet(ResultSet resultSet) throws DaoException, SQLException {
        Route route = new Route();
        route.setId(resultSet.getInt("id"));
        route.setFrom(deliveryPointDao.findById(resultSet.getInt("delivery_point_from_id")));
        route.setTo(deliveryPointDao.findById(resultSet.getInt("delivery_point_to_id")));
        return route;
    }

}
