import java.text.DecimalFormat;

public class Truck extends Vehicle {
    @Override
    public void refill(Double liters) {
        this.fuelQuantity += liters * 0.95;
    }

    @Override
    public boolean travel(Double distance) {
        if(super.travel(distance)){
            String num = distance.toString().indexOf(".") < 0 ? distance.toString() : distance.toString().replaceAll("0*$", "").replaceAll("\\.$", "");
            System.out.printf("Truck travelled %s km\n", num);
            return true;
        }
        System.out.println("Truck needs refueling");
        return false;
    }

    public Truck(Double fuelQuantity, Double fuelConsumption) {
        super(fuelQuantity, fuelConsumption + 1.6);
    }
}
