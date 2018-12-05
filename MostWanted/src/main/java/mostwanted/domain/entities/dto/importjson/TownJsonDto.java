package mostwanted.domain.entities.dto.importjson;

import com.google.gson.annotations.Expose;

import javax.validation.constraints.NotNull;

public class TownJsonDto {
    @Expose
    @NotNull
    private String name;

    public TownJsonDto() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
