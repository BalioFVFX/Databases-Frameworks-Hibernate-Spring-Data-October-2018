package app.domain.dtos.seeders;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.List;

@XmlRootElement(name = "categories")
@XmlAccessorType(value = XmlAccessType.FIELD)
public class CategorySeederListDto {
    @XmlElement(name =  "category")
    private List<CategorySeederDto> categories;

    public List<CategorySeederDto> getCategories() {
        return categories;
    }

    public void setCategories(List<CategorySeederDto> categories) {
        this.categories = categories;
    }
}
