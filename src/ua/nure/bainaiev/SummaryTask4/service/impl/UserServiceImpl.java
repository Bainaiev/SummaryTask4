package ua.nure.bainaiev.SummaryTask4.service.impl;


import ua.nure.bainaiev.SummaryTask4.annotation.Autowired;
import ua.nure.bainaiev.SummaryTask4.annotation.Service;
import ua.nure.bainaiev.SummaryTask4.bean.UserBean;
import ua.nure.bainaiev.SummaryTask4.entity.User;
import ua.nure.bainaiev.SummaryTask4.repository.UserRepository;
import ua.nure.bainaiev.SummaryTask4.service.UserService;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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

    @Override
    public Map<UserBean, Integer> getAllResult() {
        List<UserBean> list = repository.getAllResult();
        Map<UserBean, Integer> newList = new HashMap<>();

//        for(UserBean userBean: list){
//            if(newList.get(userBean) != null){
//                int temp = (Integer) newList.get(userBean) + userBean.getResult();
//                newList.put(userBean, temp);
//                System.out.println(temp);
//            }
//            newList.put(userBean, userBean.getResult());
//
//        }

        for(UserBean userBean: list){
            System.out.println(userBean.getFirstName() + " " + userBean.getResult());
        }

        return newList;
    }

    ;
}
