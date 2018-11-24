package app.GsonManager;

import app.domain.dto.PersonCreateDto;
import app.domain.model.Person;
import com.google.gson.Gson;

public class GsonManager {
    private static Gson gson;

    static {
        gson = new Gson().newBuilder()
                .setPrettyPrinting()
                .create();
    }

    public <T> T parseJson(String jsonStr, Class<T> tClass){
        return gson.fromJson(jsonStr, tClass);
    }
}
