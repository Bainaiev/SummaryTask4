package ua.nure.bainaiev.SummaryTask4.repository.impl;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ua.nure.bainaiev.SummaryTask4.db.holder.ConnectionHolder;
import ua.nure.bainaiev.SummaryTask4.exception.DataAccessException;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Provides a base functionality for all repositories.
 */
public abstract class AbstractRepository<T> {

    public static final Logger LOGGER = LoggerFactory.getLogger(AbstractRepository.class);
    protected static final String ERROR_MESSAGE = "Cannot handle sql ['{}']; Message: ";
    private final ConnectionHolder connectionHolder;

    /**
     * Creates a new repository.
     *
     * @param connectionHolder connection holder
     */
    public AbstractRepository(ConnectionHolder connectionHolder) {
        this.connectionHolder = connectionHolder;
    }


    /**
     * Gets a connection from connection holder.
     *
     * @return connection from connection holder
     */
    protected Connection getConnection() {
        return connectionHolder.get();
    }

    protected boolean delete(int id, String sql) {
        try (PreparedStatement ps = getConnection().prepareStatement(sql)) {
            ps.setInt(1, id);
            return id != 0 && ps.executeUpdate() != 0;
        } catch (SQLException e) {
            LOGGER.warn(ERROR_MESSAGE, sql, e);
            throw new DataAccessException(getMessage(sql), e);
        }
    }

    protected boolean delete(int param1, int param2, String sql) {
        try (PreparedStatement ps = getConnection().prepareStatement(sql)) {
            ps.setInt(1, param1);
            ps.setInt(2, param2);
            return param1 != 0 && param2 != 0 && ps.executeUpdate() != 0;
        } catch (SQLException e) {
            LOGGER.warn(ERROR_MESSAGE, sql, e);
            throw new DataAccessException(getMessage(sql), e);
        }
    }

    protected T get(int id, String sql) {
        try (PreparedStatement ps = getConnection().prepareStatement(sql)) {
            T entity = null;
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                entity = extractFromResultSet(rs);
            }
            return entity;
        } catch (SQLException e) {
            LOGGER.warn(ERROR_MESSAGE, sql, e);
            throw new DataAccessException(getMessage(sql), e);
        }
    }

    protected T get(int param1, int param2, String sql) {
        try (PreparedStatement ps = getConnection().prepareStatement(sql)) {
            T entity = null;
            ps.setInt(1, param1);
            ps.setInt(2, param2);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                entity = extractFromResultSet(rs);
            }
            return entity;
        } catch (SQLException e) {
            LOGGER.warn(ERROR_MESSAGE, sql, e);
            throw new DataAccessException(getMessage(sql), e);
        }
    }

    protected abstract T extractFromResultSet(ResultSet rs) throws SQLException;


    protected List<T> getAll(int id, String sql) {
        try (PreparedStatement ps = getConnection().prepareStatement(sql)) {
            List<T> list = new ArrayList<>();
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            T entity;
            while (rs.next()) {
                entity = extractFromResultSet(rs);
                list.add(entity);
            }
            return list;
        } catch (SQLException e) {
            LOGGER.warn(ERROR_MESSAGE, sql, e);
            throw new DataAccessException(getMessage(sql), e);
        }
    }

    protected boolean addEnum(int id, String value, String sql) {
        try (PreparedStatement ps = getConnection().prepareStatement(sql)) {
            ps.setInt(1, id);
            ps.setString(2, value);

            return ps.executeUpdate() != 0;
        } catch (SQLException e) {
            LOGGER.warn(ERROR_MESSAGE, sql, e);
            throw new DataAccessException(getMessage(sql), e);
        }
    }

    /**
     * Generates a message for {@link ua.nure.bainaiev.SummaryTask4.exception.DataAccessException}
     *
     * @param sql query that has not been executed properly
     * @return generated message
     */
    protected String getMessage(String sql) {
        return "Cannot handle sql ['" + sql + "']";
    }


}
