package app.util.files;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

public class FileManager {

    private String path;

    public FileManager() {
    }

    public String getString() throws IOException {
        final FileReader fileReader = new FileReader(path);;
        final BufferedReader bufferedReader = new BufferedReader(fileReader);
        StringBuilder strBuilder = new StringBuilder();

        String currentLine;
        while((currentLine = bufferedReader.readLine()) != null){
            strBuilder.append(currentLine);
        }

        return strBuilder.toString();
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }
}
