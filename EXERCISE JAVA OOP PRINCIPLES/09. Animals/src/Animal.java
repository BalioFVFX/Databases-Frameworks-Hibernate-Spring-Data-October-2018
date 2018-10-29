public class Animal implements Soundable {

    private String name;
    private int age;
    private String gender;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        if(name == null || name.equals("")){
            throw new IllegalArgumentException("Invalid input!");
        }
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        if(age < 1){
            throw new IllegalArgumentException("Invalid input!");
        }
        this.age = age;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        if(gender.toLowerCase().equals("male")== false && gender.toLowerCase().equals("female") == false){
            throw new IllegalArgumentException("Invalid input!");
        }
        this.gender = gender;
    }

    @Override
    public void produceSound() {
        System.out.println("Not implemented!");
    }

    public Animal(String name, int age, String gender){
        setName(name);
        setAge(age);
        setGender(gender);
        System.out.println(this.getClass().getName());
        System.out.println(this.name + " " + this.age + " " + this.gender);
        produceSound();
    }
}
