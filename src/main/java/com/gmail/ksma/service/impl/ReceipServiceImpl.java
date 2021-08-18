package com.gmail.ksma.service.impl;

import com.gmail.ksma.dao.RecipeDao;
import com.gmail.ksma.dao.impl.RecipeDaoImpl;
import com.gmail.ksma.dao.impl.UserDaoImpl;
import com.gmail.ksma.entity.Recipe;
import com.gmail.ksma.exception.DaoException;
import com.gmail.ksma.exception.ServiceException;
import com.gmail.ksma.service.ReceipService;

import java.util.List;

public class ReceipServiceImpl implements ReceipService {
    private final RecipeDao recipeDao;

    public ReceipServiceImpl() {
        this.recipeDao = new RecipeDaoImpl();
    }


    @Override
    public List<Recipe> findAll() throws ServiceException {
        try {
            return recipeDao.findAll();
        } catch (DaoException e) {
            throw new ServiceException("Unable to find all recipe");
        }
    }

    @Override
    public List<Recipe> findAllLimit(int first, int max) throws ServiceException {
        try {
            return recipeDao.findAllLimit(first, max);
        } catch (DaoException e) {
            throw new ServiceException("Unable to find all limit recipe");
        }
    }

    @Override
    public Recipe findById(int id) throws ServiceException {
        try {
            return recipeDao.findById(id);
        } catch (DaoException e) {
            throw new ServiceException("Unable to find by id recipe");
        }
    }

    @Override
    public void insert(Recipe recipe) throws ServiceException {
        try {
            recipeDao.insert(recipe);
        } catch (DaoException e) {
            throw new ServiceException("Unable to insert recipe");
        }
    }

    @Override
    public void update(Recipe recipe) throws ServiceException {
        try {
            recipeDao.update(recipe);
        } catch (DaoException e) {
            throw new ServiceException("Unable to update recipe");
        }
    }

    @Override
    public void remove(Recipe recipe) throws ServiceException {
        try {
            recipeDao.remove(recipe);
        } catch (DaoException e) {
            throw new ServiceException("Unable to remove recipe");
        }
    }

    @Override
    public int count() throws ServiceException {
        try {
            return recipeDao.count();
        } catch (DaoException e) {
            throw new ServiceException("Unable to count recipe");
        }
    }

    @Override
    public void truncate() throws ServiceException {
        try {
             recipeDao.truncate();
        } catch (DaoException e) {
            throw new ServiceException("Unable to tr recipe");
        }
    }
}
