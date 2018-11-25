package app.dto;

import app.entity.Product;

import java.util.List;

public class SuccessfullySoldProducts {
    private String firstName;
    private String lastName;
    private List<SuccessfullySoldProductDetailDto> soldProducts;

    public SuccessfullySoldProducts() {
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public List<SuccessfullySoldProductDetailDto> getSoldProducts() {
        return soldProducts;
    }

    public void setSoldProducts(List<SuccessfullySoldProductDetailDto> soldProducts) {
        this.soldProducts = soldProducts;
    }
}
