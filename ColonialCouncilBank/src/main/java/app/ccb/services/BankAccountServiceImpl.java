package app.ccb.services;

import app.ccb.domain.dtos.importxml.BankAccountDto;
import app.ccb.domain.dtos.importxml.BankAccountListDto;
import app.ccb.domain.entities.BankAccount;
import app.ccb.domain.entities.Client;
import app.ccb.repositories.BankAccountRepository;
import app.ccb.repositories.ClientRepository;
import app.ccb.util.FileUtil;
import app.ccb.util.ValidationUtil;
import com.google.gson.Gson;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;
import java.io.File;
import java.io.IOException;
import java.util.List;

@Service
public class BankAccountServiceImpl implements BankAccountService {

    private final static String BANKACCOUNTS_XML_FILE_PATH = "C:\\Users\\Erik\\Desktop\\ColonialCouncilBank-skeleton\\ColonialCouncilBank\\src\\main\\resources\\files\\xml\\bank-accounts.xml";

    private final BankAccountRepository bankAccountRepository;
    private final ClientRepository clientRepository;
    private final FileUtil fileUtil;
    private final ModelMapper modelMapper;
    private final ValidationUtil validationUtil;


    @Autowired
    public BankAccountServiceImpl(BankAccountRepository bankAccountRepository, ClientRepository clientRepository, FileUtil fileUtil, ModelMapper modelMapper, ValidationUtil validationUtil) {
        this.bankAccountRepository = bankAccountRepository;
        this.clientRepository = clientRepository;
        this.fileUtil = fileUtil;
        this.modelMapper = modelMapper;
        this.validationUtil = validationUtil;
    }

    @Override
    public Boolean bankAccountsAreImported() {
        return this.bankAccountRepository.count() != 0;
    }

    @Override
    public String readBankAccountsXmlFile() throws IOException {
        return this.fileUtil.readFile(BANKACCOUNTS_XML_FILE_PATH);
    }

    @Override
    public String importBankAccounts() throws JAXBException {
        StringBuilder output = new StringBuilder();
        File file = new File(BANKACCOUNTS_XML_FILE_PATH);
        JAXBContext jaxbContext = JAXBContext.newInstance(BankAccountListDto.class);
        Unmarshaller unmarshaller = jaxbContext.createUnmarshaller();
        BankAccountListDto bankAccountListDto = (BankAccountListDto)unmarshaller.unmarshal(file);

        for (BankAccountDto bankAccountDto: bankAccountListDto.getBankAccounts()) {
            Client clientEntity = this.clientRepository.findByFullName(bankAccountDto.getFullName()).orElse(null);

            if(validationUtil.isValid(bankAccountDto) == false){
                output.append("Error: Incorrect Data!");
                output.append(System.lineSeparator());
                continue;
            }

            if(clientEntity == null){
                output.append("Error: Incorrect Data!");
                output.append(System.lineSeparator());
                continue;
            }
            BankAccount bankAccountEntity = this.modelMapper.map(bankAccountDto, BankAccount.class);
            bankAccountEntity.setClient(clientEntity);
            this.bankAccountRepository.saveAndFlush(bankAccountEntity);
            output.append(String.format("Successfully imported Bank Account - %s", bankAccountEntity.getAccountNumber())).append(System.lineSeparator());
        }
        return output.toString();
    }
}
