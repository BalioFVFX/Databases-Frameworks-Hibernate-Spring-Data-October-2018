package app.entity.dto.importjson;

import javax.validation.constraints.NotNull;

public class TownJsonDto {
    @NotNull
    private String name;
    @NotNull
    private Integer population;

    public TownJsonDto() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getPopulation() {
        return population;
    }

    public void setPopulation(Integer population) {
        this.population = population;
    }
}
