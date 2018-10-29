public class Vehicle implements Fuelable {
    Double fuelQuantity;
    Double fuelConsumption;

    @Override
    public void refill(Double liters) {
        this.fuelQuantity += liters;
    }

    public boolean travel(Double distance){
        if(this.fuelConsumption * distance <= this.fuelQuantity){
            this.fuelQuantity -= this.fuelConsumption * distance;
            return true;
        }
        return false;
    }

    public Vehicle(Double fuelQuantity, Double fuelConsumption){
        this.fuelQuantity = fuelQuantity;
        this.fuelConsumption = fuelConsumption;
    }
}
