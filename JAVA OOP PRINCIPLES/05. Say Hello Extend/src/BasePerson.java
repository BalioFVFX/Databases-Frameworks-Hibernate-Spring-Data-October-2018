public abstract class BasePerson implements Person {

    private String name;

    public BasePerson(String name){
        this.name = name;
    }

    @Override
    public String getName() {
        return this.name;
    }

    @Override
    public abstract void sayHello();

    public void setName(String newName){
        this.name = newName;
    }
}
