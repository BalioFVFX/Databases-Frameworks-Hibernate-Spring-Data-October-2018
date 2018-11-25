package app.util.gson;

import app.dto.UserCreateDto;
import app.entity.User;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;


import java.lang.reflect.Array;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class GsonManager {
    private static Gson gson;

    static{
        gson = new Gson().newBuilder().setPrettyPrinting().create();
    }

    public <T> List<T> parseMany(String jsonStr, Class<T> tClass){
        List<T> result = new ArrayList<T>();
        Type type = TypeToken.getParameterized(ArrayList.class).getType();
        result = gson.fromJson(jsonStr, TypeToken.getParameterized(ArrayList.class, tClass).getType());

        return result;

    }

    public <T> T parse(String jsonStr, Class<T> tClass){
        return gson.fromJson(jsonStr, tClass);
    }

    public <T> String toJsonFile(List<T> list){
        String result = gson.toJson(list);
        return result;
    }

    public <T> String toJsonFile(T t){
        String result = gson.toJson(t);
        return result;
    }
}
