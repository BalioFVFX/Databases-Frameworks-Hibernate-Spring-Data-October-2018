package app.modelmapperconfigs;

import app.domain.dtos.output.query1.ProductsInRangeDto;
import app.domain.entities.Product;
import app.domain.entities.User;
import org.modelmapper.Converter;
import org.modelmapper.ModelMapper;
import org.modelmapper.PropertyMap;

public class CMapper {
    private static ModelMapper modelMapper;

    static{
        modelMapper = new ModelMapper();
        modelMapper.addMappings(new PropertyMap<Product, ProductsInRangeDto>() {
            Converter<User, String> fullNameConverter =
                    ctx -> ctx.getSource().getFirstName() + " " + ctx.getSource().getLastName();
            @Override
            protected void configure() {
                using(fullNameConverter).map(source.getSeller(), destination.getSeller());
            }
        });
    }

    public static ModelMapper mapper(){
        return modelMapper;
    }
}
