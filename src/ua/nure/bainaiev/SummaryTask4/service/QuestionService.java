package ua.nure.bainaiev.SummaryTask4.service;


import ua.nure.bainaiev.SummaryTask4.annotation.Transactional;
import ua.nure.bainaiev.SummaryTask4.entity.Question;

import java.util.List;

public interface QuestionService {
    @Transactional
    Question save(Question question);

    @Transactional
    Question update(Question question);

    boolean delete(int id);

    Question get(int id);

    List<Question> getAll(int testId);
}
