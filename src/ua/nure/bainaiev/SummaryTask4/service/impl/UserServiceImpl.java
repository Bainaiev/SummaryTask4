package ua.nure.bainaiev.SummaryTask4.service.impl;


import ua.nure.bainaiev.SummaryTask4.annotation.Autowired;
import ua.nure.bainaiev.SummaryTask4.annotation.Service;
import ua.nure.bainaiev.SummaryTask4.entity.User;
import ua.nure.bainaiev.SummaryTask4.repository.UserRepository;
import ua.nure.bainaiev.SummaryTask4.service.UserService;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository repository;

    @Override
    public User save(User user) {
        return repository.save(user);
    }

    @Override
    public User update(User user) {
        return repository.update(user);
    }

    @Override
    public boolean delete(int id) {
        return repository.delete(id);
    }

    @Override
    public User get(int id) {
        return repository.get(id);
    }

    @Override
    public User getByEmail(String email) {
        return repository.getByEmail(email);
    }

    @Override
    public List<User> getAll() {
        return repository.getAll();
    }

    @Override
    public User getByLogin(String login) {
        return repository.getByLogin(login);
    }

    @Override
    public List<User> getAllStudent() {
        return repository.getAllStudent();
    }
}
