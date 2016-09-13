package ua.nure.bainaiev.SummaryTask4.repository;


import org.junit.After;
import ua.nure.bainaiev.SummaryTask4.db.holder.ConnectionHolder;
import ua.nure.bainaiev.SummaryTask4.db.holder.ThreadLocalConnectionHolder;
import ua.nure.bainaiev.SummaryTask4.db.manager.BoneCPManager;
import ua.nure.bainaiev.SummaryTask4.entity.Test;
import ua.nure.bainaiev.SummaryTask4.entity.enums.Subject;
import ua.nure.bainaiev.SummaryTask4.exception.DataAccessException;
import ua.nure.bainaiev.SummaryTask4.repository.impl.TestRepositoryImpl;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

public class TestRepositoryImplTest {
    private static final ConnectionHolder holder = new ThreadLocalConnectionHolder();


    private static final Test test;

    private static final TestRepository rep = new TestRepositoryImpl(holder);

    static {
        holder.set(new BoneCPManager().getConnection());
    }

    static {
        test = new Test();
        test.setComplexity(5);
        test.setSubject(Subject.BIOLOGY);
        test.setTitle("Test");
        test.setTimePassing(30);
    }

    @org.junit.Test
    public void saveTest() {
        Test savedTest = rep.save(test);

        assertEquals(savedTest.getId(), test.getId());
        assertEquals(savedTest.getComplexity(), test.getComplexity());
        assertEquals(savedTest.getTimePassing(), test.getTimePassing());
        assertEquals(savedTest.getSubject(), test.getSubject());

        rep.delete(savedTest.getId());
    }

    @org.junit.Test(expected = DataAccessException.class)
    public void saveFailTest() {
        Test newTest = new Test();
        newTest.setSubject(Subject.MATHEMATICS);
        rep.save(newTest);
    }


    @org.junit.Test
    public void updateTest() {
        rep.save(test);

        Test newTest = rep.get(test.getId());
        newTest.setTitle("New Test!");

        Test updateTest = rep.update(newTest);
        assertEquals(newTest.getId(), updateTest.getId());
        assertEquals(newTest.getComplexity(), updateTest.getComplexity());
        assertEquals(newTest.getTimePassing(), updateTest.getTimePassing());
        assertEquals(newTest.getSubject(), updateTest.getSubject());

        rep.delete(test.getId());
    }

    @org.junit.Test(expected = DataAccessException.class)
    public void updateFailTest() {
        rep.save(test);

        Test newTest = rep.get(test.getId());
        newTest.setTitle(null);

        rep.update(newTest);
    }

    @org.junit.Test
    public void deleteTest() {
        rep.save(test);
        int before = rep.getAll().size();
        rep.delete(test.getId());
        assertEquals(before - 1, rep.getAll().size());
    }

    @org.junit.Test
    public void deleteFailTest(){
        assertEquals(false, rep.delete(-1));
    }

    @org.junit.Test
    public void getTest(){
        Test savedTest = rep.save(test);

        Test newTest = rep.get(savedTest.getId());

        assertEquals(newTest.getId(), savedTest.getId());
        assertEquals(newTest.getComplexity(), savedTest.getComplexity());
        assertEquals(newTest.getTimePassing(), savedTest.getTimePassing());
        assertEquals(newTest.getSubject(), savedTest.getSubject());

        rep.delete(savedTest.getId());

    }


    @org.junit.Test
    public void getFailTest(){
        Test test = rep.get(0);
        assertNull(test);
    }


    @After
    public void delete() {
        rep.delete(test.getId());
    }



}
