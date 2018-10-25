public class Audi implements Rentable {

    private String model;
    private String color;
    private String countryProduced;
    private int horsepower;
    private int minRentDays;
    private double pricePerDay;

    public Audi(String model, String color, int horsepower, String countryProduced, int minRentDays, double pricePerDay){
        this.model = model;
        this.color = color;
        this.horsepower = horsepower;
        this.countryProduced = countryProduced;
        this.minRentDays = minRentDays;
        this.pricePerDay = pricePerDay;
    }

    @Override
    public String getModel() {
        return this.model;
    }

    @Override
    public String getColor() {
        return this.color;
    }

    @Override
    public int getHorsePower() {
        return this.horsepower;
    }

    @Override
    public int getMinRentDay() {
        return this.minRentDays;
    }

    @Override
    public double getPricePerDay() {
        return this.pricePerDay;
    }

    @Override
    public String toString() {
        return "This is " + this.model + " produced in " + this.countryProduced + " and have " + Car.TIRES + " tires";
    }
}
