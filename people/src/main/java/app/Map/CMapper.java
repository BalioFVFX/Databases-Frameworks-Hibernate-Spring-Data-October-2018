package app.Map;

import org.modelmapper.ModelMapper;

public class CMapper {
    private static ModelMapper instance;

    static{
        instance = new ModelMapper();
    }

    public static ModelMapper getMapper(){
        return instance;
    }
}
