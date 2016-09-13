package ua.nure.bainaiev.SummaryTask4.service.impl;

import ua.nure.bainaiev.SummaryTask4.annotation.Autowired;
import ua.nure.bainaiev.SummaryTask4.annotation.Service;
import ua.nure.bainaiev.SummaryTask4.entity.Answer;
import ua.nure.bainaiev.SummaryTask4.entity.Question;
import ua.nure.bainaiev.SummaryTask4.entity.Test;
import ua.nure.bainaiev.SummaryTask4.repository.AnswerRepository;
import ua.nure.bainaiev.SummaryTask4.service.AnswerService;
import ua.nure.bainaiev.SummaryTask4.util.constant.Constants;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Service
public class AnswerServiceImpl implements AnswerService {
    @Autowired
    private AnswerRepository repository;

    @Override
    public Answer save(Answer answer) {
        return repository.save(answer);
    }

    @Override
    public Answer update(Answer answer) {
        return repository.update(answer);
    }

    @Override
    public boolean delete(int id) {
        return repository.delete(id);
    }

    @Override
    public Answer get(int id) {
        return repository.get(id);
    }

    @Override
    public List<Answer> getAll(int questionId) {
        return repository.getAll(questionId);
    }

    @Override
    public List<Answer> getByCorrect(boolean correct, int questionId) {
        return repository.getByCorrect(correct, questionId);
    }

    @Override
    public String checkAnswers(Test test, HttpServletRequest req) {
        int count = 0;
        int correctAnswers = 0;
        for (Question q : test.getQuestions()) {
            count++;
            Integer id = q.getId();

            String[] answersCorrect = req.getParameterValues(id.toString());
            if (answersCorrect == null) {
                continue;
            }

            boolean flag = false;
            for (String correct : answersCorrect) {
                if (correct.toLowerCase().equals(Constants.FALSE)) {
                    flag = true;
                }
            }

            if (flag) {
                continue;
            }


            int i = 0;
            for (Answer answer : q.getAnswers()) {
                if (answer.getCorrect().equals(true)) {
                    i++;
                }
            }

            if (answersCorrect.length != i) {
                continue;
            }
            correctAnswers++;
        }
        return correctAnswers + " / " + count;
    }

}
