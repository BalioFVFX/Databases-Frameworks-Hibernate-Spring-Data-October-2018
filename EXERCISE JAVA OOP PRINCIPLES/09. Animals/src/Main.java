import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
    public static void main(String[] args) throws IOException {
        InputStreamReader inputStreamReader = new InputStreamReader(System.in);
        BufferedReader reader = new BufferedReader(inputStreamReader);
        String input = reader.readLine();

        while(!input.equals("Beast!")){
            String animalType = input;
            input = reader.readLine();
            if(input.equals("Beast")){
                break;
            }
            String[] animalInfo = input.split(" ");
            try{
                switch(animalType){
                    case "Animal":
                        Animal animal = new Animal(animalInfo[0], Integer.parseInt(animalInfo[1]), animalInfo[2]);
                        break;
                    case "Dog":
                        Dog dog = new Dog(animalInfo[0], Integer.parseInt(animalInfo[1]), animalInfo[2]);
                        break;
                    case "Cat":
                        Cat cat = new Cat(animalInfo[0], Integer.parseInt(animalInfo[1]), animalInfo[2]);
                        break;
                    case "Frog":
                        Frog frog = new Frog(animalInfo[0], Integer.parseInt(animalInfo[1]), animalInfo[2]);
                        break;
                    case "Kitten":
                        Kitten kitten = new Kitten(animalInfo[0], Integer.parseInt(animalInfo[1]), "Female");
                        break;
                    case "Tomcat":
                        Tomcat tomcat = new Tomcat(animalInfo[0], Integer.parseInt(animalInfo[1]), "Male");
                        break;
                    default:
                        throw new IllegalArgumentException("Invalid input!");
                }
            }
            catch(Exception e){
                System.out.println("Invalid input!");
            }
            input = reader.readLine();
        }

    }
}
