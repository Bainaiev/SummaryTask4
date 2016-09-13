package ua.nure.bainaiev.SummaryTask4.repository;


import ua.nure.bainaiev.SummaryTask4.entity.Storage;

import java.util.List;

public interface StorageRepository {
    Storage save(Storage storage);
    Storage update(Storage storage);
    boolean delete(int id, int testId);
    Storage get(int id, int testId);
    List<Storage> getAll(int id);
}
