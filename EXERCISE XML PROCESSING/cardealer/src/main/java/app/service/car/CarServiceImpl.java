package app.service.car;

import app.domain.dto.input.query3.CarListDto;
import app.domain.dto.output.query2.Q2ToyotaDto;
import app.domain.dto.output.query2.Q2ToyotaListDto;
import app.domain.dto.output.query4.Q4CarDto;
import app.domain.dto.output.query4.Q4CarListDto;
import app.domain.dto.output.query4.Q4PartListDto;
import app.domain.entity.Car;
import app.domain.entity.Part;
import app.modelmappercfg.CMapper;
import app.repository.CarRepository;
import app.repository.PartRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
    public void saveAll(CarListDto carListDto) {
        List<Part> partEntities = this.partRepository.findAll();
        Random rn = new Random();
        int numberOfRandomParts = rn.nextInt((20 - 10) + 1) + 10;
        for (int i = 0; i < carListDto.getCars().size(); i++) {
             Car entity = CMapper.mapper().map(carListDto.getCars().get(i), Car.class);
            for (int j = 0; j < numberOfRandomParts; j++) {
                int randomPartIndex = rn.nextInt(partEntities.size() - 1);
                entity.getParts().add(partEntities.get(randomPartIndex));
            }
            this.carRepository.saveAndFlush(entity);
        }
    }

    @Override
    public Q2ToyotaListDto query2() {
        List<Car> carEntities = this.carRepository.query2();

        Q2ToyotaListDto result = new Q2ToyotaListDto();
        for (Car entity: carEntities){
            Q2ToyotaDto q2ToyotaDto = CMapper.mapper().map(entity, Q2ToyotaDto.class);
            result.getCars().add(q2ToyotaDto);
        }
        return result;
    }

    @Override
    public Q4CarListDto query4() {
        List<Car> carEntities = this.carRepository.findAll();
        Q4CarListDto q4CarListDto = new Q4CarListDto();

        for (Car entity: carEntities){
            Q4PartListDto q4PartListDto = new Q4PartListDto();
            Q4CarDto q4CarDto = CMapper.mapper().map(entity, Q4CarDto.class);
            q4CarListDto.getCars().add(q4CarDto);
        }
        return q4CarListDto;
    }
}
