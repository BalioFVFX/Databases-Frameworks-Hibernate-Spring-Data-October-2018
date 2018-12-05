package mostwanted.service.impl;

import mostwanted.common.Constants;
import mostwanted.domain.entities.Car;
import mostwanted.domain.entities.RaceEntry;
import mostwanted.domain.entities.Racer;
import mostwanted.domain.entities.dto.importxml.RaceEntryXmlDto;
import mostwanted.domain.entities.dto.importxml.RaceEntryXmlListDto;
import mostwanted.repository.CarRepository;
import mostwanted.repository.RaceEntryRepository;
import mostwanted.repository.RacerRepository;
import mostwanted.service.RaceEntryService;
import mostwanted.util.FileUtil;
import mostwanted.util.XmlParser;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.xml.bind.JAXBException;
import java.io.IOException;

@Service
public class RaceEntryServiceImpl implements RaceEntryService {

    private final static String RACE_ENTRY_XML_FILE_PATH = System.getProperty("user.dir") + ("\\src\\main\\resources\\files\\race-entries.xml");

    private final RaceEntryRepository raceEntryRepository;
    private final CarRepository carRepository;
    private final RacerRepository racerRepository;
    private final FileUtil fileUtil;
    private final XmlParser xmlParser;
    private final ModelMapper modelmapper;

    @Autowired
    public RaceEntryServiceImpl(RaceEntryRepository raceEntryRepository, CarRepository carRepository, RacerRepository racerRepository, FileUtil fileUtil, XmlParser xmlParser, ModelMapper modelmapper) {
        this.raceEntryRepository = raceEntryRepository;
        this.carRepository = carRepository;
        this.racerRepository = racerRepository;
        this.fileUtil = fileUtil;
        this.xmlParser = xmlParser;
        this.modelmapper = modelmapper;
    }

    @Override
    public Boolean raceEntriesAreImported() {
        return this.raceEntryRepository.count() != 0;
    }

    @Override
    public String readRaceEntriesXmlFile() throws IOException {
        return this.fileUtil.readFile(RACE_ENTRY_XML_FILE_PATH);
    }

    @Override
    public String importRaceEntries() throws JAXBException {
        StringBuilder output = new StringBuilder();
        RaceEntryXmlListDto raceEntryXmlListDto = this.xmlParser.parseXml(RaceEntryXmlListDto.class, RACE_ENTRY_XML_FILE_PATH);
        int idCounter = 1;

        for (RaceEntryXmlDto raceEntryXmlDto : raceEntryXmlListDto.getRaceEntries()) {
            Car carEntity = this.carRepository.findById(raceEntryXmlDto.getCarId()) .orElse(null);

            if(carEntity == null){
                output.append(Constants.INCORRECT_DATA_MESSAGE).append(System.lineSeparator());
                continue;
            }

            Racer racerEntity = this.racerRepository.findByName(raceEntryXmlDto.getRacerFullName()).orElse(null);

            if(racerEntity == null){
                output.append(Constants.INCORRECT_DATA_MESSAGE).append(System.lineSeparator());
                continue;
            }

            RaceEntry raceEntryEntityToImport = this.modelmapper.map(raceEntryXmlDto, RaceEntry.class);
            raceEntryEntityToImport.setCar(carEntity);
            raceEntryEntityToImport.setRacer(racerEntity);
            raceEntryEntityToImport.setRace(null);

            this.raceEntryRepository.saveAndFlush(raceEntryEntityToImport);
            output.append(String.format(Constants.SUCCESSFUL_IMPORT_MESSAGE, "RaceEntry", idCounter)).append(System.lineSeparator());
            idCounter++;
        }

        return output.toString();
    }
}
