public class Seat implements Car{
    private String countryProduced;
    private String model;
    private String color;
    private int horsepower;


    public Seat(String model, String color, int horsepower, String countryProduced){
        this.model = model;
        this.color = color;
        this.horsepower = horsepower;
        this.countryProduced = countryProduced;
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

    public String toString(){
        return "This is " + this.model + " produced in " + this.countryProduced + " and have " + Car.TIRES + " tires";
    }
}
