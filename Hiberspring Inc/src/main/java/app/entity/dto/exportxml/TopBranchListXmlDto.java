package app.entity.dto.exportxml;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.ArrayList;
import java.util.List;

@XmlRootElement(name = "branches")
@XmlAccessorType(value = XmlAccessType.FIELD)
public class TopBranchListXmlDto {
    @XmlElement(name = "branch")
    private List<TopBranchXmlDto> topBranches;

    public TopBranchListXmlDto() {
        this.topBranches = new ArrayList<>();
    }

    public List<TopBranchXmlDto> getTopBranches() {
        return topBranches;
    }

    public void setTopBranches(List<TopBranchXmlDto> topBranches) {
        this.topBranches = topBranches;
    }
}
