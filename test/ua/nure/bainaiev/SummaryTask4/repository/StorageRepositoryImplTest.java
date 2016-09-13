package ua.nure.bainaiev.SummaryTask4.repository;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import ua.nure.bainaiev.SummaryTask4.db.holder.ConnectionHolder;
import ua.nure.bainaiev.SummaryTask4.db.holder.ThreadLocalConnectionHolder;
import ua.nure.bainaiev.SummaryTask4.db.manager.BoneCPManager;
import ua.nure.bainaiev.SummaryTask4.db.manager.ConnectionManager;
import ua.nure.bainaiev.SummaryTask4.entity.Storage;
import ua.nure.bainaiev.SummaryTask4.entity.Test;
import ua.nure.bainaiev.SummaryTask4.entity.User;
import ua.nure.bainaiev.SummaryTask4.entity.enums.Role;
import ua.nure.bainaiev.SummaryTask4.entity.enums.Status;
import ua.nure.bainaiev.SummaryTask4.entity.enums.Subject;
import ua.nure.bainaiev.SummaryTask4.exception.DataAccessException;
import ua.nure.bainaiev.SummaryTask4.repository.impl.*;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

public class StorageRepositoryImplTest {
    private static final ConnectionHolder holder = new ThreadLocalConnectionHolder();

    private static final Storage storage;
    private static final User user;
    private static final Test test;


    static {
        holder.set(new BoneCPManager().getConnection());
    }
    private static final StorageRepository rep = new StorageRepositoryImpl(holder);
    private static final UserRepository userRep = new UserRepositoryImpl(holder);
    private static final TestRepository testRep = new TestRepositoryImpl(holder);

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

        test = new Test();
        test.setTimePassing(20);
        test.setTitle("Test");
        test.setComplexity(5);
        test.setSubject(Subject.ENGLISH);

        storage = new Storage();
        storage.setResult("3 / 10");
    }

    @BeforeClass
    public static void prepare() {
        User savedUser = userRep.save(user);
        Test savedTest = testRep.save(test);
        storage.setTestId(savedTest.getId());
        storage.setId(savedUser.getId());
    }

    @org.junit.Test
    public void saveTest() {
        Storage savedStorage = rep.save(storage);
        assertEquals(storage, savedStorage);

        rep.delete(savedStorage.getId(), savedStorage.getTestId());
    }

    @org.junit.Test(expected = DataAccessException.class)
    public void saveFailTest() {
        Storage newStorage = new Storage();
        newStorage.setResult("3 / 5");
        rep.save(newStorage);
    }

    @org.junit.Test
    public void updateTest() {
        rep.save(storage);

        Storage newStorage = rep.get(storage.getId(), storage.getTestId());
        newStorage.setResult("5 / 10");

        Storage updateStorage = rep.update(newStorage);
        assertEquals(newStorage, updateStorage);

        rep.delete(storage.getId(), storage.getTestId());
    }

    @org.junit.Test(expected = DataAccessException.class)
    public void updateFailTest() {
        rep.save(storage);

        Storage newStorage = rep.get(storage.getId(), storage.getTestId());
        newStorage.setResult(null);

       rep.update(newStorage);

    }

    @org.junit.Test
    public void deleteTest() {
        rep.save(storage);
        int before = rep.getAll(storage.getId()).size();
        rep.delete(storage.getId(), storage.getTestId());
        assertEquals(before - 1, rep.getAll(storage.getId()).size());
    }

    @org.junit.Test
    public void deleteFailTest(){
        assertEquals(false, rep.delete(-1, -2));
    }

    @org.junit.Test
    public void getTest(){
        Storage savedStorage = rep.save(storage);
        Storage getStorage = rep.get(savedStorage.getId(), savedStorage.getTestId());
        assertEquals(savedStorage, getStorage);

        rep.delete(storage.getId(), storage.getTestId());
    }

    @org.junit.Test
    public void getFailTest(){
        Storage storage = rep.get(0, 0);
        assertNull(storage);
    }

    @org.junit.Test
    public void getAllTest(){
        List<Storage> storageList = new ArrayList<>();
        Storage savedStorage = rep.save(storage);
        storageList.add(savedStorage);

        List<Storage> list = rep.getAll(storage.getId());
        assertEquals(storageList, list);

        rep.delete(storage.getId(), storage.getTestId());
    }

    @After
    public void deleteStorage(){
        rep.delete(storage.getId(), storage.getTestId());
    }

    @AfterClass
    public static void close(){
        userRep.delete(storage.getId());
        testRep.delete(storage.getTestId());
    }




}
