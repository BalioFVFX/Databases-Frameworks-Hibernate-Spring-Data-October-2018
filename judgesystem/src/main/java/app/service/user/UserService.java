package app.service.user;


import app.domain.dto.importjson.user.UserJsonDto;

import javax.xml.bind.JAXBException;
import java.util.List;

public interface UserService {
    void saveAll(List<UserJsonDto> userJsonDtoList);
    void updateUserParticipations() throws JAXBException;
}
