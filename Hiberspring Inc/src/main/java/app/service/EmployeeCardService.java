package app.service;

import java.io.IOException;

public interface EmployeeCardService {
    void saveAll() throws IOException;
    void saveFreeCards() throws IOException;
}
