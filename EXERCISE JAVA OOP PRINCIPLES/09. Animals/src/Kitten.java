public class Kitten extends Animal {
    @Override
    public void produceSound() {
        System.out.println("Miau");
    }

    public Kitten(String name, int age, String gender) {
        super(name, age, gender);
        if(!gender.toLowerCase().equals("female")){
            throw new IllegalArgumentException("Invalid input!");
        }
    }
}
