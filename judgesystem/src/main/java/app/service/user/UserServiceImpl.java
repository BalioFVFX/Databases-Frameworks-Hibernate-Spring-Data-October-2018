package app.service.user;

import app.domain.dto.importjson.user.UserJsonDto;
import app.domain.dto.importxml.userpart.UserPart;
import app.domain.dto.importxml.userpart.UserPartList;
import app.domain.entity.Contest;
import app.domain.entity.Problem;
import app.domain.entity.User;
import app.mapperconfig.CMapper;
import app.repository.ContestRepository;
import app.repository.ProblemRepository;
import app.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;
import java.io.File;
import java.util.List;

@Service
public class UserServiceImpl implements UserService{

    private final UserRepository userRepository;
    private final ContestRepository contestRepository;
    private final ProblemRepository problemRepository;

    @Autowired
    public UserServiceImpl(UserRepository userRepository, ContestRepository contestRepository, ProblemRepository problemRepository) {
        this.userRepository = userRepository;
        this.contestRepository = contestRepository;
        this.problemRepository = problemRepository;
    }


    @Override
    public void saveAll(List<UserJsonDto> userJsonDtoList) {
        for (UserJsonDto userJsonDto : userJsonDtoList) {
            User userEntity = CMapper.mapper().map(userJsonDto, User.class);
            this.userRepository.saveAndFlush(userEntity);
        }
    }

    @Override
    public void updateUserParticipations() throws JAXBException {
        JAXBContext jaxbContext = JAXBContext.newInstance(UserPartList.class);
        Unmarshaller unmarshaller = jaxbContext.createUnmarshaller();
        UserPartList userPartList = (UserPartList)unmarshaller.unmarshal(new File("C:\\Users\\Erik\\Desktop\\judgesystem\\src\\main\\resources\\input\\json\\user_participations.xml"));

        for (UserPart userPart: userPartList.getParticipations()) {
            User userEntity = this.userRepository.findById(userPart.getUser().getId()).orElse(null);
            Contest contestEntity = this.contestRepository.findById(userPart.getContest().getId()).orElse(null);


            if(userEntity == null){
                System.out.println("user not found");
                continue;
            }
            if(contestEntity == null){
                System.out.println("contest not found");
                continue;
            }


            userEntity.getContests().add(contestEntity);
            this.userRepository.saveAndFlush(userEntity);

            System.out.println();
        }


    }
}
