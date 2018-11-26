package app.domain.dto.exportDto;

import java.util.Date;
import java.util.List;

public class query1Dto {
    private Long id;
    private String name;
    private Date birthDate;
    private boolean isYoungDriver;
    private List<query1DtoSales> sales;


    public query1Dto() {
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

    public Date getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(Date birthDate) {
        this.birthDate = birthDate;
    }

    public boolean isYoungDriver() {
        return isYoungDriver;
    }

    public void setYoungDriver(boolean youngDriver) {
        isYoungDriver = youngDriver;
    }

    public List<query1DtoSales> getSales() {
        return sales;
    }

    public void setSales(List<query1DtoSales> sales) {
        this.sales = sales;
    }
}
