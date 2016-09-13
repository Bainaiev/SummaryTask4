package ua.nure.bainaiev.SummaryTask4.service.impl;


import ua.nure.bainaiev.SummaryTask4.annotation.Autowired;
import ua.nure.bainaiev.SummaryTask4.annotation.Service;
import ua.nure.bainaiev.SummaryTask4.entity.Test;
import ua.nure.bainaiev.SummaryTask4.entity.enums.Subject;
import ua.nure.bainaiev.SummaryTask4.repository.TestRepository;
import ua.nure.bainaiev.SummaryTask4.service.TestService;

import java.util.List;

@Service
public class TestServiceImpl implements TestService {
    @Autowired
    private TestRepository repository;

    @Override
    public Test save(Test test) {
        return repository.save(test);
    }

    @Override
    public Test update(Test test) {
        return repository.update(test);
    }

    @Override
    public boolean delete(int id) {
        return repository.delete(id);
    }

    @Override
    public Test get(int id) {
        return repository.get(id);
    }

    @Override
    public List<Test> getAll() {
        return repository.getAll();
    }

    @Override
    public List<Test> getAllBySubject(Subject subject) {
        return repository.getAllBySubject(subject);
    }

    @Override
    public List<Test> getSorted(String subjectName, String sort, String order) {
        return repository.getSorted(subjectName, sort, order);
    }
}
