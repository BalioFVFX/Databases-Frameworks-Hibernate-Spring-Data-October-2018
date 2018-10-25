public class Bulgarian implements Person, Runnable
{
    private String name;

    public Bulgarian(String name){
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
        System.out.println("Здравей");
    }

    @Override
    public void run() {

    }
}
