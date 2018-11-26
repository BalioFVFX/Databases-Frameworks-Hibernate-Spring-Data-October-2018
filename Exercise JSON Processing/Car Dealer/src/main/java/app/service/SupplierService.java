package app.service;

import app.domain.dto.exportDto.Query3Dto;
import app.domain.dto.importDto.SupplierImportDto;

import java.util.List;

public interface SupplierService {
    void saveAll(List<SupplierImportDto> suppliers);
    List<Query3Dto> query3();
}
