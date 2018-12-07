package app.service;

import javax.xml.bind.JAXBException;
import java.io.IOException;

public interface EmployeeService {
    void saveAll() throws JAXBException;
    void saveProductiveEmployees() throws IOException;
}
