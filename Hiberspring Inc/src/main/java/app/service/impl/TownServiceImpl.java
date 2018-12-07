package app.service.impl;

import app.entity.Town;
import app.entity.dto.exportxml.TownListXmlDto;
import app.entity.dto.exportxml.TownXmlDto;
import app.entity.dto.importjson.TownJsonDto;
import app.mappers.File.FileMapper;
import app.mappers.json.JsonMapper;
import app.mappers.modelmapper.CMapper;
import app.mappers.xml.XmlMapper;
import app.repository.TownRepository;
import app.service.TownService;
import app.validator.CValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.xml.bind.JAXBException;
import java.io.File;
import java.io.IOException;
import java.util.List;

@Service
public class TownServiceImpl implements TownService {
    private final static String TOWN_JSON_FILE_PATH = "C:\\Users\\Erik\\Desktop\\Hiberspring Inc\\src\\main\\resources\\json\\input\\towns.json";
    private final static String TOWN_XML_FILE_PATH = "C:\\Users\\Erik\\Desktop\\Hiberspring Inc\\src\\main\\resources\\xml\\output\\towns.xml";
    private final TownRepository townRepository;

    @Autowired
    public TownServiceImpl(TownRepository townRepository) {
        this.townRepository = townRepository;
    }

    @Override
    public void saveAll() throws IOException {
        StringBuilder output = new StringBuilder();
        String jsonStr = FileMapper.readFile(TOWN_JSON_FILE_PATH);
        List<TownJsonDto> townJsonDtos = JsonMapper.importData(jsonStr, TownJsonDto.class);

        for (TownJsonDto townJsonDto : townJsonDtos) {
            if(!CValidator.isValid(townJsonDto)){
                output.append("Error: Invalid data.").append(System.lineSeparator());
                continue;
            }
            Town townEntityToImport = CMapper.mapper().map(townJsonDto, Town.class);
            this.townRepository.saveAndFlush(townEntityToImport);
            output.append(String.format("Successfully imported %s %s", "Town", townEntityToImport.getName())).append(System.lineSeparator());
        }
        System.out.println(output.toString());
    }

    @Override
    public void saveTowns() throws JAXBException {
        List<Object[]> townInfo = this.townRepository.towns();
        TownListXmlDto townListXmlDto = new TownListXmlDto();
        for (Object[] objects : townInfo) {
            TownXmlDto townXmlDto = new TownXmlDto(objects[0].toString(), Integer.parseInt(objects[1].toString()), Long.parseLong(objects[2].toString()));
            townListXmlDto.getTowns().add(townXmlDto);
        }
        XmlMapper.marshall(new File(TOWN_XML_FILE_PATH), townListXmlDto);
    }
}
