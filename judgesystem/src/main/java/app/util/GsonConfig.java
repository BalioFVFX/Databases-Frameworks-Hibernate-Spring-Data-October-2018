package app.util;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.util.ArrayList;
import java.util.List;

public class GsonConfig {
    private static Gson gson;

    static{
        gson = new Gson().newBuilder()
                .setPrettyPrinting()
                .create();
    }

    public static <T> List<T> importData(String jsonStr, Class<T> tClass){
        return gson.fromJson(jsonStr, TypeToken.getParameterized(ArrayList.class, tClass).getType());
    }
}
