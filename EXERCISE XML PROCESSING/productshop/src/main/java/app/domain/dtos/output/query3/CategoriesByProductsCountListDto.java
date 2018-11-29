package app.domain.dtos.output.query3;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.ArrayList;
import java.util.List;

@XmlRootElement(name = "categories")
@XmlAccessorType(value = XmlAccessType.FIELD)
public class CategoriesByProductsCountListDto {
    @XmlElement(name = "category")

    private String name;
    private List<CategoriesByProductCountDto> categories;

    public CategoriesByProductsCountListDto() {
        this.categories = new ArrayList<>();
    }

    public List<CategoriesByProductCountDto> getCategories() {
        return categories;
    }

    public void setCategories(List<CategoriesByProductCountDto> categories) {
        this.categories = categories;
    }
}
