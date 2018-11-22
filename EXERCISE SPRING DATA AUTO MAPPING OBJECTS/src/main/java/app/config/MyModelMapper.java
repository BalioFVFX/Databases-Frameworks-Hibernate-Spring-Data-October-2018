package app.config;

import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;


public class MyModelMapper {
    public static org.modelmapper.ModelMapper mapper;

    static{
        mapper = new org.modelmapper.ModelMapper();
    }

}
