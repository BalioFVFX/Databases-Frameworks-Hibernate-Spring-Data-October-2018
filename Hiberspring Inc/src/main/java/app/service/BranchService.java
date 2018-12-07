package app.service;

import javax.xml.bind.JAXBException;
import java.io.IOException;

public interface BranchService {
    void saveAll() throws IOException;
    void saveAllTopBranches() throws JAXBException;
}
