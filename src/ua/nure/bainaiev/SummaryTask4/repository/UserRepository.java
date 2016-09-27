package ua.nure.bainaiev.SummaryTask4.repository;


import ua.nure.bainaiev.SummaryTask4.entity.User;

import java.util.List;

public interface UserRepository {
    User save(User user);
    User update(User user);
    boolean delete(int id);
    User get(int id);

    User getByEmail(String email);
    List<User> getAll();
    User getByLogin(String login);
    List<User> getAllStudent();



}
