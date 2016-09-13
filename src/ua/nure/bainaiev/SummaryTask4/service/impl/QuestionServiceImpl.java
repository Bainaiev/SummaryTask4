package ua.nure.bainaiev.SummaryTask4.service.impl;

import ua.nure.bainaiev.SummaryTask4.annotation.Autowired;
import ua.nure.bainaiev.SummaryTask4.annotation.Service;
import ua.nure.bainaiev.SummaryTask4.entity.Question;
import ua.nure.bainaiev.SummaryTask4.repository.QuestionRepository;
import ua.nure.bainaiev.SummaryTask4.service.QuestionService;

import java.util.List;

@Service
public class QuestionServiceImpl implements QuestionService {
    @Autowired
    private QuestionRepository repository;

    @Override
    public Question save(Question question) {
        return repository.save(question);
    }

    @Override
    public Question update(Question question) {
        return repository.update(question);
    }

    @Override
    public boolean delete(int id) {
        return repository.delete(id);
    }

    @Override
    public Question get(int id) {
        return repository.get(id);
    }

    @Override
    public List<Question> getAll(int testId) {
        return repository.getAll(testId);
    }
}
