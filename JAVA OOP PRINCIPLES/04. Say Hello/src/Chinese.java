public class Chinese implements Person{
    private String name;

    public Chinese(String name){
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
        System.out.println("Djydjybydjy");
    }
}
