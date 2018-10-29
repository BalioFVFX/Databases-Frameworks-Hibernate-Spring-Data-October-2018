import java.util.regex.Pattern;

public class Smartphone implements Callable, Browsable{

    @Override
    public void browse(String url) {
        if(!Pattern.matches("^[\\D]*$", url)){
            System.out.println("Invalid URL!");
        }
        else{
            System.out.println("Browsing: " + url + "!");
        }
    }

    @Override
    public void call(String number) {
        if(!Pattern.matches("^[0-9]*$", number)){
            System.out.println("Invalid number!");
        }
        else{
            System.out.println("Calling... " + number);
        }
    }

    public Smartphone(){

    }
}
