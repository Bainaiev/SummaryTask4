package ua.nure.bainaiev.SummaryTask4.service;


import ua.nure.bainaiev.SummaryTask4.annotation.Transactional;
import ua.nure.bainaiev.SummaryTask4.entity.Answer;
import ua.nure.bainaiev.SummaryTask4.entity.Test;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

public interface AnswerService {
    @Transactional
    Answer save(Answer answer);

    @Transactional
    Answer update(Answer answer);

    boolean delete(int id);

    Answer get(int id);

    List<Answer> getAll(int questionId);

    List<Answer> getByCorrect(boolean correct, int questionId);

    String checkAnswers(Test test, HttpServletRequest req);

}
