package app.ccb.services;

import app.ccb.domain.dtos.importjson.BranchJsonDto;
import app.ccb.domain.entities.Branch;
import app.ccb.repositories.BranchRepository;
import app.ccb.util.FileUtil;
import app.ccb.util.ValidationUtil;
import com.google.gson.Gson;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;

@Service
public class BranchServiceImpl implements BranchService {

    private final static String BRANCHES_JSON_FILE_PATH = "C:\\Users\\Erik\\Desktop\\ColonialCouncilBank-skeleton\\ColonialCouncilBank\\src\\main\\resources\\files\\json\\branches.json";


    private final BranchRepository branchRepository;
    private final Gson gson;
    private final ModelMapper modelMapper;
    private final FileUtil fileUtil;
    private final ValidationUtil validationUtil;
    @Autowired
    public BranchServiceImpl(BranchRepository branchRepository, Gson gson, ModelMapper modelMapper, FileUtil fileUtil, ValidationUtil validationUtil) {
        this.branchRepository = branchRepository;
        this.gson = gson;
        this.modelMapper = modelMapper;
        this.fileUtil = fileUtil;
        this.validationUtil = validationUtil;
    }

    @Override
    public Boolean branchesAreImported() {
        return this.branchRepository.count() != 0;
    }

    @Override
    public String readBranchesJsonFile() throws IOException {
        String readedJsonFileStr = fileUtil.readFile(BRANCHES_JSON_FILE_PATH);
        return readedJsonFileStr;
    }

    @Override
    public String importBranches(String branchesJson) {
        BranchJsonDto[] branchJsonDtos = this.gson.fromJson(branchesJson, BranchJsonDto[].class);
        StringBuilder output = new StringBuilder();
        for (BranchJsonDto branchJsonDto : branchJsonDtos) {
            if(validationUtil.isValid(branchJsonDto) == false){
                output.append("Error: Incorrect Data!");
                output.append(System.lineSeparator());
                continue;
            }
            Branch branchEntity = this.modelMapper.map(branchJsonDto, Branch.class);
            this.branchRepository.saveAndFlush(branchEntity);
            output.append(String.format("Successfully imported Branch - %s", branchEntity.getName()));
            output.append(System.lineSeparator());
        }
        return output.toString();
    }
}
