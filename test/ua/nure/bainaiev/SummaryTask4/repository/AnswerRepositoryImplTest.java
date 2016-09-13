package ua.nure.bainaiev.SummaryTask4.repository;


import org.junit.After;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import ua.nure.bainaiev.SummaryTask4.db.holder.ConnectionHolder;
import ua.nure.bainaiev.SummaryTask4.db.holder.ThreadLocalConnectionHolder;
import ua.nure.bainaiev.SummaryTask4.db.manager.BoneCPManager;
import ua.nure.bainaiev.SummaryTask4.db.manager.ConnectionManager;
import ua.nure.bainaiev.SummaryTask4.entity.Answer;
import ua.nure.bainaiev.SummaryTask4.entity.Question;
import ua.nure.bainaiev.SummaryTask4.entity.Test;
import ua.nure.bainaiev.SummaryTask4.entity.enums.Subject;
import ua.nure.bainaiev.SummaryTask4.exception.DataAccessException;
import ua.nure.bainaiev.SummaryTask4.repository.impl.AnswerRepositoryImpl;
import ua.nure.bainaiev.SummaryTask4.repository.impl.QuestionRepositoryImpl;
import ua.nure.bainaiev.SummaryTask4.repository.impl.TestRepositoryImpl;


import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

public class AnswerRepositoryImplTest {
    private static final ConnectionHolder holder = new ThreadLocalConnectionHolder();
    private static final ConnectionManager conn = new BoneCPManager();

    private static final Answer answer;
    private static final Question question;
    private static final Test test;

    static {
        holder.set(conn.getConnection());
    }

    private static final AnswerRepository rep = new AnswerRepositoryImpl(holder);
    private static final QuestionRepository questionRep = new QuestionRepositoryImpl(holder);
    private static final TestRepository testRep = new TestRepositoryImpl(holder);

    static {
        test = new Test();
        test.setComplexity(5);
        test.setSubject(Subject.BIOLOGY);
        test.setTitle("Test");
        test.setTimePassing(30);

        question = new Question();
        question.setQuestionText("QuestionText");

        answer = new Answer();
        answer.setCorrect(false);
        answer.setContent("Answer");
    }

    @BeforeClass
    public static void prepare() {
        Test savedTest = testRep.save(test);
        question.setTestId(savedTest.getId());

        Question savedQuestion = questionRep.save(question);
        answer.setQuestionId(savedQuestion.getId());
    }

    @org.junit.Test
    public void saveTest() {
        Answer savedAnswer = rep.save(answer);
        assertEquals(answer, savedAnswer);

        rep.delete(answer.getId());
    }

    @org.junit.Test(expected = DataAccessException.class)
    public void saveFailTest() {
        Answer newAnswer = new Answer();
        newAnswer.setCorrect(false);
        rep.save(newAnswer);
    }

    @org.junit.Test
    public void updateTest() {
        rep.save(answer);

        Answer newAnswer = rep.get(answer.getId());
        newAnswer.setContent("New content!");
        Answer updateAnswer = rep.update(newAnswer);
        assertEquals(newAnswer, updateAnswer);

        rep.delete(answer.getId());

    }


    @org.junit.Test(expected = DataAccessException.class)
    public void updateFailTest() {
        rep.save(answer);

        Answer newAnswer = rep.get(answer.getId());
        newAnswer.setContent(null);
        rep.update(newAnswer);
    }

    @org.junit.Test
    public void deleteTest() {
        rep.save(answer);
        int before = rep.getAll(answer.getQuestionId()).size();
        rep.delete(answer.getId());
        assertEquals(before - 1, rep.getAll(answer.getQuestionId()).size());
    }

    @org.junit.Test
    public void deleteFailTest() {
        assertEquals(false, rep.delete(-1));
    }

    @org.junit.Test
    public void getTest() {
        Answer savedAnswer = rep.save(answer);
        Answer getAnswer = rep.get(answer.getId());
        assertEquals(savedAnswer, getAnswer);

        rep.delete(answer.getId());
    }

    @org.junit.Test
    public void getFailTest() {
        Answer newAnswer = rep.get(0);
        assertNull(newAnswer);
    }

    @After
    public void deleteAnswer() {
        rep.delete(answer.getId());
    }

    @AfterClass
    public static void close(){
        testRep.delete(question.getTestId());
        questionRep.delete(answer.getQuestionId());
        conn.shutdown();
        holder.remove();
    }

}
