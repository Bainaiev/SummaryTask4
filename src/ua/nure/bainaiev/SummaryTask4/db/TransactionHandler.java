package ua.nure.bainaiev.SummaryTask4.db;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import ua.nure.bainaiev.SummaryTask4.annotation.Transactional;
import ua.nure.bainaiev.SummaryTask4.db.holder.ConnectionHolder;
import ua.nure.bainaiev.SummaryTask4.db.manager.ConnectionManager;
import ua.nure.bainaiev.SummaryTask4.exception.DataAccessException;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.sql.Connection;
import java.sql.SQLException;

public class TransactionHandler implements InvocationHandler {
    private static final Logger LOGGER = LoggerFactory.getLogger(TransactionHandler.class);

    private static final String COMMIT_FAILED = "Commit failed";
    private static final String INVOCATION_FAILED = "Invocation failed";
    private static final String ROLLBACK_FAILED = "Rollback failed";
    private static final String CLOSING_CONNECTION_FAILED = "Closing connection failed";

    private final ConnectionManager connectionManager;
    private final ConnectionHolder connectionHolder;
    private final Object serviceToInvoke;


    /**
     * Creates a new transaction handler.
     *
     * @param holder          connection holder
     * @param serviceToInvoke service to invoke
     * @param manager         connection manager
     */
    public TransactionHandler(ConnectionManager manager, ConnectionHolder holder, Object serviceToInvoke) {
        this.connectionManager = manager;
        this.serviceToInvoke = serviceToInvoke;
        this.connectionHolder = holder;
    }


    @Override
    public Object invoke(Object proxy, Method method, Object[] args) {
        if (method.isAnnotationPresent(Transactional.class)) {
            return invokeWithTransaction(method, args);
        }
        return invokeWithoutTransaction(method, args);

    }


    private Object invokeWithoutTransaction(Method method, Object[] args) {
        LOGGER.debug("Invoking without transaction");
        Connection connection = connectionManager.getConnection();
        connectionHolder.set(connection);
        try {
            connection.setAutoCommit(true);
            return method.invoke(serviceToInvoke, args);
        } catch (Exception e) {
            LOGGER.warn(INVOCATION_FAILED, e);
            throw new DataAccessException(INVOCATION_FAILED, e);
        } finally {
            closeConnection(connection);
            connectionHolder.remove();
        }
    }

    private Object invokeWithTransaction(Method method, Object[] args) {
        LOGGER.debug("Invoking with transaction");
        Connection connection = connectionManager.getConnection();
        connectionHolder.set(connection);
        try {
            Object result;
            connection.setAutoCommit(false);
            try {
                result = method.invoke(serviceToInvoke, args);

            } catch (Exception e) {
                LOGGER.warn(INVOCATION_FAILED, e);
                throw new DataAccessException(INVOCATION_FAILED, e);
            }
            connection.commit();
            return result;
        } catch (Exception e) {
            rollback(connection);
            LOGGER.warn(COMMIT_FAILED, e);
            throw new DataAccessException(COMMIT_FAILED, e);
        } finally {
            closeConnection(connection);
            connectionHolder.remove();
        }
    }

    private void closeConnection(Connection connection) {
        try {
            connection.close();
        } catch (SQLException e) {
            LOGGER.error(CLOSING_CONNECTION_FAILED, e.getMessage());
            throw new DataAccessException(CLOSING_CONNECTION_FAILED, e);
        }
    }

    private void rollback(Connection connection) {
        try {
            connection.rollback();
        } catch (SQLException e) {
            LOGGER.error(ROLLBACK_FAILED, e);
            throw new DataAccessException(ROLLBACK_FAILED, e);
        }
    }

}
