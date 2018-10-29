import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

public class Main {
    public static void main(String[] args) throws IOException {
        InputStreamReader inputStreamReader = new InputStreamReader(System.in);
        BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
        String input = bufferedReader.readLine();
        ArrayList<String> visitors = new ArrayList<String>();
        while(!input.equals("End")){
            visitors.add(input);
            input = bufferedReader.readLine();
        }
        input = bufferedReader.readLine();

        for (String visitor: visitors){
            if(visitor.endsWith(input) && (visitor.split(" ")[0].equals("Citizen") ||
                    visitor.split(" ")[0].equals("Pet")) ){
                System.out.println(visitor.split(" ")[visitor.split(" ").length - 1]);
            }
        }
    }
}
