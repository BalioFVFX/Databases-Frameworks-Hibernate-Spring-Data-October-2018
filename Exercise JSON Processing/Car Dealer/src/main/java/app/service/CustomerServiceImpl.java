package app.service;

import app.domain.dto.exportDto.Query5Dto;
import app.domain.dto.exportDto.query1Dto;
import app.domain.dto.exportDto.query1DtoSales;
import app.domain.dto.importDto.CustomerImportDto;
import app.domain.entity.Customer;
import app.domain.entity.Part;
import app.domain.entity.PartSupplier;
import app.domain.entity.Sale;
import app.repository.CustomerRepository;
import app.utils.CMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.lang.reflect.Type;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Service
public class CustomerServiceImpl implements CustomerService {

    private final CustomerRepository customerRepository;

    @Autowired
    public CustomerServiceImpl(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }


    @Override
    public void saveAll(List<CustomerImportDto> customerImportDtoList) {
        Type listType = new TypeToken<List<Customer>>() {}.getType();
        List<Customer> entities = CMapper.mapper().map(customerImportDtoList, listType);
        this.customerRepository.saveAll(entities);
    }

    @Override
    public List<query1Dto> query1() {
        List<Customer> customerEntities = this.customerRepository.query1();
        List<query1Dto> result = new ArrayList<>();
        for (Customer entity: customerEntities){
            Type listType = new TypeToken<List<query1DtoSales>>() {}.getType();
            query1Dto query1Dto = CMapper.mapper().map(entity, app.domain.dto.exportDto.query1Dto.class);
            List<query1DtoSales> query1DtoSales = CMapper.mapper().map(entity.getSales(), listType);
            query1Dto.setSales(query1DtoSales);
            result.add(query1Dto);
        }
        return result;
    }

    @Override
    public List<Query5Dto> query5() {
        List<Customer> entities = this.customerRepository.findAll();
        List<Query5Dto> query5Dtos = new ArrayList<>();
        for (Customer entity: entities){

            Query5Dto query5Dto = new Query5Dto();
            BigDecimal spentMoney = new BigDecimal(0);
            Integer boughtCars = entity.getSales().size();
            if(boughtCars == 0){
                continue;
            }
            for (Sale sales:  entity.getSales()){
                for (Part parts: sales.getCar().getParts()){
                    spentMoney = spentMoney.add(parts.getPrice());
                }
            }
            query5Dto.setFullName(entity.getName());
            query5Dto.setBoughtCars(boughtCars);
            query5Dto.setSpentMoney(spentMoney);
            query5Dtos.add(query5Dto);
        }

        query5Dtos.sort((q1, q2) -> {
            int comp = q2.getSpentMoney().compareTo(q1.getSpentMoney());
            if(comp == 0){
                return  q2.getBoughtCars().compareTo(q1.getBoughtCars());
            }
            return comp;
        });

        return query5Dtos;
    }
}
