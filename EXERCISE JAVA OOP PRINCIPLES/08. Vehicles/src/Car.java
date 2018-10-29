import java.text.DecimalFormat;

public class Car extends Vehicle {
    @Override
    public void refill(Double liters) {
        super.refill(liters);
    }

    @Override
    public boolean travel(Double distance) {
        if(super.travel(distance)) {
            String num = distance.toString().indexOf(".") < 0 ? distance.toString() : distance.toString().replaceAll("0*$", "").replaceAll("\\.$", "");
            System.out.printf("Car travelled %s km\n", num);
            return true;
        }
        System.out.println("Car needs refueling");
        return false;
    }

    public Car(Double fuelQuantity, Double fuelConsumption) {
        super(fuelQuantity, fuelConsumption + 0.9);
    }
}
