package app.service;

import app.domain.dto.exportDto.Query6Dto;

import java.util.List;

public interface SaleService {
    void saveRandomSales();
    List<Query6Dto> query6();
}
