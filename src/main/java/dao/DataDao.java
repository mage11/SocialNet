package dao;

import org.springframework.stereotype.Service;

@Service
public interface DataDao {
    void createTableIfNotExist();
}