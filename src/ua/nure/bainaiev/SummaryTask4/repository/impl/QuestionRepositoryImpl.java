package ua.nure.bainaiev.SummaryTask4.repository.impl;

import ua.nure.bainaiev.SummaryTask4.annotation.Repository;
import ua.nure.bainaiev.SummaryTask4.db.QueryStorage;
import ua.nure.bainaiev.SummaryTask4.db.holder.ConnectionHolder;
import ua.nure.bainaiev.SummaryTask4.entity.Answer;
import ua.nure.bainaiev.SummaryTask4.entity.Question;
import ua.nure.bainaiev.SummaryTask4.exception.DataAccessException;
import ua.nure.bainaiev.SummaryTask4.repository.QuestionRepository;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

@Repository
public class QuestionRepositoryImpl extends AbstractRepository<Question> implements QuestionRepository {
    private static final String SAVE_QUESTION = "question.save";
    private static final String UPDATE_QUESTION = "question.update";
    private static final String DELETE_QUESTION = "question.delete";
    private static final String GET_QUESTION = "question.get.by.id";
    private static final String GET_ALL = "question.all";
    private static final String GET_ALL_ANSWERS = "answer.all";


    /**
     * Creates a new repository.
     *
     * @param connectionHolder connection holder
     */
    public QuestionRepositoryImpl(ConnectionHolder connectionHolder) {
        super(connectionHolder);
    }



    @Override
    public Question save(Question question) {
        String sql = QueryStorage.get(SAVE_QUESTION);
        try (PreparedStatement ps = getConnection().prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            prepareForInsert(question, ps);
            ps.executeUpdate();
            ResultSet generatedKeys = ps.getGeneratedKeys();
            generatedKeys.next();
            int id = generatedKeys.getInt(1);

            question.setId(id);

            return get(question.getId());
        } catch (SQLException e) {
            LOGGER.warn(ERROR_MESSAGE, sql, e);
            throw new DataAccessException(getMessage(sql), e);
        }
    }

    private void prepareForInsert(Question question, PreparedStatement ps) throws SQLException {
        ps.setInt(1, question.getTestId());
        ps.setString(2, question.getQuestionText());
    }

    @Override
    public Question update(Question question) {
        String sql = QueryStorage.get(UPDATE_QUESTION);
        try (PreparedStatement ps = getConnection().prepareStatement(sql)) {
            prepareForUpdate(question, ps);
            ps.executeUpdate();
            return get(question.getId());
        } catch (SQLException e) {
            LOGGER.warn(ERROR_MESSAGE, sql, e);
            throw new DataAccessException(getMessage(sql), e);
        }
    }

    private void prepareForUpdate(Question question, PreparedStatement ps) throws SQLException {
        prepareForInsert(question, ps);
        ps.setInt(3, question.getId());
    }

    @Override
    public boolean delete(int id) {
        String sql = QueryStorage.get(DELETE_QUESTION);
        return delete(id, sql);
    }

    @Override
    public Question get(int id) {
        String sql = QueryStorage.get(GET_QUESTION);
        return get(id, sql);
    }

    @Override
    public List<Question> getAll(int testId) {
        String sql = QueryStorage.get(GET_ALL);
        return getAll(testId, sql);
    }

    @Override
    protected Question extractFromResultSet(ResultSet rs) throws SQLException {
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
