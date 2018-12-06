package app.domain.dto.importxml.submission;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.ArrayList;
import java.util.List;

@XmlRootElement(name = "submissions")
@XmlAccessorType(value = XmlAccessType.FIELD)
public class SubmissionListXml {
    @XmlElement(name = "submission")
    private List<SubmissionXml> submissions;

    public SubmissionListXml() {
        this.submissions = new ArrayList<>();
    }

    public List<SubmissionXml> getSubmissions() {
        return submissions;
    }

    public void setSubmissions(List<SubmissionXml> submissions) {
        this.submissions = submissions;
    }
}
