package ua.nure.bainaiev.SummaryTask4.repository;


import ua.nure.bainaiev.SummaryTask4.entity.Question;

import java.util.List;

public interface QuestionRepository {
    Question save(Question question);
    Question update(Question question);
    boolean delete(int id);
    Question get(int id);
    List<Question> getAll(int testId);
}
