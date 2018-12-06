package app.service.submission;

import javax.xml.bind.JAXBException;

public interface SubmissionService {
    void saveSubmissions() throws JAXBException;
}
