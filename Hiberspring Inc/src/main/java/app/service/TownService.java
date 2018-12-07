package app.service;

import javax.xml.bind.JAXBException;
import java.io.IOException;

public interface TownService {
    void saveAll() throws IOException;
    void saveTowns() throws JAXBException;
}
