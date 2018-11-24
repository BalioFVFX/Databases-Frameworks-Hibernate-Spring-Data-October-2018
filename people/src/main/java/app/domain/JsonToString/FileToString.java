package app.domain.JsonToString;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

public class FileToString {
    private final FileReader fileReader;
    private final BufferedReader bufferedReader;

    public FileToString() throws FileNotFoundException {
        this.fileReader = new FileReader("C:\\Users\\Erik\\Desktop\\Skeleton\\Skeleton\\people\\src\\main\\resources\\persons.json");
        this.bufferedReader = new BufferedReader(fileReader);
    }

    public String getString() throws IOException {
        StringBuilder strBuilder = new StringBuilder();

        String currentLine;
        while((currentLine = bufferedReader.readLine()) != null){
            strBuilder.append(currentLine);
        }

        return strBuilder.toString();
    }
}
