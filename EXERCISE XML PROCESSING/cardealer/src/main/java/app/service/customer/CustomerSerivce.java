package app.service.customer;

import app.domain.dto.input.query4.CustomerListDto;
import app.domain.dto.output.query1.Q1CustomersList;
import app.domain.dto.output.query5.Q5CustomerListDto;

public interface CustomerSerivce {
    void saveAll(CustomerListDto customerListDto);
    Q1CustomersList query1();
    Q5CustomerListDto query5();
}
