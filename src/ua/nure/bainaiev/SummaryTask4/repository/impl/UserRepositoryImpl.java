package ua.nure.bainaiev.SummaryTask4.repository.impl;


import ua.nure.bainaiev.SummaryTask4.annotation.Repository;
import ua.nure.bainaiev.SummaryTask4.bean.UserBean;
import ua.nure.bainaiev.SummaryTask4.db.QueryStorage;
import ua.nure.bainaiev.SummaryTask4.db.holder.ConnectionHolder;
import ua.nure.bainaiev.SummaryTask4.entity.User;
import ua.nure.bainaiev.SummaryTask4.entity.enums.Role;
import ua.nure.bainaiev.SummaryTask4.entity.enums.Status;
import ua.nure.bainaiev.SummaryTask4.exception.DataAccessException;
import ua.nure.bainaiev.SummaryTask4.repository.UserRepository;

import java.sql.*;
import java.util.*;

@Repository
public class UserRepositoryImpl extends AbstractRepository<User> implements UserRepository {
    private static final String SAVE_USER = "user.save";
    private static final String UPDATE_USER = "user.update";
    private static final String DELETE_USER = "user.delete";
    private static final String GET_USER = "user.get.by.id";
    private static final String GET_USER_BY_EMAIL = "user.get.by.email";
    private static final String GET_BY_LOGIN = "user.get.by.login";
    private static final String GET_ROLES = "user.get.roles.by.id";
    private static final String DELETE_OLD = "user.role.delete.old";
    private static final String ADD_ROLE = "user.role.add";
    private static final String GET_ALL = "user.all";
    private static final String GET_ALL_STUDENT = "user.all.student";
    private static final String GET_ALL_STUDENT_AND_MARK = "user.all.result";


    /**
     * Creates a new repository.
     *
     * @param connectionHolder connection holder
     */
    public UserRepositoryImpl(ConnectionHolder connectionHolder) {
        super(connectionHolder);
    }

    @Override
    public User save(User user) {
        String sql = QueryStorage.get(SAVE_USER);
        try (PreparedStatement ps = getConnection().prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            prepareForInsert(user, ps);
            ps.executeUpdate();
            ResultSet generatedKeys = ps.getGeneratedKeys();
            generatedKeys.next();
            int id = generatedKeys.getInt(1);
            user.setId(id);

            updateRoles(user);
            return get(id);

        } catch (SQLException e) {
            LOGGER.warn(ERROR_MESSAGE, sql, e);
            throw new DataAccessException(getMessage(sql), e);
        }
    }


    private void prepareForInsert(User user, PreparedStatement ps) throws SQLException {
        ps.setString(1, user.getFirstName());
        ps.setString(2, user.getLastName());
        ps.setString(3, user.getLogin());
        ps.setString(4, user.getPassword());
        ps.setString(5, user.getEmail());
        ps.setString(6, user.getStatus().toString());
        ps.setString(7, user.getImage());
    }

    @Override
    public User update(User user) {
        String sql = QueryStorage.get(UPDATE_USER);
        try (PreparedStatement ps = getConnection().prepareStatement(sql)) {
            prepareForUpdate(user, ps);
            ps.executeUpdate();

            updateRoles(user);

            return get(user.getId());
        } catch (SQLException e) {
            LOGGER.warn(ERROR_MESSAGE, sql, e);
            throw new DataAccessException(getMessage(sql), e);
        }
    }

    private void prepareForUpdate(User user, PreparedStatement ps) throws SQLException {
        prepareForInsert(user, ps);
        ps.setInt(8, user.getId());
    }

    @Override
    public boolean delete(int id) {
        String sql = QueryStorage.get(DELETE_USER);
        return delete(id, sql);
    }

    @Override
    public User get(int id) {
        String sql = QueryStorage.get(GET_USER);
        return get(id, sql);
    }


    @Override
    public User getByEmail(String email) {
        String sql = QueryStorage.get(GET_USER_BY_EMAIL);
        try (PreparedStatement ps = getConnection().prepareStatement(sql)) {
            User user = null;
            ps.setString(1, email);
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                user = extractFromResultSet(rs);
            }

            return user;
        } catch (SQLException e) {
            LOGGER.warn(ERROR_MESSAGE, sql, e);
            throw new DataAccessException(getMessage(sql), e);
        }
    }


    @Override
    public List<User> getAll() {
        String sql = QueryStorage.get(GET_ALL);
        try (PreparedStatement ps = getConnection().prepareStatement(sql)) {
            List<User> allUsers = new ArrayList<>();
            ResultSet rs = ps.executeQuery();

            User user;

            while (rs.next()) {
                user = extractFromResultSet(rs);
                allUsers.add(user);
            }

            return allUsers;
        } catch (SQLException e) {
            LOGGER.warn(ERROR_MESSAGE, sql, e);
            throw new DataAccessException(getMessage(sql), e);
        }
    }

    @Override
    public User getByLogin(String login) {
        String sql = QueryStorage.get(GET_BY_LOGIN);
        try (PreparedStatement ps = getConnection().prepareStatement(sql)) {
            ps.setString(1, login);

            ResultSet rs = ps.executeQuery();

            User user = null;

            if (rs.next()) {
                user = extractFromResultSet(rs);
            }

            return user;
        } catch (SQLException e) {
            LOGGER.warn(ERROR_MESSAGE, sql, e);
            throw new DataAccessException(getMessage(sql), e);
        }
    }


    @Override
    protected User extractFromResultSet(ResultSet rs) throws SQLException {
        User user;
        user = new User();
        user.setId(rs.getInt("id"));
        user.setFirstName(rs.getString("first_name"));
        user.setLastName(rs.getString("last_name"));
        user.setLogin(rs.getString("login"));
        user.setPassword(rs.getString("password"));
        user.setEmail(rs.getString("email"));
        user.setStatus(Status.valueOf(rs.getString("status").toUpperCase()));
        user.setImage(rs.getString("image"));
        user.setRoles(getRoles(user.getId()));

        return user;
    }

    private Set<Role> getRoles(int id) {
        String sql = QueryStorage.get(GET_ROLES);
        try (PreparedStatement ps = getConnection().prepareStatement(sql)) {
            Set<Role> roles = new HashSet<>();
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                roles.add(Role.valueOf(rs.getString("role")));
            }

            return roles;
        } catch (SQLException e) {
            LOGGER.warn(ERROR_MESSAGE, sql, e);
            throw new DataAccessException(getMessage(sql), e);
        }
    }

    private boolean updateRoles(User user) {
        if (user.getRoles() == null || user.getRoles().size() == 0) {
            user.addRole(Role.STUDENT);
        }

        deleteOldRoles(user.getId());


        for (Role role : user.getRoles()) {
            LOGGER.debug("Adding role {}", role);
            addRole(user.getId(), role);
        }

        return true;
    }

    private boolean deleteOldRoles(int userId) {
        return delete(userId, QueryStorage.get(DELETE_OLD));
    }

    private boolean addRole(int userId, Role role) {
        String sql = QueryStorage.get(ADD_ROLE);
        return addEnum(userId, role.toString(), sql);
    }

    @Override
    public List<User> getAllStudent() {
        String sql = QueryStorage.get(GET_ALL_STUDENT);
        try (PreparedStatement ps = getConnection().prepareStatement(sql)) {
            List<User> allUsers = new ArrayList<>();
            ResultSet rs = ps.executeQuery();

            User user;

            while (rs.next()) {
                user = extractFromResultSet(rs);
                allUsers.add(user);
            }

            return allUsers;
        } catch (SQLException e) {
            LOGGER.warn(ERROR_MESSAGE, sql, e);
            throw new DataAccessException(getMessage(sql), e);
        }
    }

    public List<UserBean> getAllResult() {
        String sql = QueryStorage.get(GET_ALL_STUDENT_AND_MARK);
        try (PreparedStatement ps = getConnection().prepareStatement(sql)) {
            List<UserBean> allUserBeans = new ArrayList<>();
            ResultSet rs = ps.executeQuery();

            UserBean bean;
            while (rs.next()) {
                bean = extractResult(rs);
                allUserBeans.add(bean);
            }


            return allUserBeans;
        } catch (SQLException e) {
            LOGGER.warn(ERROR_MESSAGE, sql, e);
            throw new DataAccessException(getMessage(sql), e);
        }
    }


    private UserBean extractResult(ResultSet rs) throws SQLException {
        UserBean userBean;
        userBean = new UserBean();
        userBean.setFirstName(rs.getString("first_name"));
        userBean.setLastName(rs.getString("last_name"));
        String result = rs.getString("result");

        Integer res = Integer.parseInt(result.substring(0, 1));
        userBean.setResult(res);

        return userBean;
    }


}
