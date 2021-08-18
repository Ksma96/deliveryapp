package com.gmail.ksma.dao;
import com.gmail.ksma.entity.Role;

import java.sql.SQLException;
import java.util.Optional;


/**
 * @author Maxim Melanich
 * Date: 08.08.2021
 * Time: 10:42
 */
public interface RoleDao extends GenericDao<Role> {
    Optional<Role> findByName(String name) throws SQLException;
}
