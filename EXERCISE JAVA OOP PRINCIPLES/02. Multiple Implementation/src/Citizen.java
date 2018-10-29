public class Citizen implements Person, Identifiable, Birthable {
    private String name;
    private int age;
    private String id;
    private String birthdate;
    @Override
    public String getName() {
        return this.name;
    }


    @Override
    public int getAge() {
        return this.age;
    }

    @Override
    public String getBirthDate() {
        return this.birthdate;
    }

    @Override
    public String getId() {
        return this.id;
    }

    public Citizen(String name, int age, String id, String birthdate){
        this.name = name;
        this.age = age;
        this.id = id;
        this.birthdate = birthdate;
    }
}
