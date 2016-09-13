package ua.nure.bainaiev.SummaryTask4.repository.impl;

import ua.nure.bainaiev.SummaryTask4.annotation.Repository;
import ua.nure.bainaiev.SummaryTask4.db.QueryStorage;
import ua.nure.bainaiev.SummaryTask4.db.holder.ConnectionHolder;
import ua.nure.bainaiev.SummaryTask4.entity.Storage;
import ua.nure.bainaiev.SummaryTask4.exception.DataAccessException;
import ua.nure.bainaiev.SummaryTask4.repository.StorageRepository;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@Repository
public class StorageRepositoryImpl extends AbstractRepository<Storage> implements StorageRepository {
    private static final String SAVE_STORAGE = "storage.save";
    private static final String UPDATE_STORAGE = "storage.update";
    private static final String DELETE_STORAGE = "storage.delete";
    private static final String GET_STORAGE = "storage.get.by.id";
    private static final String GET_ALL = "storage.all";

    /**
     * Creates a new repository.
     *
     * @param connectionHolder connection holder
     */
    public StorageRepositoryImpl(ConnectionHolder connectionHolder) {
        super(connectionHolder);
    }

    @Override
    public Storage save(Storage storage) {
        String sql = QueryStorage.get(SAVE_STORAGE);
        try (PreparedStatement ps = getConnection().prepareStatement(sql)) {
            prepareForInsert(storage, ps);
            ps.executeUpdate();

            return get(storage.getId(), storage.getTestId());
        } catch (SQLException e) {
            LOGGER.warn(ERROR_MESSAGE, sql, e);
            throw new DataAccessException(getMessage(sql), e);
        }
    }

    private void prepareForInsert(Storage storage, PreparedStatement ps) throws SQLException {
        ps.setInt(1, storage.getId());
        ps.setInt(2, storage.getTestId());
        ps.setString(3, storage.getResult());
    }

    @Override
    public Storage update(Storage storage) {
        String sql = QueryStorage.get(UPDATE_STORAGE);
        try (PreparedStatement ps = getConnection().prepareStatement(sql)) {
            prepareForUpdate(storage, ps);
            ps.executeUpdate();

            return get(storage.getId(), storage.getTestId());
        } catch (SQLException e) {
            LOGGER.warn(ERROR_MESSAGE, sql, e);
            throw new DataAccessException(getMessage(sql), e);
        }
    }

    private void prepareForUpdate(Storage storage, PreparedStatement ps) throws SQLException {
        ps.setString(1, storage.getResult());
        ps.setInt(2, storage.getId());
        ps.setInt(3, storage.getTestId());
    }


    @Override
    public boolean delete(int id, int testId) {
        String sql = QueryStorage.get(DELETE_STORAGE);
        return delete(id, testId, sql);
    }

    @Override
    public Storage get(int id, int testId) {
        String sql = QueryStorage.get(GET_STORAGE);
        return get(id, testId, sql);
    }

    @Override
    public List<Storage> getAll(int user_id) {
        String sql = QueryStorage.get(GET_ALL);
        return getAll(user_id, sql);
    }

    @Override
    protected Storage extractFromResultSet(ResultSet rs) throws SQLException {
        Storage storage;
        storage = new Storage();
        storage.setId(rs.getInt("user_id"));
        storage.setTestId(rs.getInt("test_id"));
        storage.setResult(rs.getString("result"));
        return storage;
    }

}
