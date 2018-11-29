package app.service.user;

import app.domain.dtos.output.query2.Query2UserListDto;

import app.domain.dtos.output.query4.UsersAndProductsUserListDto;
import app.domain.dtos.seeders.UserSeederDto;


import java.util.List;

public interface UserService {
    void saveAll(List<UserSeederDto> users);
    Query2UserListDto successfullySoldProducts();
    UsersAndProductsUserListDto usersAndProducts();
}
