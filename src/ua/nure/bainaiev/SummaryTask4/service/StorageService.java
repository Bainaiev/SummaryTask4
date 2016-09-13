package ua.nure.bainaiev.SummaryTask4.service;


import ua.nure.bainaiev.SummaryTask4.annotation.Transactional;
import ua.nure.bainaiev.SummaryTask4.entity.Storage;
import ua.nure.bainaiev.SummaryTask4.entity.User;

import java.util.List;

public interface StorageService {
    @Transactional
    Storage save(Storage storage);

    @Transactional
    Storage update(Storage storage);

    boolean delete(int id, int testId);

    Storage get(int id, int testId);

    List<Storage> getAll(int id);

}
