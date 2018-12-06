package app.mapperconfig;

import org.modelmapper.ModelMapper;

public class CMapper {
    private static ModelMapper modelMapper;

    static{
        modelMapper = new ModelMapper();
    }

    public static ModelMapper mapper(){
        return modelMapper;
    }
}
