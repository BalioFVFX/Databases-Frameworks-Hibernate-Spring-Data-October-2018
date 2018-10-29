public class Tomcat extends Animal {
    @Override
    public void produceSound() {
        System.out.println("Give me one million b***h");
    }

    public Tomcat(String name, int age, String gender) {
        super(name, age, gender);
        if(!gender.toLowerCase().equals("male")){
            throw new IllegalArgumentException("Invalid input!");
        }
    }
}
