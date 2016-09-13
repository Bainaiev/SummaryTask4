package ua.nure.bainaiev.SummaryTask4.repository.impl;


import ua.nure.bainaiev.SummaryTask4.annotation.Autowired;
import ua.nure.bainaiev.SummaryTask4.annotation.Repository;
import ua.nure.bainaiev.SummaryTask4.db.QueryStorage;
import ua.nure.bainaiev.SummaryTask4.db.holder.ConnectionHolder;
import ua.nure.bainaiev.SummaryTask4.entity.Answer;
import ua.nure.bainaiev.SummaryTask4.entity.Question;
import ua.nure.bainaiev.SummaryTask4.entity.Test;
import ua.nure.bainaiev.SummaryTask4.entity.enums.Subject;
import ua.nure.bainaiev.SummaryTask4.exception.DataAccessException;
import ua.nure.bainaiev.SummaryTask4.repository.QuestionRepository;
import ua.nure.bainaiev.SummaryTask4.repository.TestRepository;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

@Repository
public class TestRepositoryImpl extends AbstractRepository<Test> implements TestRepository {
    private static final String SAVE_TEST = "test.save";
    private static final String UPDATE_TEST = "test.update";
    private static final String DELETE_TEST = "test.delete";
    private static final String GET_TEST = "test.get.by.id";
    private static final String GET_ALL = "test.all";
    private static final String GET_ALL_BY_SUBJECT = "test.all.by.subject";
    private static final String GET_SORTED = "test.get.sorted";
    private static final String GET_ALL_ANSWERS = "answer.all";
    private static final String GET_ALL_QUESTIONS = "question.all";

    /**
     * Creates a new repository.
     *
     * @param connectionHolder connection holder
     */
    public TestRepositoryImpl(ConnectionHolder connectionHolder) {
        super(connectionHolder);
    }

    public TestRepositoryImpl(ConnectionHolder connectionHolder, QuestionRepository repository) {
        super(connectionHolder);
    }

    @Override
    public Test save(Test test) {
        String sql = QueryStorage.get(SAVE_TEST);
        try (PreparedStatement ps = getConnection().prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            prepareForInsert(test, ps);
            ps.executeUpdate();
            ResultSet generatedKeys = ps.getGeneratedKeys();
            generatedKeys.next();
            int id = generatedKeys.getInt(1);
            test.setId(id);

            return get(id);

        } catch (SQLException e) {
            LOGGER.warn(ERROR_MESSAGE, sql, e);
            throw new DataAccessException(getMessage(sql), e);
        }
    }


    private void prepareForInsert(Test test, PreparedStatement ps) throws SQLException {
        ps.setString(1, test.getSubject().toString());
        ps.setString(2, test.getTitle());
        ps.setInt(3, test.getComplexity());
        ps.setInt(4, test.getTimePassing());
    }

    @Override
    public Test update(Test test) {
        String sql = QueryStorage.get(UPDATE_TEST);
        try (PreparedStatement ps = getConnection().prepareStatement(sql)) {
            prepareForUpdate(test, ps);
            ps.executeUpdate();

            return get(test.getId());
        } catch (SQLException e) {
            LOGGER.warn(ERROR_MESSAGE, sql, e);
            throw new DataAccessException(getMessage(sql), e);
        }
    }

    private void prepareForUpdate(Test test, PreparedStatement ps) throws SQLException {
        prepareForInsert(test, ps);
        ps.setInt(5, test.getId());
    }


    @Override
    public boolean delete(int id) {
        String sql = QueryStorage.get(DELETE_TEST);
        return delete(id, sql);
    }

    @Override
    public Test get(int id) {
        String sql = QueryStorage.get(GET_TEST);
        return get(id, sql);
    }

    @Override
    public List<Test> getAll() {
        String sql = QueryStorage.get(GET_ALL);
        try (PreparedStatement ps = getConnection().prepareStatement(sql)) {
            List<Test> allTests = new ArrayList<>();
            ResultSet rs = ps.executeQuery();

            Test test;

            while (rs.next()) {
                test = extractFromResultSet(rs);
                allTests.add(test);
            }

            return allTests;
        } catch (SQLException e) {
            LOGGER.warn(ERROR_MESSAGE, sql, e);
            throw new DataAccessException(getMessage(sql), e);
        }
    }

    @Override
    public List<Test> getAllBySubject(Subject subject) {
        String sql = QueryStorage.get(GET_ALL_BY_SUBJECT);
        try (PreparedStatement ps = getConnection().prepareStatement(sql)) {
            List<Test> list = new ArrayList<>();
            ps.setString(1, subject.toString());
            ResultSet rs = ps.executeQuery();
            Test entity;
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


    @Override
    protected Test extractFromResultSet(ResultSet rs) throws SQLException {
        Test test;
        test = new Test();
        test.setId(rs.getInt("id"));
        test.setSubject(Subject.valueOf(rs.getString("subject")));
        test.setTitle(rs.getString("title"));
        test.setComplexity(rs.getInt("complexity"));
        test.setTimePassing(rs.getInt("time_passing"));
        test.setQuestions(getAllQuestion(test.getId()));

        return test;
    }


    @Override
    public List<Test> getSorted(String subjectName, String sort, String order) {
        String query = QueryStorage.get(GET_SORTED);
        if (subjectName == null || subjectName.equals("")) {
            query = query.replace("subject = ?", "NOT subject = ?");
        }
        if (sort == null || sort.equals("")) {
            query = query.replace("{s}", "id");
        } else {
            query = query.replace("{s}", sort);
        }
        if (order == null || order.equals("")) {
            query = query.replace("{o}", "ASC");
        } else {
            query = query.replace("{o}", order);
        }


        try (PreparedStatement ps = getConnection().prepareStatement(query)) {
            List<Test> list = new ArrayList<>();
            if (subjectName == null) {
                ps.setString(1, "DEFAULT");
            } else {
                ps.setString(1, subjectName);
            }
            ResultSet rs = ps.executeQuery();
            Test entity;
            while (rs.next()) {
                entity = extractFromResultSet(rs);
                list.add(entity);
            }
            return list;
        } catch (SQLException e) {
            LOGGER.warn(ERROR_MESSAGE, query, e);
            throw new DataAccessException(getMessage(query), e);
        }
    }

    private List<Question> getAllQuestion(int testId) {
        String sql = QueryStorage.get(GET_ALL_QUESTIONS);
        try (PreparedStatement ps = getConnection().prepareStatement(sql)) {
            List<Question> list = new ArrayList<>();
            ps.setInt(1, testId);
            ResultSet rs = ps.executeQuery();
            Question entity;
            while (rs.next()) {
                entity = extractQuestion(rs);
                list.add(entity);
            }
            return list;
        } catch (SQLException e) {
            LOGGER.warn(ERROR_MESSAGE, sql, e);
            throw new DataAccessException(getMessage(sql), e);
        }
    }

    private Question extractQuestion(ResultSet rs) throws SQLException {
        Question question;
        question = new Question();
        question.setId(rs.getInt("id"));
        question.setQuestionText(rs.getString("question_text"));
        question.setTestId(rs.getInt("test_id"));
        question.setAnswers(getAnswers(question.getId()));
        return question;
    }

    private List<Answer> getAnswers(int questionId){
        String sql = QueryStorage.get(GET_ALL_ANSWERS);
        try (PreparedStatement ps = getConnection().prepareStatement(sql)) {
            List<Answer> list = new ArrayList<>();
            ps.setInt(1, questionId);
            ResultSet rs = ps.executeQuery();
            Answer entity;
            while (rs.next()) {
                entity = extractAnswers(rs);
                list.add(entity);
            }
            return list;
        } catch (SQLException e) {
            LOGGER.warn(ERROR_MESSAGE, sql, e);
            throw new DataAccessException(getMessage(sql), e);
        }
    }

    private Answer extractAnswers(ResultSet rs) throws SQLException {
        Answer answer;
        answer = new Answer();
        answer.setId(rs.getInt("id"));
        answer.setContent(rs.getString("content"));
        answer.setCorrect(rs.getBoolean("correct"));
        answer.setQuestionId(rs.getInt("question_id"));

        return answer;
    }


}
