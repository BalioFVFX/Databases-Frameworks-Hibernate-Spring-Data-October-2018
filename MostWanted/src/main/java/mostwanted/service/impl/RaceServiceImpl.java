package mostwanted.service.impl;

import mostwanted.common.Constants;
import mostwanted.domain.entities.District;
import mostwanted.domain.entities.Race;
import mostwanted.domain.entities.RaceEntry;
import mostwanted.domain.entities.dto.importxml.RaceEntrySimpleXmlDto;
import mostwanted.domain.entities.dto.importxml.RaceXmlDto;
import mostwanted.domain.entities.dto.importxml.RaceXmlListDto;
import mostwanted.repository.DistrictRepository;
import mostwanted.repository.RaceEntryRepository;
import mostwanted.repository.RaceRepository;
import mostwanted.service.RaceService;
import mostwanted.util.FileUtil;
import mostwanted.util.ValidationUtil;
import mostwanted.util.XmlParser;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.xml.bind.JAXBException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Service
public class RaceServiceImpl implements RaceService {

    private final static String RACE_XML_FILE_PATH = System.getProperty("user.dir") + ("\\src\\main\\resources\\files\\races.xml");;

    private final RaceRepository raceRepository;
    private final DistrictRepository districtRepository;
    private final RaceEntryRepository raceEntryRepository;
    private final FileUtil fileUtil;
    private final ValidationUtil validationUtil;
    private final XmlParser xmlParser;
    private final ModelMapper modelMapper;

    @Autowired
    public RaceServiceImpl(RaceRepository raceRepository, DistrictRepository districtRepository, RaceEntryRepository raceEntryRepository, FileUtil fileUtil, ValidationUtil validationUtil, XmlParser xmlParser, ModelMapper modelMapper) {
        this.raceRepository = raceRepository;
        this.districtRepository = districtRepository;
        this.raceEntryRepository = raceEntryRepository;
        this.fileUtil = fileUtil;
        this.validationUtil = validationUtil;
        this.xmlParser = xmlParser;
        this.modelMapper = modelMapper;
    }

    @Override
    public Boolean racesAreImported() {
        return this.raceRepository.count() != 0;
    }

    @Override
    public String readRacesXmlFile() throws IOException {
        return fileUtil.readFile(RACE_XML_FILE_PATH);
    }

    @Override
    public String importRaces() throws JAXBException {
        StringBuilder output = new StringBuilder();
        int idCounter = 1;

        RaceXmlListDto raceXmlListDto = this.xmlParser.parseXml(RaceXmlListDto.class, RACE_XML_FILE_PATH);

        for (RaceXmlDto race : raceXmlListDto.getRaces()) {
            if(this.validationUtil.isValid(race) == false){
                output.append(Constants.INCORRECT_DATA_MESSAGE).append(System.lineSeparator());
                continue;
            }

            District districtEntity = this.districtRepository.findByName(race.getDistrictName()).orElse(null);

            if(districtEntity == null){
                output.append(Constants.INCORRECT_DATA_MESSAGE).append(System.lineSeparator());
                continue;
            }

            List<RaceEntry> raceEntryEntitiesList = new ArrayList<>();

            for (RaceEntrySimpleXmlDto raceEntry : race.getEntries().getEntries()) {
                RaceEntry raceEntryEntity = this.raceEntryRepository.findById(raceEntry.getId()).orElse(null);
                if(raceEntryEntity == null){
                    output.append(Constants.INCORRECT_DATA_MESSAGE).append(System.lineSeparator());
                    continue;
                }
                raceEntryEntitiesList.add(raceEntryEntity);
            }

            Race raceEntityToImport = this.modelMapper.map(race, Race.class);
            raceEntityToImport.setDistrict(districtEntity);
            raceEntityToImport.setEntries(raceEntryEntitiesList);
            this.raceRepository.saveAndFlush(raceEntityToImport);

            for (RaceEntry entry : raceEntityToImport.getEntries()) {
                Race raceEntity = this.raceRepository.findById(raceEntityToImport.getId()).orElse(null);
                if(raceEntity == null){
                    output.append(Constants.INCORRECT_DATA_MESSAGE).append(System.lineSeparator());
                    continue;
                }
                entry.setRace(raceEntity);
                this.raceEntryRepository.saveAndFlush(entry);
            }

            output.append(String.format(Constants.SUCCESSFUL_IMPORT_MESSAGE, "Race", idCounter)).append(System.lineSeparator());
            idCounter++;
        }

        return output.toString();
    }
}
