package app.service;

import app.domain.dto.exportDto.Query2Dto;
import app.domain.dto.exportDto.Query4CarDto;
import app.domain.dto.exportDto.Query4Dto;
import app.domain.dto.exportDto.Query4PartsDto;
import app.domain.dto.importDto.CarImportDto;
import app.domain.entity.Car;
import app.domain.entity.Part;
import app.domain.entity.PartSupplier;
import app.repository.CarRepository;
import app.repository.PartRepository;
import app.utils.CMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

@Service
public class CarServiceImpl implements CarService {

    private final CarRepository carRepository;
    private final PartRepository partRepository;

    @Autowired
    public CarServiceImpl(CarRepository carRepository, PartRepository partRepository) {
        this.carRepository = carRepository;
        this.partRepository = partRepository;
    }


    @Override
    public void saveAll(List<CarImportDto> carImportDtoList) {
        List<Part> partEntities = partRepository.findAll();
        Type listType = new TypeToken<List<Car>>() {
        }.getType();
        List<Car> entities = CMapper.mapper().map(carImportDtoList, listType);

        Random rn = new Random();
        for (Car entity : entities) {

            int numberOfRandomParts = rn.nextInt((20 - 10) + 1) + 10;
            List<Part> randomParts = new ArrayList<>();
            for (int i = 0; i < numberOfRandomParts; i++) {
                int randomPartIndex = rn.nextInt(partEntities.size() - 1);
                randomParts.add(partEntities.get(randomPartIndex));
            }
            entity.setParts(randomParts);
        }

        this.carRepository.saveAll(entities);
    }

    @Override
    public List<Query2Dto> query2(String make) {
        List<Car> carEntities = this.carRepository.findAllByMakeOrderByModelAscTravelledDistanceDesc(make);
        Type listType = new TypeToken<List<Query2Dto>>(){}.getType();
        List<Query2Dto> query2Dtos = CMapper.mapper().map(carEntities, listType);
        return query2Dtos;
    }

    @Override
    public List<Query4Dto> query4() {
        List<Query4Dto> result = new ArrayList<>();
        List<Car> entities = this.carRepository.findAll();
        for (Car entity: entities){
            Query4CarDto carInfoDto = CMapper.mapper().map(entity, Query4CarDto.class);
            Type listTypePartsDto = new TypeToken<List<Query4PartsDto>>() {
            }.getType();
            List<Query4PartsDto> partsInfoDto = CMapper.mapper().map(entity.getParts(), listTypePartsDto);
            Query4Dto query4Dto = new Query4Dto();
            query4Dto.setCar(carInfoDto);
            query4Dto.setParts(partsInfoDto);
            result.add(query4Dto);
        }
        return result;
    }
}
