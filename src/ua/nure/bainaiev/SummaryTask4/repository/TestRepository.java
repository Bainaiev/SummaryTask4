package ua.nure.bainaiev.SummaryTask4.repository;


import ua.nure.bainaiev.SummaryTask4.entity.Test;
import ua.nure.bainaiev.SummaryTask4.entity.enums.Subject;

import java.util.List;

public interface TestRepository {
    Test save(Test test);
    Test update(Test test);
    boolean delete(int id);
    Test get(int id);

    List<Test> getAll();
    List<Test> getAllBySubject(Subject subject);

    List<Test> getSorted(String subjectName, String sort, String order);
}
