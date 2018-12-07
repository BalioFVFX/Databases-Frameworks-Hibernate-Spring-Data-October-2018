package app.service.impl;

import app.entity.Branch;
import app.entity.Town;
import app.entity.dto.exportxml.TopBranchListXmlDto;
import app.entity.dto.exportxml.TopBranchXmlDto;
import app.entity.dto.importjson.BranchJsonDto;
import app.mappers.File.FileMapper;
import app.mappers.json.JsonMapper;
import app.mappers.modelmapper.CMapper;
import app.mappers.xml.XmlMapper;
import app.repository.BranchRepository;
import app.repository.TownRepository;
import app.service.BranchService;
import app.validator.CValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.xml.bind.JAXBException;
import java.io.File;
import java.io.IOException;
import java.util.List;

@Service
public class BranchServiceImpl implements BranchService {

    private final static String BRANCH_JSON_FILE_PATH = "C:\\Users\\Erik\\Desktop\\Hiberspring Inc\\src\\main\\resources\\json\\input\\branches.json";
    private final static String TOP_BRANCHES_XML_FILE_PATH = "C:\\Users\\Erik\\Desktop\\Hiberspring Inc\\src\\main\\resources\\xml\\output\\top-branches.xml";
    private final BranchRepository branchRepository;
    private final TownRepository townRepository;

    @Autowired
    public BranchServiceImpl(BranchRepository branchRepository, TownRepository townRepository) {
        this.branchRepository = branchRepository;
        this.townRepository = townRepository;
    }

    @Override
    public void saveAll() throws IOException {
        StringBuilder output = new StringBuilder();
        String jsonStr = FileMapper.readFile(BRANCH_JSON_FILE_PATH);
        List<BranchJsonDto> branchJsonDtoList = JsonMapper.importData(jsonStr, BranchJsonDto.class);

        for (BranchJsonDto branchJsonDto : branchJsonDtoList) {
            if(!CValidator.isValid(branchJsonDto)){
                output.append("Error: Invalid data.").append(System.lineSeparator());
                continue;
            }
            Town townEntity = this.townRepository.findByName(branchJsonDto.getTown()).orElse(null);

            if(townEntity == null){
                output.append("Error: Invalid data.").append(System.lineSeparator());
                continue;
            }

            Branch branchEntityToImport = CMapper.mapper().map(branchJsonDto, Branch.class);
            branchEntityToImport.setTown(townEntity);
            this.branchRepository.saveAndFlush(branchEntityToImport);
            output.append(String.format("Successfully imported %s %s", "Branch", branchEntityToImport.getName())).append(System.lineSeparator());
        }
        System.out.println(output.toString());
    }

    @Override
    public void saveAllTopBranches() throws JAXBException {
        List<Object[]> topBranches = this.branchRepository.topBranches();
        TopBranchListXmlDto topBranchListXmlDto = new TopBranchListXmlDto();
        for (Object[] topBranch : topBranches) {
            TopBranchXmlDto topBranchXmlDto = new TopBranchXmlDto(topBranch[0].toString(), topBranch[1].toString(), Long.parseLong(topBranch[2].toString()));
            topBranchListXmlDto.getTopBranches().add(topBranchXmlDto);
        }
        XmlMapper.marshall(new File(TOP_BRANCHES_XML_FILE_PATH), topBranchListXmlDto);
    }
}
