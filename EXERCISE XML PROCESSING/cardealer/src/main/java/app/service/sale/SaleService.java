package app.service.sale;

import app.domain.dto.output.query6.Q6SalesListDto;

public interface SaleService {
    void saveRandomSales();
    Q6SalesListDto query6();
}
