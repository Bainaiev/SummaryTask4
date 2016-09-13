package ua.nure.bainaiev.SummaryTask4.repository;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import ua.nure.bainaiev.SummaryTask4.db.holder.ConnectionHolder;
import ua.nure.bainaiev.SummaryTask4.db.holder.ThreadLocalConnectionHolder;
import ua.nure.bainaiev.SummaryTask4.db.manager.BoneCPManager;
import ua.nure.bainaiev.SummaryTask4.db.manager.ConnectionManager;
import ua.nure.bainaiev.SummaryTask4.entity.Question;
import ua.nure.bainaiev.SummaryTask4.entity.Test;
import ua.nure.bainaiev.SummaryTask4.entity.enums.Subject;
import ua.nure.bainaiev.SummaryTask4.exception.DataAccessException;
import ua.nure.bainaiev.SummaryTask4.repository.impl.*;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

public class QuestionRepositoryImplTest {
    private static final ConnectionHolder holder = new ThreadLocalConnectionHolder();


    private static final Test test;
    private static final Question question;


    private static final QuestionRepository rep = new QuestionRepositoryImpl(holder);
    private static final TestRepository testRep = new TestRepositoryImpl(holder);
    static {
        holder.set(new BoneCPManager().getConnection());
    }

    static {
        test = new Test();
        test.setComplexity(5);
        test.setSubject(Subject.BIOLOGY);
        test.setTitle("Test");
        test.setTimePassing(30);

        question = new Question();
        question.setQuestionText("QuestionText");

    }

    @BeforeClass
    public static void prepare() {
        Test savedTest = testRep.save(test);
        question.setTestId(savedTest.getId());
    }

    @org.junit.Test
    public void saveTest() {
        Question savedQuestion = rep.save(question);
        assertEquals(question.getQuestionText(), savedQuestion.getQuestionText());
        assertEquals(question.getTestId(), savedQuestion.getTestId());
        assertEquals(question.getId(), savedQuestion.getId());

        rep.delete(savedQuestion.getId());
    }

    @org.junit.Test(expected = DataAccessException.class)
    public void saveFailTest() {
        Question newQuestion = new Question();
        newQuestion.setQuestionText("failed");
        rep.save(newQuestion);
    }

    @org.junit.Test
    public void updateTest(){
        rep.save(question);

        Question newQuestion = rep.get(question.getId());
        newQuestion.setQuestionText("Update question text");

        Question updateQuestion = rep.update(newQuestion);
        assertEquals(newQuestion.getQuestionText(), updateQuestion.getQuestionText());
        assertEquals(newQuestion.getTestId(), updateQuestion.getTestId());
        assertEquals(newQuestion.getId(), updateQuestion.getId());

        rep.delete(question.getId());
    }

    @org.junit.Test(expected = DataAccessException.class)
    public void updateFailTest(){
        rep.save(question);

        Question newQuestion = rep.get(question.getId());
        newQuestion.setQuestionText(null);

        rep.update(newQuestion);
    }

    @org.junit.Test
    public void deleteTest(){
        rep.save(question);
        int before = rep.getAll(question.getTestId()).size();
        rep.delete(question.getId());
        assertEquals(before - 1, rep.getAll(question.getTestId()).size());
    }

    @org.junit.Test
    public void deleteFailTest(){
        assertEquals(false, rep.delete(-1));
    }

    @org.junit.Test
    public void getTest(){
        Question savedQuestion = rep.save(question);
        Question getQuestion = rep.get(savedQuestion.getId());
        assertEquals(savedQuestion.getQuestionText(), getQuestion.getQuestionText());
        assertEquals(savedQuestion.getTestId(), getQuestion.getTestId());
        assertEquals(savedQuestion.getId(), getQuestion.getId());

        rep.delete(question.getId());
    }

    @org.junit.Test
    public void getFailTest(){
        Question question = rep.get(0);
        assertNull(question);
    }

    @After
    public void deleteQuestion(){
        rep.delete(question.getId());
    }

    @AfterClass
    public static void close(){
        testRep.delete(question.getTestId());
    }

}
