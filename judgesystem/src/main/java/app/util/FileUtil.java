package app.util;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.List;

public class FileUtil {
    public static <T> List<T> parseJson(String path, Class<T> tClass) throws IOException {
        StringBuilder stringBuilder = new StringBuilder();

        final FileReader fileReader = new FileReader(path);
        final BufferedReader bufferedReader = new BufferedReader(fileReader);
        StringBuilder strBuilder = new StringBuilder();

        String currentLine;
        while((currentLine = bufferedReader.readLine()) != null){
            strBuilder.append(currentLine);
        }

        return GsonConfig.importData(strBuilder.toString(), tClass);
    }
}
