package app.service.car;

import app.domain.dto.input.query3.CarListDto;
import app.domain.dto.output.query2.Q2ToyotaListDto;
import app.domain.dto.output.query4.Q4CarListDto;

public interface CarService {
    void saveAll(CarListDto carListDto);
    Q2ToyotaListDto query2();
    Q4CarListDto query4();
}
