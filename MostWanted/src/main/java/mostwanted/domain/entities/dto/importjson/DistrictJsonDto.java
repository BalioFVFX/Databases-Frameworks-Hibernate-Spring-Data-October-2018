package mostwanted.domain.entities.dto.importjson;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import javax.validation.constraints.NotNull;

public class DistrictJsonDto {
    @Expose
    @NotNull
    private String name;

    @Expose
    @NotNull
    private String townName;

    public DistrictJsonDto() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getTownName() {
        return townName;
    }

    public void setTownName(String townName) {
        this.townName = townName;
    }
}
