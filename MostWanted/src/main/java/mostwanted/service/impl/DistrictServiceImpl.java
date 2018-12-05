package mostwanted.service.impl;

import com.google.gson.Gson;
import mostwanted.common.Constants;
import mostwanted.domain.entities.District;
import mostwanted.domain.entities.Town;
import mostwanted.domain.entities.dto.importjson.DistrictJsonDto;
import mostwanted.repository.DistrictRepository;
import mostwanted.repository.TownRepository;
import mostwanted.service.DistrictService;
import mostwanted.util.FileUtil;
import mostwanted.util.ValidationUtil;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;

@Service
public class DistrictServiceImpl implements DistrictService {

    private final static String DISTRICT_JSON_FILE_PATH = System.getProperty("user.dir") + ("\\src\\main\\resources\\files\\districts.json");

    private final DistrictRepository districtRepository;
    private final TownRepository townRepository;
    private final Gson gson;
    private final FileUtil fileUtil;
    private final ValidationUtil validationUtil;
    private final ModelMapper modelMapper;

    @Autowired
    public DistrictServiceImpl(DistrictRepository districtRepository, TownRepository townRepository, Gson gson, FileUtil fileUtil, ValidationUtil validationUtil, ModelMapper modelMapper) {
        this.districtRepository = districtRepository;
        this.townRepository = townRepository;
        this.gson = gson;
        this.fileUtil = fileUtil;
        this.validationUtil = validationUtil;
        this.modelMapper = modelMapper;
    }

    @Override
    public Boolean districtsAreImported() {
        return this.districtRepository.count() != 0;
    }

    @Override
    public String readDistrictsJsonFile() throws IOException {
        return this.fileUtil.readFile(DISTRICT_JSON_FILE_PATH);
    }

    @Override
    public String importDistricts(String districtsFileContent) {
        StringBuilder output = new StringBuilder();

        DistrictJsonDto[] districtJsonDtos = this.gson.fromJson(districtsFileContent, DistrictJsonDto[].class);

        for (DistrictJsonDto districtJsonDto : districtJsonDtos) {

            // Check if the dto is valid
            if(this.validationUtil.isValid(districtJsonDto) == false){
                output.append(Constants.INCORRECT_DATA_MESSAGE).append(System.lineSeparator());
                continue;
            }

            // Check if the district is unique
            District districtEntity = this.districtRepository.findByName(districtJsonDto.getName()).orElse(null);

            if(districtEntity != null){
                output.append(Constants.DUPLICATE_DATA_MESSAGE).append(System.lineSeparator());
                continue;
            }

            // Check if the district's town is valid ( Database (towns table) contains the dto town's name)

            Town townEntity = this.townRepository.findByName(districtJsonDto.getTownName()).orElse(null);

            if(townEntity == null){
                output.append(Constants.INCORRECT_DATA_MESSAGE).append(System.lineSeparator());
                continue;
            }

            District districtEntityToImport = this.modelMapper.map(districtJsonDto, District.class);
            districtEntityToImport.setTown(townEntity);
            this.districtRepository.saveAndFlush(districtEntityToImport);
            output.append(String.format(Constants.SUCCESSFUL_IMPORT_MESSAGE, "District", districtEntityToImport.getName())).append(System.lineSeparator());


        }

        return output.toString();
    }
}
