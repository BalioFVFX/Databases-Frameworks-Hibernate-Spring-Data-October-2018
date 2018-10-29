import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws IOException {
        InputStreamReader inputStreamReader = new InputStreamReader(System.in);
        BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
        String[] phoneNumbers = bufferedReader.readLine().split(" ");
        String[] urls = bufferedReader.readLine().split(" ");
        Smartphone smartphone = new Smartphone();

        for (int i = 0; i < phoneNumbers.length; i++) {
            smartphone.call(phoneNumbers[i]);
        }

        for (int i = 0; i < urls.length; i++) {
            smartphone.browse(urls[i]);
        }
    }
}
