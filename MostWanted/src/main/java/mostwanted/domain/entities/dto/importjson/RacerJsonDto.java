package mostwanted.domain.entities.dto.importjson;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import javax.validation.constraints.NotNull;
import java.math.BigDecimal;

public class RacerJsonDto {
    @Expose
    @NotNull
    private String name;
    @Expose
    private Integer age;
    @Expose
    private BigDecimal bounty;
    @Expose
    @SerializedName("homeTown")
    @NotNull
    private String townName;

    public RacerJsonDto() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public BigDecimal getBounty() {
        return bounty;
    }

    public void setBounty(BigDecimal bounty) {
        this.bounty = bounty;
    }

    public String getTownName() {
        return townName;
    }

    public void setTownName(String townName) {
        this.townName = townName;
    }
}
