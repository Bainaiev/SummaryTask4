package ua.nure.bainaiev.SummaryTask4.service.impl;

import ua.nure.bainaiev.SummaryTask4.annotation.Autowired;
import ua.nure.bainaiev.SummaryTask4.annotation.Service;
import ua.nure.bainaiev.SummaryTask4.entity.Storage;
import ua.nure.bainaiev.SummaryTask4.entity.Test;
import ua.nure.bainaiev.SummaryTask4.entity.User;
import ua.nure.bainaiev.SummaryTask4.repository.StorageRepository;
import ua.nure.bainaiev.SummaryTask4.service.StorageService;
import ua.nure.bainaiev.SummaryTask4.service.TestService;

import java.util.List;

@Service
public class StorageServiceImpl implements StorageService {
    @Autowired
    private StorageRepository repository;

    @Override
    public Storage save(Storage storage) {
        return repository.save(storage);
    }

    @Override
    public Storage update(Storage storage) {
        return repository.update(storage);
    }

    @Override
    public boolean delete(int id, int testId) {
        return repository.delete(id, testId);
    }

    @Override
    public Storage get(int id, int testId) {
        return repository.get(id, testId);
    }

    @Override
    public List<Storage> getAll(int id) {
        return repository.getAll(id);
    }

}
