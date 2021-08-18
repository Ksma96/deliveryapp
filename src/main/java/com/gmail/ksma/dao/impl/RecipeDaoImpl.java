package com.gmail.ksma.dao.impl;

import com.gmail.ksma.dao.ConnectionFactory;
import com.gmail.ksma.dao.RecipeDao;
import com.gmail.ksma.entity.Recipe;
import com.gmail.ksma.exception.DaoException;

import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.*;

/**
 * @author Maxim Melanich
 * Date: 04.08.2021
 * Time: 17:37
 */
public class RecipeDaoImpl implements RecipeDao {
    private Properties properties;
    public RecipeDaoImpl(){
        this.properties = new Properties();
        try {
            properties.load(new FileInputStream("src/main/resources/queries.properties"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public List<Recipe> findAll() throws DaoException {
        Connection connection = ConnectionFactory.getConnection();
        List<Recipe> recipes = new ArrayList<>();
        try (PreparedStatement preparedStatement = connection.prepareStatement(properties.getProperty("recipe.find-all"))) {
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                Recipe recipe = extractRecipeFromResultSet(resultSet);
                recipes.add(recipe);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return recipes;
    }

    @Override
    public List<Recipe> findAllLimit(int first, int max) throws DaoException {
        Connection connection = ConnectionFactory.getConnection();
        List<Recipe> result = new ArrayList<>();
        try (PreparedStatement statement = connection.prepareStatement(properties.getProperty("recipe.find-all-limit"))) {
            statement.setInt(1, first);
            statement.setInt(2, max);
            try (ResultSet resultSet = statement.executeQuery()) {
                while (resultSet.next()) {
                    Recipe recipe = extractRecipeFromResultSet(resultSet);
                    result.add(recipe);
                }

            }
        } catch (SQLException e) {
            throw new DaoException("Unable to remove user", e);
        }
        return result;
    }


    @Override
    public Recipe extractRecipeFromResultSet(ResultSet resultSet) throws SQLException {
        Recipe recipe = new Recipe();
        recipe.setId(resultSet.getInt("id"));
        recipe.setPrice(resultSet.getLong("Price"));
        recipe.setPaid(resultSet.getBoolean("isPaid"));
        return recipe;
    }

    @Override
    public Recipe findById(int id) throws DaoException {
        Recipe recipe = null;
        Connection connection = ConnectionFactory.getConnection();
        try (PreparedStatement statement = connection.prepareStatement(properties.getProperty("recipe.find-by-id"))) {
            statement.setInt(1, id);
            try (ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    recipe = extractRecipeFromResultSet(resultSet);
                }
                if (recipe == null) {
                    throw new DaoException("Unable to get cargo type with id " + id);
                }
                return recipe;
            }
        } catch (SQLException e) {
            throw new DaoException("Unable to insert recipe", e);
        }
    }

    @Override
    public void insert(Recipe recipe) throws DaoException {
        Connection connection = ConnectionFactory.getConnection();
        try (PreparedStatement statement = connection.prepareStatement(properties.getProperty("recipe.insert"))) {
            statement.setInt(1,recipe.getId());
            statement.setLong(2, recipe.getPrice());
            statement.setBoolean(3, recipe.isPaid());
        } catch (SQLException e) {
            throw new DaoException("Unable to insert recipe", e);
        }

    }

    @Override
    public void update(Recipe recipe) throws DaoException {
        Connection connection = ConnectionFactory.getConnection();
        try (PreparedStatement statement = connection.prepareStatement(properties.getProperty("recipe.update"))) {
            statement.setInt(1, recipe.getId());
            statement.setLong(2, recipe.getPrice());
            statement.setBoolean(3, recipe.isPaid());
        } catch (SQLException e) {
            throw new DaoException("Unable to insert recipe", e);
        }
    }

    @Override
    public void remove(Recipe recipe) throws DaoException {
        Connection connection = ConnectionFactory.getConnection();
        try (PreparedStatement statement = connection.prepareStatement(properties.getProperty("recipe.remove"))) {
            statement.setInt(1, recipe.getId());
            statement.executeUpdate();
        } catch (SQLException e) {
            throw new DaoException("Unable to insert recipe", e);
        }

    }

    @Override
    public int count() throws DaoException {
        Connection connection = ConnectionFactory.getConnection();
        try (PreparedStatement preparedStatement = connection.prepareStatement(properties.getProperty("recipe.count"))) {
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                if (resultSet.next()) {
                    return resultSet.getInt(1);
                }
            }
        } catch (SQLException e) {
            throw new DaoException("Unable to insert recipe", e);
        }
        return 0;
    }

    @Override
    public void truncate() throws DaoException {
        Connection connection = ConnectionFactory.getConnection();
        try (PreparedStatement preparedStatement = connection.prepareStatement(properties.getProperty("recipe.truncate"))) {
            preparedStatement.execute();
        } catch (SQLException e) {
            throw new DaoException("Unable to truncate recipe", e);
        }
    }
}










