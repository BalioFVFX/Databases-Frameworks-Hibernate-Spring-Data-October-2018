package app.service;

import app.domain.dto.exportDto.Query5Dto;
import app.domain.dto.exportDto.query1Dto;
import app.domain.dto.importDto.CustomerImportDto;

import java.util.List;

public interface CustomerService {
    void saveAll(List<CustomerImportDto> customerImportDtoList);
    List<query1Dto> query1();
    List<Query5Dto> query5();
}
