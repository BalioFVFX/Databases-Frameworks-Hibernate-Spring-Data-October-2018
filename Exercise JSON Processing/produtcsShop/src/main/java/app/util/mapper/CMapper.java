package app.util.mapper;

import app.dto.SuccessfullySoldProductDetailDto;
import app.entity.User;
import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;

public class CMapper {
    private static ModelMapper modelMapper;

    static{
        modelMapper = new ModelMapper();
        modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);
    }

    public static ModelMapper mapper(){
        return modelMapper;
    }
}
