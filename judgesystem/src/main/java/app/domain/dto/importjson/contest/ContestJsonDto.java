package app.domain.dto.importjson.contest;

import app.domain.dto.importjson.category.CategoryJsonDto;
import app.domain.dto.importjson.strategy.StrategyJsonDto;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class ContestJsonDto {
    private Long id;
    private String name;
    private CategoryJsonDto category;
    @SerializedName("allowedStrategies")
    private List<StrategyJsonDto> strategies;

    public ContestJsonDto() {
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

    public List<StrategyJsonDto> getStrategies() {
        return strategies;
    }

    public void setStrategies(List<StrategyJsonDto> strategies) {
        this.strategies = strategies;
    }
}
