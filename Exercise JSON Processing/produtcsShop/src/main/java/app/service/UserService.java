package app.service;

import app.dto.Query4AbstractDto;
import app.dto.SuccessfullySoldProductDetailDto;
import app.dto.SuccessfullySoldProducts;
import app.dto.UserCreateDto;

import java.util.List;

public interface UserService {
    void saveAll(List<UserCreateDto> users);
    List<SuccessfullySoldProducts> successfullySoldProduct();
    Query4AbstractDto query4();
}
