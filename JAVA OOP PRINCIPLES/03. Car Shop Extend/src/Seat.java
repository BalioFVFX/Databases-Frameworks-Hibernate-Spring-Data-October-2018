public class Seat implements Sellable{

    private String model;
    private String color;
    private String countryProduced;
    private int horsepower;
    private double price;

    public Seat(String model, String color, int horsepower, String countryProduced, double price){
        this.model = model;
        this.color = color;
        this.horsepower = horsepower;
        this.countryProduced = countryProduced;
        this.price = price;
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

    public double getPrice(){
        return this.price;
    }


    @Override
    public String toString() {
        return "This is " + this.model + " produced in " + this.countryProduced + " and have " + Car.TIRES + " tires";
    }
}
