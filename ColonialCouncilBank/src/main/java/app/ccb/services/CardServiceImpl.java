package app.ccb.services;

import app.ccb.domain.dtos.importxml.CardDto;
import app.ccb.domain.dtos.importxml.CardListDto;
import app.ccb.domain.entities.BankAccount;
import app.ccb.domain.entities.Card;
import app.ccb.repositories.BankAccountRepository;
import app.ccb.repositories.CardRepository;
import app.ccb.util.FileUtil;
import app.ccb.util.ValidationUtil;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;
import java.io.File;
import java.io.IOException;

@Service
public class CardServiceImpl implements CardService {
    private final static String CARDS_XML_FILE_PATH = "C:\\Users\\Erik\\Desktop\\ColonialCouncilBank-skeleton\\ColonialCouncilBank\\src\\main\\resources\\files\\xml\\cards.xml";

    private final CardRepository cardRepository;
    private final BankAccountRepository bankAccountRepository;
    private final ValidationUtil validationUtil;
    private final ModelMapper modelMapper;
    private final FileUtil fileUtil;

    @Autowired
    public CardServiceImpl(CardRepository cardRepository, BankAccountRepository bankAccountRepository, ValidationUtil validationUtil, ModelMapper modelMapper, FileUtil fileUtil) {
        this.cardRepository = cardRepository;
        this.bankAccountRepository = bankAccountRepository;
        this.validationUtil = validationUtil;
        this.modelMapper = modelMapper;
        this.fileUtil = fileUtil;
    }

    @Override
    public Boolean cardsAreImported() {
        return this.cardRepository.count() != 0;
    }

    @Override
    public String readCardsXmlFile() throws IOException {
        return this.fileUtil.readFile(CARDS_XML_FILE_PATH);
    }

    @Override
    public String importCards() throws JAXBException {
        StringBuilder output = new StringBuilder();

        JAXBContext jaxbContext = JAXBContext.newInstance(CardListDto.class);
        Unmarshaller unmarshaller = jaxbContext.createUnmarshaller();
        CardListDto cardListDto = (CardListDto)unmarshaller.unmarshal(new File(CARDS_XML_FILE_PATH));

        for (CardDto cardDto: cardListDto.getCards()){
            if(!validationUtil.isValid(cardDto)){
                output.append("Error: Incorrect Data").append(System.lineSeparator());
                continue;
            }
            BankAccount bankAccountEntity = this.bankAccountRepository.findByAccountNumber(cardDto.getAccountNumber()).orElse(null);
            if(bankAccountEntity == null){
                output.append("Error: Incorrect Data").append(System.lineSeparator());
                continue;
            }

            Card cardEntity = this.modelMapper.map(cardDto, Card.class);
            cardEntity.setbankAccount(bankAccountEntity);
            this.cardRepository.saveAndFlush(cardEntity);
            output.append(String.format("Successfully imported Card - %s", cardEntity.getCardNumber())).append(System.lineSeparator());

        }


        return output.toString();
    }
}
