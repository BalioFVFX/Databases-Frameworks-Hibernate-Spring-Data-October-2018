public class European implements Person {

    private String name;

    public European(String name){
        this.name = name;
    }

    public void setName(String newName){
        this.name = newName;
    }

    @Override
    public String getName() {
        return this.name;
    }

    @Override
    public void sayHello() {
        System.out.println("Hello");
    }
}
