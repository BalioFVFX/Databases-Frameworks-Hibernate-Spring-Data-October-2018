package app.modelmappercfg;

import app.domain.dto.output.query3.Q3SupplierDto;
import app.domain.dto.output.query5.Q5CustomerDto;
import app.domain.dto.output.query6.Q6SaleDto;
import app.domain.entity.*;
import org.modelmapper.Converter;
import org.modelmapper.ModelMapper;
import org.modelmapper.PropertyMap;

import java.math.BigDecimal;
import java.util.List;

public class CMapper {
    private static ModelMapper modelMapper;

    static{
        modelMapper = new ModelMapper();

        modelMapper.addMappings(new PropertyMap<PartSupplier, Q3SupplierDto>() {
            Converter<List<Part>, Integer> partsCountConverter =
                    ctx -> ctx.getSource().size();
            @Override
            protected void configure() {
                using(partsCountConverter).map(source.getParts(), destination.getPartsCount());
            }
        });

        modelMapper.addMappings(new PropertyMap<Customer, Q5CustomerDto>() {
            Converter<List<Sale>, Integer> boughtCarsConverter =
                    ctx -> ctx.getSource().size();
            Converter<List<Sale>, BigDecimal> spentMoneyConverter = ctx -> {
             BigDecimal result = new BigDecimal(0);
                for (Sale sale: ctx.getSource()){
                 for (Part part: sale.getCar().getParts()){
                     result = result.add(part.getPrice());
                 }
             }
                return result;
            };
            @Override
            protected void configure() {
                using(boughtCarsConverter).map(source.getSales(), destination.getBoughtCars());
                using(spentMoneyConverter).map(source.getSales(), destination.getSpentMoney());
            }
        });

        modelMapper.addMappings(new PropertyMap<Sale, Q6SaleDto>() {
            Converter<Car, BigDecimal> priceConverter = ctx -> {
                BigDecimal result = new BigDecimal(0);
                for (Part part: ctx.getSource().getParts()){
                    result = result.add(part.getPrice());
                }
                return result;
            };

            Converter<Integer, Double> discountConverter = ctx -> {
                double result = Double.parseDouble(ctx.getSource().toString());
                result /= 100;
                return result;
            };

            @Override
            protected void configure() {
                map(source.getCar(), destination.getQ6CarDto());
                using(priceConverter).map(source.getCar(), destination.getPrice());
                using(discountConverter).map(source.getDiscount(), destination.getDiscount());
            }
        });
    }

    public static ModelMapper mapper(){

        return modelMapper;

    }
}
