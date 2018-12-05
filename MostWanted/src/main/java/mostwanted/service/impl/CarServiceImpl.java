package mostwanted.service.impl;

import com.google.gson.Gson;
import mostwanted.common.Constants;
import mostwanted.domain.entities.Car;
import mostwanted.domain.entities.Racer;
import mostwanted.domain.entities.dto.importjson.CarJsonDto;
import mostwanted.repository.CarRepository;
import mostwanted.repository.RacerRepository;
import mostwanted.service.CarService;
import mostwanted.util.FileUtil;
import mostwanted.util.ValidationUtil;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;

@Service
public class CarServiceImpl implements CarService {

    private final static String CAR_JSON_FILE_PATH = System.getProperty("user.dir") + ("\\src\\main\\resources\\files\\cars.json");

    private final CarRepository carRepository;
    private final RacerRepository racerRepository;
    private final ValidationUtil validationUtil;
    private final FileUtil fileUtil;
    private final Gson gson;
    private final ModelMapper modelMapper;

    @Autowired
    public CarServiceImpl(CarRepository carRepository, RacerRepository racerRepository, ValidationUtil validationUtil, FileUtil fileUtil, Gson gson, ModelMapper modelMapper) {
        this.carRepository = carRepository;
        this.racerRepository = racerRepository;
        this.validationUtil = validationUtil;
        this.fileUtil = fileUtil;
        this.gson = gson;
        this.modelMapper = modelMapper;
    }

    @Override
    public Boolean carsAreImported() {
        return this.carRepository.count() != 0;
    }

    @Override
    public String readCarsJsonFile() throws IOException {
        return this.fileUtil.readFile(CAR_JSON_FILE_PATH);
    }

    @Override
    public String importCars(String carsFileContent) {
        StringBuilder output = new StringBuilder();

        CarJsonDto[] carJsonDtos = this.gson.fromJson(carsFileContent, CarJsonDto[].class);

        for (CarJsonDto carJsonDto : carJsonDtos) {
            // Brand or Model is null
            if(validationUtil.isValid(carJsonDto) == false){
                output.append(Constants.INCORRECT_DATA_MESSAGE).append(System.lineSeparator());
                continue;
            }
            Racer racerEntity = this.racerRepository.findByName(carJsonDto.getRacerName()).orElse(null);
            // No such racer
            if(racerEntity == null){
                output.append(Constants.INCORRECT_DATA_MESSAGE).append(System.lineSeparator());
                continue;
            }

            Car carEntityToImport = this.modelMapper.map(carJsonDto, Car.class);
            carEntityToImport.setRacer(racerEntity);
            this.carRepository.saveAndFlush(carEntityToImport);
            output.append(String.format(Constants.SUCCESSFUL_IMPORT_MESSAGE, "Car", carEntityToImport.getBrand() + " @ " + carEntityToImport.getModel())).append(System.lineSeparator());
        }

        return output.toString();
    }
}
