package ua.nure.bainaiev.SummaryTask4.repository.impl;

import ua.nure.bainaiev.SummaryTask4.annotation.Repository;
import ua.nure.bainaiev.SummaryTask4.db.QueryStorage;
import ua.nure.bainaiev.SummaryTask4.db.holder.ConnectionHolder;
import ua.nure.bainaiev.SummaryTask4.entity.Answer;
import ua.nure.bainaiev.SummaryTask4.exception.DataAccessException;
import ua.nure.bainaiev.SummaryTask4.repository.AnswerRepository;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

@Repository
public class AnswerRepositoryImpl extends AbstractRepository<Answer> implements AnswerRepository {
    private static final String SAVE_ANSWER = "answer.save";
    private static final String UPDATE_ANSWER = "answer.update";
    private static final String DELETE_ANSWER = "answer.delete";
    private static final String GET_ANSWER = "answer.get.by.id";
    private static final String GET_ALL = "answer.all";
    private static final String GET_BY_CORRECT = "answer.get.by.correct";

    /**
     * Creates a new repository.
     *
     * @param connectionHolder connection holder
     */
    public AnswerRepositoryImpl(ConnectionHolder connectionHolder) {
        super(connectionHolder);
    }

    @Override
    public Answer save(Answer answer) {
        String sql = QueryStorage.get(SAVE_ANSWER);
        try (PreparedStatement ps = getConnection().prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            prepareForInsert(answer, ps);
            ps.executeUpdate();
            ResultSet generatedKeys = ps.getGeneratedKeys();
            generatedKeys.next();
            int id = generatedKeys.getInt(1);
            answer.setId(id);

            return get(answer.getId());
        } catch (SQLException e) {
            LOGGER.warn(ERROR_MESSAGE, sql, e);
            throw new DataAccessException(getMessage(sql), e);
        }
    }

    private void prepareForInsert(Answer answer, PreparedStatement ps) throws SQLException {
        ps.setInt(1, answer.getQuestionId());
        ps.setString(2, answer.getContent());
        ps.setBoolean(3, answer.isCorrect());
    }

    @Override
    public Answer update(Answer answer) {
        String sql = QueryStorage.get(UPDATE_ANSWER);
        try (PreparedStatement ps = getConnection().prepareStatement(sql)) {
            prepareForUpdate(answer, ps);
            ps.executeUpdate();
            return get(answer.getId());
        } catch (SQLException e) {
            LOGGER.warn(ERROR_MESSAGE, sql, e);
            throw new DataAccessException(getMessage(sql), e);
        }
    }

    private void prepareForUpdate(Answer answer, PreparedStatement ps) throws SQLException {
        prepareForInsert(answer, ps);
        ps.setInt(4, answer.getId());

    }

    @Override
    public boolean delete(int id) {
        String sql = QueryStorage.get(DELETE_ANSWER);
        return delete(id, sql);
    }


    @Override
    public Answer get(int id) {
        String sql = QueryStorage.get(GET_ANSWER);
        return get(id, sql);
    }

    @Override
    public List<Answer> getAll(int questionId) {
        String sql = QueryStorage.get(GET_ALL);
        return getAll(questionId, sql);
    }

    @Override
    public List<Answer> getByCorrect(boolean correct, int questionId) {
        String sql = QueryStorage.get(GET_BY_CORRECT);
        try (PreparedStatement ps = getConnection().prepareStatement(sql)) {
            List<Answer> allAnswer = new ArrayList<>();
            ps.setBoolean(1, correct);
            ps.setInt(2, questionId);

            ResultSet rs = ps.executeQuery();
            Answer answer;
            while (rs.next()) {
                answer = extractFromResultSet(rs);
                allAnswer.add(answer);
            }
            return allAnswer;

        } catch (SQLException e) {
            LOGGER.warn(ERROR_MESSAGE, sql, e);
            throw new DataAccessException(getMessage(sql), e);
        }
    }

    @Override
    protected Answer extractFromResultSet(ResultSet rs) throws SQLException {
        Answer answer;
        answer = new Answer();
        answer.setId(rs.getInt("id"));
        answer.setContent(rs.getString("content"));
        answer.setCorrect(rs.getBoolean("correct"));
        answer.setQuestionId(rs.getInt("question_id"));

        return answer;
    }

}
