public class Ferrari implements Car{
    private String driver;
    private String model;

    @Override
    public String useBreaks() {
        return "Brakes!";
    }

    @Override
    public String pushTheGas() {
        return "Zadu6avam sA!";
    }

    @Override
    public String getModel() {
        return this.model;
    }

    public Ferrari(String driverName){
        this.driver = driverName;
        this.model = "488-Spider";
        System.out.println(this.model + "/" + useBreaks() + "/" + pushTheGas() + "/" + this.driver);
    }
}
