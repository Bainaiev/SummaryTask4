package ua.nure.bainaiev.SummaryTask4.service;


import ua.nure.bainaiev.SummaryTask4.annotation.Transactional;
import ua.nure.bainaiev.SummaryTask4.bean.UserBean;
import ua.nure.bainaiev.SummaryTask4.entity.User;

import java.util.List;
import java.util.Map;

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

    Map<UserBean, Integer> getAllResult();
}
