package app.service;

import app.domain.dto.exportDto.Query2Dto;
import app.domain.dto.exportDto.Query4Dto;
import app.domain.dto.importDto.CarImportDto;

import java.util.List;

public interface CarService {
    void saveAll(List<CarImportDto> carImportDtoList);
    List<Query2Dto> query2(String make);
    List<Query4Dto> query4();
}
