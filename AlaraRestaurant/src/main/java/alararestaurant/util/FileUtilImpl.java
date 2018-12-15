package alararestaurant.util;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class FileUtilImpl implements FileUtil {

    public FileUtilImpl() {
    }

    @Override
    public String readFile(String filePath) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new FileReader(filePath));
        StringBuilder sb = new StringBuilder();
        String line = "";

        while((line = bufferedReader.readLine()) != null){
            sb.append(line).append(System.lineSeparator());
        }

        return sb.toString();
    }
}
