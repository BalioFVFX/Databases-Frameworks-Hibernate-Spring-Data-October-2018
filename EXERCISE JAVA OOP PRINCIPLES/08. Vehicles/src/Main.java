import java.util.Arrays;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String[] carInfo = scanner.nextLine().split(" ");
        String[] truckInfo = scanner.nextLine().split(" ");
        int commandsLength = Integer.parseInt(scanner.nextLine());
        Car car = new Car(Double.parseDouble(carInfo[1]), Double.parseDouble(carInfo[2]));
        Truck truck = new Truck(Double.parseDouble(truckInfo[1]), Double.parseDouble(truckInfo[2]));

        for (int i = 0; i < commandsLength; i++) {
            String[] input = scanner.nextLine().split(" ");
            if(input[1].equals("Car")){
                if(input[0].equals("Drive")){
                    car.travel(Double.parseDouble(input[2]));
                }
                else if(input[0].equals("Refuel")){
                    car.refill(Double.parseDouble(input[2]));
                }
            }
            else if(input[1].equals("Truck")){
                if(input[0].equals("Drive")){
                    truck.travel(Double.parseDouble(input[2]));
                }
                else if(input[0].equals("Refuel")){
                    truck.refill(Double.parseDouble(input[2]));
                }
            }
        }

        System.out.printf("Car: %.02f\n", car.fuelQuantity);
        System.out.printf("Truck: %.02f\n", truck.fuelQuantity);

    }
}
