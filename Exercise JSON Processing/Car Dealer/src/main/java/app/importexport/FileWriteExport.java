package app.importexport;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class FileWriteExport {
    public void writeQuery1(String jsonStr) throws IOException {
        File file = new File("ordered-customer.json");

        // creates the file
        file.createNewFile();

        // creates a FileWriter Object
        FileWriter writer = new FileWriter(file);

        // Writes the content to the file
        writer.write(jsonStr);
        writer.flush();
    }

    public void writeQuery2(String jsonStr) throws IOException {
        File file = new File("toyota-cars.json");

        // creates the file
        file.createNewFile();

        // creates a FileWriter Object
        FileWriter writer = new FileWriter(file);

        // Writes the content to the file
        writer.write(jsonStr);
        writer.flush();
    }

    public void writeQuery3(String jsonStr) throws IOException {
        File file = new File("local-suppliers.json");

        // creates the file
        file.createNewFile();

        // creates a FileWriter Object
        FileWriter writer = new FileWriter(file);

        // Writes the content to the file
        writer.write(jsonStr);
        writer.flush();
    }

    public void writeQuery4(String jsonStr) throws IOException {
        File file = new File("cars-and-parts.json");

        // creates the file
        file.createNewFile();

        // creates a FileWriter Object
        FileWriter writer = new FileWriter(file);

        // Writes the content to the file
        writer.write(jsonStr);
        writer.flush();
    }

    public void writeQuery5(String jsonStr) throws IOException {
        File file = new File("customer-total-sales.json");

        // creates the file
        file.createNewFile();

        // creates a FileWriter Object
        FileWriter writer = new FileWriter(file);

        // Writes the content to the file
        writer.write(jsonStr);
        writer.flush();
    }

    public void writeQuery6(String jsonStr) throws IOException {
        File file = new File("sales-discounts.json");

        // creates the file
        file.createNewFile();

        // creates a FileWriter Object
        FileWriter writer = new FileWriter(file);

        // Writes the content to the file
        writer.write(jsonStr);
        writer.flush();
    }
}
