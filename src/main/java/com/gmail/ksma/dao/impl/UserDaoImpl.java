package com.gmail.ksma.dao.impl;

import com.gmail.ksma.dao.ConnectionFactory;
import com.gmail.ksma.dao.UserDao;
import com.gmail.ksma.entity.Role;
import com.gmail.ksma.entity.User;
import com.gmail.ksma.exception.DaoException;

import java.io.FileInputStream;
import java.io.IOException;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

/**
 * @author Maxim Melanich
 * Date: 29.07.2021
 * Time: 12:17
 */

public class UserDaoImpl implements UserDao {
    private final Properties properties;

    public UserDaoImpl() {
        this.properties = new Properties();
        try {
            properties.load(new FileInputStream("src/main/resources/queries.properties"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public List<User> findAll() throws DaoException {
        Connection connection = ConnectionFactory.getConnection();
        List<User> users = new ArrayList<>();
        try (PreparedStatement preparedStatement = connection.prepareStatement(properties.getProperty("user.find.all"))) {
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                User user = extractUserFromResultSet(resultSet);
                users.add(user);
            }
        } catch (SQLException e) {
            throw new DaoException("Unable to remove cargo type", e);
        }
        return users;
    }

    @Override
    public List<User> findAllLimit(int first, int max) throws DaoException {
        Connection connection = ConnectionFactory.getConnection();
        List<User> result = new ArrayList<>();
        try (PreparedStatement statement = connection.prepareStatement(properties.getProperty("user.find-all-limit"))) {
            statement.setInt(1, first);
            statement.setInt(2, max);
            try (ResultSet resultSet = statement.executeQuery()) {
                while (resultSet.next()) {
                    User user = extractUserFromResultSet(resultSet);
                    result.add(user);
                }
            }
        } catch (SQLException e) {
            throw new DaoException("Unable to remove user", e);
        }

        return result;
    }

    @Override
    public User findById(int id) throws DaoException {
        User user = null;
        Connection connection = ConnectionFactory.getConnection();
        try (PreparedStatement statement = connection.prepareStatement(properties.getProperty("user.find-by-id"))) {
            statement.setInt(1, id);
            try (ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    user = extractUserFromResultSet(resultSet);
                }
                if (user == null) {
                    throw new DaoException("Unable to get user with id " + id);
                }
                return user;
            }
        } catch (SQLException e) {
            throw new DaoException("Unable to insert user", e);
        }
    }

    @Override
    public void insert(User user) throws DaoException {
        Connection connection = ConnectionFactory.getConnection();
        try (PreparedStatement statement = connection.prepareStatement(properties.getProperty("user.insert"), Statement.RETURN_GENERATED_KEYS)) {
            statement.setString(1, user.getFirstName());
            statement.setString(2, user.getLastName());
            statement.setString(3, user.getEmail());
            statement.setString(4, user.getPassword());
            statement.setString(5, user.getRole().name());
            statement.executeUpdate();
            ResultSet generatedKeys = statement.getGeneratedKeys();
            if (generatedKeys.next()) {
                user.setId(generatedKeys.getInt(1));
            }
        } catch (SQLException e) {
            throw new DaoException("Unable to insert user", e);
        }
    }

    @Override
    public void update(User user) throws DaoException {
        Connection connection = ConnectionFactory.getConnection();
        try (PreparedStatement statement = connection.prepareStatement(properties.getProperty("user.update"))) {
            statement.setString(1, user.getFirstName());
            statement.setString(2, user.getLastName());
            statement.setString(3, user.getEmail());
            statement.setString(3, user.getPassword());
            statement.setString(4, user.getRole().name());
            statement.executeUpdate();
        } catch (SQLException e) {
            throw new DaoException("Unable to update user", e);
        }

    }

    @Override
    public void remove(User user) throws DaoException {
        Connection connection = ConnectionFactory.getConnection();
        try (PreparedStatement statement = connection.prepareStatement((properties.getProperty("user.remove")))) {
            statement.setInt(1, user.getId());
            statement.executeUpdate();
        } catch (SQLException e) {
            throw new DaoException("Unable to remove user", e);
        }
    }

    @Override
    public int count() throws DaoException {
        Connection connection = ConnectionFactory.getConnection();
        try (PreparedStatement preparedStatement = connection.prepareStatement(properties.getProperty("user.count"))) {
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                if (resultSet.next()) {
                    return resultSet.getInt(1);
                }
            }
        } catch (SQLException e) {
            throw new DaoException("Unable to count user", e);
        }
        return 0;
    }

    @Override
    public void truncate() throws DaoException {
        Connection connection = ConnectionFactory.getConnection();
        try (PreparedStatement preparedStatement = connection.prepareStatement(properties.getProperty("user.truncate"))) {
            preparedStatement.execute();
        } catch (SQLException e) {
            throw new DaoException("Unable to truncate user table");
        }
    }

    @Override
    public User findByEmail(String email) throws DaoException {
        Connection connection = ConnectionFactory.getConnection();
        try (PreparedStatement statement = connection.prepareStatement(properties.getProperty("user.find-by-email"))) {
            statement.setString(1, email);
            try (ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    return extractUserFromResultSet(resultSet);

                }
            }
        } catch (SQLException e) {
            throw new DaoException("Unable to find user by email");
        }
        return null;
    }

    private User extractUserFromResultSet(ResultSet resultSet) throws SQLException {
        User user = new User();
        user.setId(resultSet.getInt("id"));
        user.setEmail(resultSet.getString("email"));
        user.setPassword(resultSet.getString("password"));
        user.setFirstName(resultSet.getString("first_name"));
        user.setLastName(resultSet.getString("last_name"));
        user.setRole(Role.valueOf(resultSet.getString("role")));
        return user;
    }


    @Override
    public List<User> findByRole(Role role) throws DaoException {
        Connection connection = ConnectionFactory.getConnection();
        List<User> result = new ArrayList<>();
        try (PreparedStatement statement = connection.prepareStatement(properties.getProperty("user.find-by-role"))) {
            statement.setString(1, role.name());
            try (ResultSet resultSet = statement.executeQuery()) {
                while (resultSet.next()) {
                    User user = extractUserFromResultSet(resultSet);
                    result.add(user);
                }
            }
        } catch (SQLException e) {
            throw new DaoException("Unable to find users by tole");
        }
        return result;
    }
}
