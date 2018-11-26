package app.importexport;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class FilerReadImport {

    public String readSuppliers() throws IOException {
        StringBuilder stringBuilder = new StringBuilder();

        final FileReader fileReader = new FileReader("C:\\Users\\Erik\\Desktop\\Car Dealer\\src\\main\\resources\\suppliers.json");
        final BufferedReader bufferedReader = new BufferedReader(fileReader);
        StringBuilder strBuilder = new StringBuilder();

        String currentLine;
        while((currentLine = bufferedReader.readLine()) != null){
            strBuilder.append(currentLine);
        }

        return strBuilder.toString();
    }

    public String readParts() throws IOException {
        StringBuilder stringBuilder = new StringBuilder();

        final FileReader fileReader = new FileReader("C:\\Users\\Erik\\Desktop\\Car Dealer\\src\\main\\resources\\parts.json");
        final BufferedReader bufferedReader = new BufferedReader(fileReader);
        StringBuilder strBuilder = new StringBuilder();

        String currentLine;
        while((currentLine = bufferedReader.readLine()) != null){
            strBuilder.append(currentLine);
        }

        return strBuilder.toString();
    }

    public String readCars() throws IOException {
        StringBuilder stringBuilder = new StringBuilder();

        final FileReader fileReader = new FileReader("C:\\Users\\Erik\\Desktop\\Car Dealer\\src\\main\\resources\\cars.json");
        final BufferedReader bufferedReader = new BufferedReader(fileReader);
        StringBuilder strBuilder = new StringBuilder();

        String currentLine;
        while((currentLine = bufferedReader.readLine()) != null){
            strBuilder.append(currentLine);
        }

        return strBuilder.toString();
    }


    public String readCustomers() throws IOException {
        StringBuilder stringBuilder = new StringBuilder();

        final FileReader fileReader = new FileReader("C:\\Users\\Erik\\Desktop\\Car Dealer\\src\\main\\resources\\customers.json");
        final BufferedReader bufferedReader = new BufferedReader(fileReader);
        StringBuilder strBuilder = new StringBuilder();

        String currentLine;
        while((currentLine = bufferedReader.readLine()) != null){
            strBuilder.append(currentLine);
        }

        return strBuilder.toString();
    }

}
