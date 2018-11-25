package app.dto;

import java.util.List;

public class Query4Dto {
    private String firstName;
    private String lastName;
    private Integer age;
    private Query4SoldProductsCountDto soldProducts;

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

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public Query4SoldProductsCountDto getSoldProducts() {
        return soldProducts;
    }

    public void setSoldProducts(Query4SoldProductsCountDto soldProducts) {
        this.soldProducts = soldProducts;
    }
}
