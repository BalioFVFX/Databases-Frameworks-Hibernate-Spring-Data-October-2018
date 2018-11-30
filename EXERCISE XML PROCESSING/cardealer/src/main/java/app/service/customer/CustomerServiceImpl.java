package app.service.customer;

import app.domain.dto.input.query4.CustomerDto;
import app.domain.dto.input.query4.CustomerListDto;
import app.domain.dto.output.query1.Q1CustomerDto;
import app.domain.dto.output.query1.Q1CustomersList;
import app.domain.dto.output.query5.Q5CustomerDto;
import app.domain.dto.output.query5.Q5CustomerListDto;
import app.domain.entity.Customer;
import app.modelmappercfg.CMapper;
import app.repository.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CustomerServiceImpl implements CustomerSerivce{

    private final CustomerRepository customerRepository;

    @Autowired
    public CustomerServiceImpl(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }

    @Override
    public void saveAll(CustomerListDto customerListDto) {
        for (CustomerDto customer: customerListDto.getCustomers()){
            Customer entity = CMapper.mapper().map(customer, Customer.class);
            this.customerRepository.saveAndFlush(entity);
        }
    }

    @Override
    public Q1CustomersList query1() {
        List<Customer> customerEntities = this.customerRepository.query1();

        Q1CustomersList q1CustomerList = new Q1CustomersList();
        for (Customer entity: customerEntities){
            Q1CustomerDto customerDto = CMapper.mapper().map(entity, Q1CustomerDto.class);
            q1CustomerList.getCustomers().add(customerDto);
        }
        return q1CustomerList;
    }

    @Override
    public Q5CustomerListDto query5() {
        List<Customer> customerEntities = this.customerRepository.findAll();
        Q5CustomerListDto result = new Q5CustomerListDto();
        for (Customer customerEntity: customerEntities){
            int boughtCars = customerEntity.getSales().size();
            if(boughtCars < 1){
                continue;
            }
            Q5CustomerDto q5CustomerDto = CMapper.mapper().map(customerEntity, Q5CustomerDto.class);
            result.getCustomers().add(q5CustomerDto);
        }


        result.getCustomers().sort((c1, c2) -> {
            if(c2.getSpentMoney().compareTo(c1.getSpentMoney()) < 0){
                return -1;
            }
            return c2.getBoughtCars().compareTo(c1.getBoughtCars());
        });
        return result;
    }
}
