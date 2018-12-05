package mostwanted.service.impl;


import com.google.gson.Gson;
import mostwanted.common.Constants;
import mostwanted.domain.entities.Town;
import mostwanted.domain.entities.dto.importjson.TownJsonDto;
import mostwanted.repository.TownRepository;
import mostwanted.service.TownService;
import mostwanted.util.FileUtil;
import mostwanted.util.ValidationUtil;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.List;

@Service
public class TownServiceImpl implements TownService {

    private final static String TOWN_JSON_FILE_PATH = System.getProperty("user.dir") + ("\\src\\main\\resources\\files\\towns.json");

    private final ValidationUtil validationUtil;
    private final TownRepository townRepository;
    private final Gson gson;
    private final ModelMapper modelMapper;
    private final FileUtil fileUtil;

    @Autowired
    public TownServiceImpl(ValidationUtil validationUtil, TownRepository townRepository, Gson gson, ModelMapper modelMapper, FileUtil fileUtil) {
        this.validationUtil = validationUtil;
        this.townRepository = townRepository;
        this.gson = gson;
        this.modelMapper = modelMapper;
        this.fileUtil = fileUtil;
    }

    @Override
    public Boolean townsAreImported() {
        return this.townRepository.count() != 0;
    }

    @Override
    public String readTownsJsonFile() throws IOException {
        return this.fileUtil.readFile(TOWN_JSON_FILE_PATH);
    }

    @Override
    public String importTowns(String townsFileContent) {
        StringBuilder output = new StringBuilder();

        TownJsonDto[] townJsonDtos = this.gson.fromJson(townsFileContent, TownJsonDto[].class);

        for (TownJsonDto townJsonDto : townJsonDtos) {
            // Validation
            if(validationUtil.isValid(townJsonDto) == false){
                output.append(Constants.INCORRECT_DATA_MESSAGE).append(System.lineSeparator());
                continue;
            }
            Town townEntity = this.townRepository.findByName(townJsonDto.getName()).orElse(null);
            if(townEntity != null){
                output.append(Constants.DUPLICATE_DATA_MESSAGE).append(System.lineSeparator());
                continue;
            }

            Town townEntityToImport = this.modelMapper.map(townJsonDto, Town.class);
            this.townRepository.saveAndFlush(townEntityToImport);
            output.append(String.format(Constants.SUCCESSFUL_IMPORT_MESSAGE, "Town", townEntityToImport.getName())).append(System.lineSeparator());
        }

        return output.toString();
    }

    @Override
    public String exportRacingTowns() {
        StringBuilder output = new StringBuilder();

        List<Town> towns = this.townRepository.racingTowns();

        for (Town town : towns) {
            output.append(String.format("Name: %s", town.getName())).append(System.lineSeparator());
            output.append(String.format("Racers: %s", town.getRacers().size())).append(System.lineSeparator());
            output.append(System.lineSeparator());
        }

        return output.toString();
    }
}
