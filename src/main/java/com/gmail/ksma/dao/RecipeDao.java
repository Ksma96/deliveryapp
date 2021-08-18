package com.gmail.ksma.dao;

import com.gmail.ksma.dao.GenericDao;
import com.gmail.ksma.entity.Recipe;
import com.gmail.ksma.exception.DaoException;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Optional;


/**
 * @author Maxim Melanich
 * Date: 04.08.2021
 * Time: 17:25
 */
public interface RecipeDao extends GenericDao<Recipe> {

    Recipe extractRecipeFromResultSet(ResultSet resultSet) throws SQLException;
}
