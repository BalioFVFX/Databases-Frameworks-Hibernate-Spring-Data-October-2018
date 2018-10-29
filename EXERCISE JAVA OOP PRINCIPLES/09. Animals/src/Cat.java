public class Cat extends Animal {
    @Override
    public void produceSound() {
        System.out.println("MiauMiau");
    }
    public Cat(String name, int age, String gender){
        super(name, age, gender);
    }
}
