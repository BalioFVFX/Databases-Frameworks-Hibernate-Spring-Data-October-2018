public class Frog extends Animal {
    @Override
    public void produceSound() {
        System.out.println("Frogggg");
    }

    public Frog(String name, int age, String gender) {
        super(name, age, gender);
    }
}
