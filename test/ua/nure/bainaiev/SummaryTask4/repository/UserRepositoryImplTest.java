package ua.nure.bainaiev.SummaryTask4.repository;


import org.junit.Test;
import ua.nure.bainaiev.SummaryTask4.db.holder.ConnectionHolder;
import ua.nure.bainaiev.SummaryTask4.db.holder.ThreadLocalConnectionHolder;
import ua.nure.bainaiev.SummaryTask4.db.manager.BoneCPManager;
import ua.nure.bainaiev.SummaryTask4.entity.User;
import ua.nure.bainaiev.SummaryTask4.entity.enums.Role;
import ua.nure.bainaiev.SummaryTask4.entity.enums.Status;
import ua.nure.bainaiev.SummaryTask4.exception.DataAccessException;
import ua.nure.bainaiev.SummaryTask4.repository.impl.UserRepositoryImpl;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

public class UserRepositoryImplTest {
    private static final ConnectionHolder holder = new ThreadLocalConnectionHolder();
    private static final User user;

    static {
        holder.set(new BoneCPManager().getConnection());
    }

    private static final UserRepository rep = new UserRepositoryImpl(holder);

    static {
        user = new User();
        user.setFirstName("firstName");
        user.setLastName("lastName");
        user.setLogin("login");
        user.setPassword("password");
        user.setEmail("name@mail.ru");
        user.setStatus(Status.ACTIVE);
        user.setImage("noimage.jpg");
        user.addRole(Role.STUDENT);
    }


    @Test
    public void saveTest() {
        User user1 = rep.save(user);
        assertEquals(user, user1);

        rep.delete(user.getId());
    }

    @Test(expected = DataAccessException.class)
    public void saveFailTest() {
        User user1 = new User();
        user1.setStatus(Status.ACTIVE);
        rep.save(user1);
    }

    @Test
    public void updateTest() {
        User user1 = rep.get(1);
        user1.setFirstName("new_name");
        User user2 = rep.update(user1);
        assertEquals(user1, user2);

    }

    @Test(expected = DataAccessException.class)
    public void updateFailTest() {
        User user1 = rep.get(1);
        user1.setFirstName(null);
        rep.update(user1);
    }

    @Test
    public void deleteTest() {
        rep.save(user);
        int before = rep.getAll().size();
        rep.delete(user.getId());
        assertEquals(before - 1, rep.getAll().size());
    }

    @Test
    public void deleteFailTest() {
        assertEquals(false, rep.delete(-1));
    }

    @Test
    public void getTest() {
        User user1;
        User user2;
        user1 = rep.save(user);
        user2 = rep.get(user.getId());
        assertEquals(user1, user2);

        rep.delete(user.getId());
    }

    @Test
    public void getFailTest() {
        User user1 = rep.get(0);
        assertNull(user1);
    }

    @Test
    public void getByEmailTest() {
        User user1 = rep.save(user);
        User user2 = rep.getByEmail(user.getEmail());
        assertEquals(user1, user2);

        rep.delete(user1.getId());
    }

    @Test
    public void getByEmailFailTest(){
        User user1 = rep.getByEmail("nonExistingEmail@host.com");
        assertNull(user1);
    }

    @Test
    public void getByLoginTest(){
        rep.save(user);
        User user1 = rep.getByLogin(user.getLogin());
        assertEquals(user, user1);

        rep.delete(user.getId());
    }

    @Test
    public void getByLoginFailTest(){
        User myUser = rep.getByLogin("nonExistingLogin");
        assertNull(myUser);
    }


}
