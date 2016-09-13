package ua.nure.bainaiev.SummaryTask4.repository;


import ua.nure.bainaiev.SummaryTask4.entity.Answer;

import java.util.List;

public interface AnswerRepository {

    /**
     * save answer
     * @param answer
     * @return
     */
    Answer save(Answer answer);

    Answer update(Answer answer);

    boolean delete(int id);

    Answer get(int id);

    List<Answer> getAll(int questionId);

    List<Answer> getByCorrect(boolean correct, int questionId);
}
