package app.utils;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.text.DateFormat;
import java.util.ArrayList;
import java.util.List;

public class GsonManager {
    private static Gson gson;

    static{
        gson = new Gson().newBuilder()
                .setPrettyPrinting()
                .setDateFormat("yyyy-MM-dd'T'hh:mm:ss")
                .create();
    }

    public static  <T> List<T> importData(String jsonStr, Class<T> tClass){
        return gson.fromJson(jsonStr, TypeToken.getParameterized(ArrayList.class, tClass).getType());
    }

    public static <T> String toJsonFile(List<T> list){
        String result = gson.toJson(list);
        return result;
    }

    public static <T> String toJsonFile(T t){
        String result = gson.toJson(t);
        return result;
    }
}
