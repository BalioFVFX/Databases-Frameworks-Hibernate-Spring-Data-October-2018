package app.service.sale;

import app.domain.dto.output.query6.Q6SaleDto;
import app.domain.dto.output.query6.Q6SalesListDto;
import app.domain.entity.Customer;
import app.domain.entity.Sale;
import app.modelmappercfg.CMapper;
import app.repository.CarRepository;
import app.repository.CustomerRepository;
import app.repository.SaleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;
import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;

@Service
public class SaleServiceImpl implements SaleService {
    private final SaleRepository saleRepository;
    private final CarRepository carRepository;
    private final CustomerRepository customerRepository;

    @Autowired
    public SaleServiceImpl(SaleRepository saleRepository, CarRepository carRepository, CustomerRepository customerRepository) {
        this.saleRepository = saleRepository;
        this.carRepository = carRepository;
        this.customerRepository = customerRepository;
    }

    @Override
    public void saveRandomSales() {
        long carCount = this.carRepository.count();
        long customerCount = this.customerRepository.count();
        Random rnd = new Random();
        for (long i = 0; i < customerCount; i++) {
            long randomCarrId = ThreadLocalRandom.current().nextLong((carCount - 1) + 1) + 1;
            long randomCustomerId = ThreadLocalRandom.current().nextLong((customerCount - 1) + 1) + 1;

            Sale entity = new Sale();
            entity.setCar(this.carRepository.findById(randomCarrId).orElse(null));
            Customer customerEntity = this.customerRepository.findById(randomCustomerId).orElse(null);
            entity.setCustomer(customerEntity);

            if(customerEntity.isYoungDriver()){
                int[] discounts = {5, 10, 15, 20, 30, 40, 50};
                entity.setDiscount(discounts[rnd.nextInt(discounts.length - 1)]);
            }
            else{
                entity.setDiscount(0);
            }
            this.saleRepository.saveAndFlush(entity);
        }
    }

    @Override
    public Q6SalesListDto query6() {
        List<Sale> saleEntities = this.saleRepository.findAll();
        Q6SalesListDto result = new Q6SalesListDto();
        for (Sale entity: saleEntities){
            Q6SaleDto q6SaleDto = CMapper.mapper().map(entity, Q6SaleDto.class);
            BigDecimal priceWithDiscount = new BigDecimal(0);
            priceWithDiscount = q6SaleDto.getPrice().multiply(new BigDecimal(q6SaleDto.getDiscount()));
            priceWithDiscount = q6SaleDto.getPrice().subtract(priceWithDiscount);
            q6SaleDto.setPriceWithDiscount(priceWithDiscount);
            result.getSales().add(q6SaleDto);
        }
        return result;
    }
}
