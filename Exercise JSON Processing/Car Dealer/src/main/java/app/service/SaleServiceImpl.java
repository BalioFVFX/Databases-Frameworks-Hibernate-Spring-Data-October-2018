package app.service;

import app.domain.dto.exportDto.Query4CarDto;
import app.domain.dto.exportDto.Query6Dto;
import app.domain.entity.Car;
import app.domain.entity.Customer;
import app.domain.entity.Part;
import app.domain.entity.Sale;
import app.repository.CarRepository;
import app.repository.CustomerRepository;
import app.repository.SaleRepository;
import app.utils.CMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

@Service
public class SaleServiceImpl implements SaleService {
    private final SaleRepository saleRepository;
    private final CustomerRepository customerRepository;
    private final CarRepository carRepository;

    @Autowired
    public SaleServiceImpl(SaleRepository saleRepository, CustomerRepository customerRepository, CarRepository carRepository) {
        this.saleRepository = saleRepository;
        this.customerRepository = customerRepository;
        this.carRepository = carRepository;
    }


    @Override
    public void saveRandomSales() {
        List<Customer> customerEntities = this.customerRepository.findAll();
        List<Car> carEntities = this.carRepository.findAll();
        List<Sale> saleEntities = new ArrayList<>();
        int[] disscounts = {5, 10, 15, 20, 30, 40, 50};
        Random rnd = new Random();
        for (int i = 0; i < customerEntities.size(); i++) {

            Sale sale = new Sale();

            int randomCarIndex = rnd.nextInt(carEntities.size() - 1);
            int randomCustomerIndex = rnd.nextInt(customerEntities.size() - 1);
            int randomDisscount = 0;
            if(customerEntities.get(i).isYoungDriver() == true){
                randomDisscount = disscounts[rnd.nextInt(disscounts.length - 1)];
            }
            sale.setCar(carEntities.get(randomCarIndex));
            sale.setCustomer(customerEntities.get(randomCustomerIndex));
            sale.setDiscount(randomDisscount);
            saleEntities.add(sale);
        }
        this.saleRepository.saveAll(saleEntities);
    }

    @Override
    public List<Query6Dto> query6() {
        List<Sale> saleEntities = this.saleRepository.findAll();
        List<Query6Dto> query6Dtos = new ArrayList<>();
        for (Sale entity: saleEntities){
            Query6Dto query6Dto = new Query6Dto();
            String customerName = entity.getCustomer().getName();
            Double discount = (double)entity.getDiscount();
            discount /= 100;
            BigDecimal price = new BigDecimal(0);
            BigDecimal priceWithDiscount = new BigDecimal(0);
            for (Part part: entity.getCar().getParts()){
                price = price.add(part.getPrice());
            }
            System.out.println();
            priceWithDiscount = price.multiply(new BigDecimal(discount));
            priceWithDiscount = price.subtract(priceWithDiscount);
            Query4CarDto query4CarDto = CMapper.mapper().map(entity.getCar(), Query4CarDto.class);

            query6Dto.setCar(query4CarDto);
            query6Dto.setCustomerName(customerName);
            query6Dto.setDiscount(discount);
            query6Dto.setPrice(price);
            query6Dto.setPriceWithDiscount(priceWithDiscount);
            query6Dtos.add(query6Dto);
        }
        return query6Dtos;
    }
}
