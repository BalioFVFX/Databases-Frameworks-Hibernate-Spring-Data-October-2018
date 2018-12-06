package app.domain.dto.importjson.category;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class CategoryJsonDto {
    private Long id;
    private String name;
    private CategoryJsonDto category;
    @SerializedName("categories")
    private List<CategoryJsonDto> subcategories;

    public CategoryJsonDto() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public CategoryJsonDto getCategory() {
        return category;
    }

    public void setCategory(CategoryJsonDto category) {
        this.category = category;
    }

    public List<CategoryJsonDto> getSubcategories() {
        return subcategories;
    }

    public void setSubcategories(List<CategoryJsonDto> subcategories) {
        this.subcategories = subcategories;
    }
}
