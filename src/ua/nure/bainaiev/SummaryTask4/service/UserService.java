package ua.nure.bainaiev.SummaryTask4.service;


import ua.nure.bainaiev.SummaryTask4.annotation.Transactional;
import ua.nure.bainaiev.SummaryTask4.entity.User;

import java.util.List;

public interface UserService {
    @Transactional
    User save(User user);

    @Transactional
    User update(User user);

    boolean delete(int id);

    User get(int id);

    User getByEmail(String email);

    List<User> getAll();

    User getByLogin(String login);

    List<User> getAllStudent();
}
