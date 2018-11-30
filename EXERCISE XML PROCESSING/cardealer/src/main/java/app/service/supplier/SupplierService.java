package app.service.supplier;

import app.domain.dto.input.query1.SupplierListDto;
import app.domain.dto.output.query3.Q3SupplierListDto;

public interface SupplierService {
    void saveAll(SupplierListDto supplierListDto);
    Q3SupplierListDto query3();
}
