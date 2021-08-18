package com.gmail.ksma.dao.impl;

import com.gmail.ksma.dao.CargoTypeDao;
import com.gmail.ksma.dao.ConnectionFactory;
import com.gmail.ksma.entity.CargoType;
import com.gmail.ksma.entity.Recipe;
import com.gmail.ksma.exception.DaoException;

import java.io.FileInputStream;
import java.io.IOException;
import java.sql.*;
import java.util.*;

/**
 * @author Maxim Melanich
 * Date: 04.08.2021
 * Time: 17:39
 */
public class CargoTypeDaoImpl implements CargoTypeDao {
    private final Properties properties;


    public CargoTypeDaoImpl() {
        this.properties = new Properties();
        try {
            properties.load(new FileInputStream("src/main/resources/queries.properties"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public List<CargoType> findAll() throws DaoException {
        Connection connection = ConnectionFactory.getConnection();
        List<CargoType> cargoes = new ArrayList<>();
        try (PreparedStatement preparedStatement = connection.prepareStatement(properties.getProperty("cargo-type.find-all"))) {
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                CargoType cargoType = extractCargoTypeFromResultSet(resultSet);
                cargoes.add(cargoType);
            }
        } catch (SQLException e) {
            throw new DaoException("Unable to get all cargo types");
        }
        return cargoes;
    }

    @Override
    public List<CargoType> findAllLimit(int first, int max) throws DaoException {
        Connection connection = ConnectionFactory.getConnection();
        List<CargoType> result = new ArrayList<>();
        try (PreparedStatement statement = connection.prepareStatement(properties.getProperty("cargo-type.find-all-limit"))) {
            statement.setInt(1, first);
            statement.setInt(2, max);
            try (ResultSet resultSet = statement.executeQuery()) {
                while (resultSet.next()) {
                    CargoType cargotype = extractCargoTypeFromResultSet(resultSet);
                    result.add(cargotype);
                }
                return result;
            }
        } catch (SQLException e) {
            throw new DaoException("Unable to find all limit cargo type" + findAllLimit(first, max));

        }
    }

    @Override
    public CargoType findById(int id) throws DaoException {
        Connection connection = ConnectionFactory.getConnection();
        CargoType cargoType = null;
        try (PreparedStatement statement = connection.prepareStatement(properties.getProperty("cargo-type.find-by-id"))) {
            statement.setInt(1, id);
            try (ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    cargoType = extractCargoTypeFromResultSet(resultSet);
                }
            }
            if (cargoType == null) {
                throw new DaoException("Unable to get cargo type with id " + id);
            }
            return cargoType;
        } catch (SQLException e) {
            throw new DaoException("Unable to get cargo type with id " + id);
        }
    }


    @Override
    public void insert(CargoType cargoType) throws DaoException {
        Connection connection = ConnectionFactory.getConnection();
        try (PreparedStatement statement = connection.prepareStatement(properties.getProperty("cargo-type.insert"), Statement.RETURN_GENERATED_KEYS)) {
            statement.setString(1, cargoType.getName());
            statement.executeUpdate();
            ResultSet generatedKeys = statement.getGeneratedKeys();
            if (generatedKeys.next()) {
                cargoType.setId(generatedKeys.getInt(1));
            }
        } catch (SQLException e) {
            throw new DaoException("Unable to insert cargo type", e);
        }
    }

    @Override
    public void update(CargoType cargoType) throws DaoException {
        Connection connection = ConnectionFactory.getConnection();
        try (PreparedStatement statement = connection.prepareStatement(properties.getProperty("cargo-type.update"))) {
            statement.setInt(1, cargoType.getId());
            statement.setString(2, cargoType.getName());
            statement.executeUpdate();
        } catch (SQLException e) {
            throw new DaoException("Unable to update cargo type", e);
        }
    }

    @Override
    public void remove(CargoType cargoType) throws DaoException {
        Connection connection = ConnectionFactory.getConnection();
        try (PreparedStatement statement = connection.prepareStatement(properties.getProperty("cargo-type.remove"))) {
            statement.setInt(1, cargoType.getId());
            statement.executeUpdate();

        } catch (SQLException e) {
            throw new DaoException("Unable to remove cargo type", e);

        }
    }

    @Override
    public int count() throws DaoException {
        Connection connection = ConnectionFactory.getConnection();
        try (PreparedStatement statement = connection.prepareStatement(properties.getProperty("cargo-type.count"))) {
            try (ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    return resultSet.getInt(1);
                }
            }
        } catch (SQLException e) {
            throw new DaoException("Unable to count cargo type");

        }
        return 0;
    }

    @Override
    public void truncate() throws DaoException {
        Connection connection = ConnectionFactory.getConnection();
        try (PreparedStatement preparedStatement = connection.prepareStatement(properties.getProperty("cargo-type.truncate"))) {
            preparedStatement.execute();
        } catch (SQLException e) {
            throw new DaoException("Unable to truncate cargo_type table");
        }
    }

    private CargoType extractCargoTypeFromResultSet(ResultSet resultSet) throws SQLException {
        CargoType cargoType = new CargoType();
        cargoType.setId(resultSet.getInt("id"));
        cargoType.setName(resultSet.getString("name"));
        return cargoType;
    }
}
