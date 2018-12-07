package app.service.impl;

import app.entity.EmployeeCard;
import app.entity.dto.exportjson.FreeCardsJsonDto;
import app.entity.dto.importjson.EmployeeCardJsonDto;
import app.mappers.File.FileMapper;
import app.mappers.json.JsonMapper;
import app.mappers.modelmapper.CMapper;
import app.repository.EmployeeCardRepository;
import app.service.EmployeeCardService;
import app.validator.CValidator;
import com.google.gson.reflect.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.lang.reflect.Type;
import java.util.List;

@Service
public class EmployeeCardServiceImpl implements EmployeeCardService {
    private final static String EMPLOYEE_CARD_JSON_FILE_PATH = "C:\\Users\\Erik\\Desktop\\Hiberspring Inc\\src\\main\\resources\\json\\input\\employee_cards.json";
    private final static String FREE_CARDS_JSON_FILE_PATH = "C:\\Users\\Erik\\Desktop\\Hiberspring Inc\\src\\main\\resources\\json\\output\\free_cards.json";

    private final EmployeeCardRepository employeeCardRepository;

    @Autowired
    public EmployeeCardServiceImpl(EmployeeCardRepository employeeCardRepository) {
        this.employeeCardRepository = employeeCardRepository;
    }

    @Override
    public void saveAll() throws IOException {
        StringBuilder output = new StringBuilder();
        String jsonStr = FileMapper.readFile(EMPLOYEE_CARD_JSON_FILE_PATH);
        List<EmployeeCardJsonDto> employeeCardJsonDtos = JsonMapper.importData(jsonStr, EmployeeCardJsonDto.class);

        for (EmployeeCardJsonDto employeeCardJsonDto : employeeCardJsonDtos) {
            if(!CValidator.isValid(employeeCardJsonDto)){
                output.append("Error: Invalid data.").append(System.lineSeparator());
                continue;
            }

            // Unique cards check
            EmployeeCard empCardEntity = this.employeeCardRepository.findByNumber(employeeCardJsonDto.getNumber()).orElse(null);
            if(empCardEntity != null){
                output.append("Error: Invalid data.").append(System.lineSeparator());
                continue;
            }

            EmployeeCard employeeCardEntityToImport = CMapper.mapper().map(employeeCardJsonDto, EmployeeCard.class);
            this.employeeCardRepository.saveAndFlush(employeeCardEntityToImport);
            output.append(String.format("Successfully imported %s %s", "Employee Card", employeeCardEntityToImport.getNumber())).append(System.lineSeparator());
        }
        System.out.println(output.toString());
    }

    @Override
    public void saveFreeCards() throws IOException {
        List<EmployeeCard> freeCardsEntities = this.employeeCardRepository.freeCards();
        Type listType = new TypeToken<List<FreeCardsJsonDto>>() {}.getType();
        List<FreeCardsJsonDto> freeCardsJsonDtos = CMapper.mapper().map(freeCardsEntities, listType);
        String jsonStr = JsonMapper.toJsonFile(freeCardsJsonDtos);
        FileMapper.writeFile(FREE_CARDS_JSON_FILE_PATH, jsonStr);
    }
}
