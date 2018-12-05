package mostwanted.service.impl;

import com.google.gson.Gson;
import mostwanted.common.Constants;
import mostwanted.domain.entities.Car;
import mostwanted.domain.entities.Racer;
import mostwanted.domain.entities.Town;
import mostwanted.domain.entities.dto.importjson.RacerJsonDto;
import mostwanted.repository.RacerRepository;
import mostwanted.repository.TownRepository;
import mostwanted.service.RacerService;
import mostwanted.util.FileUtil;
import mostwanted.util.ValidationUtil;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.List;

@Service
public class RacerServiceImpl implements RacerService {

    private final static String RACER_JSON_FILE_PATH = System.getProperty("user.dir") + ("\\src\\main\\resources\\files\\racers.json");

    private final RacerRepository racerRepository;
    private final TownRepository townRepository;
    private final ValidationUtil validationUtil;
    private final Gson gson;
    private final ModelMapper modelMapper;
    private final FileUtil fileUtil;

    @Autowired
    public RacerServiceImpl(RacerRepository racerRepository, TownRepository townRepository, ValidationUtil validationUtil, Gson gson, ModelMapper modelMapper, FileUtil fileUtil) {
        this.racerRepository = racerRepository;
        this.townRepository = townRepository;
        this.validationUtil = validationUtil;
        this.gson = gson;
        this.modelMapper = modelMapper;
        this.fileUtil = fileUtil;
    }

    @Override
    public Boolean racersAreImported() {
        return this.racerRepository.count() != 0;
    }

    @Override
    public String readRacersJsonFile() throws IOException {
        return this.fileUtil.readFile(RACER_JSON_FILE_PATH);
    }

    @Override
    public String importRacers(String racersFileContent) {
        StringBuilder output = new StringBuilder();
        RacerJsonDto[] racerJsonDtos = this.gson.fromJson(racersFileContent, RacerJsonDto[].class);
        for (RacerJsonDto racerJsonDto : racerJsonDtos) {
            if(this.validationUtil.isValid(racerJsonDto) == false){
                output.append(Constants.INCORRECT_DATA_MESSAGE).append(System.lineSeparator());
                continue;
            }
            Racer racerEntity = this.racerRepository.findByName(racerJsonDto.getName()).orElse(null);

            if(racerEntity != null){
                output.append(Constants.DUPLICATE_DATA_MESSAGE).append(System.lineSeparator());
                continue;
            }

            Town townEntity = this.townRepository.findByName(racerJsonDto.getTownName()).orElse(null);

            if(townEntity == null){
                output.append(Constants.INCORRECT_DATA_MESSAGE).append(System.lineSeparator());
                continue;
            }

            Racer racerEntityToImport = this.modelMapper.map(racerJsonDto, Racer.class);
            racerEntityToImport.setHomeTown(townEntity);
            this.racerRepository.saveAndFlush(racerEntityToImport);
            output.append(String.format(Constants.SUCCESSFUL_IMPORT_MESSAGE, "Racer", racerEntityToImport.getName())).append(System.lineSeparator());

        }

        return output.toString();
    }

    @Override
    public String exportRacingCars() {
        StringBuilder output = new StringBuilder();
        List<Racer> racers = this.racerRepository.racingCars();

        for (Racer racer : racers) {
            output.append(String.format("Name: %s", racer.getName())).append(System.lineSeparator());
            output.append("Cars:").append(System.lineSeparator());
            for (Car car : racer.getCars()) {
                output.append(String.format("%s %s %s", car.getBrand(), car.getModel(), car.getYearOfProduction())).append(System.lineSeparator());
            }
            output.append(System.lineSeparator());
        }

        return output.toString();
    }
}
