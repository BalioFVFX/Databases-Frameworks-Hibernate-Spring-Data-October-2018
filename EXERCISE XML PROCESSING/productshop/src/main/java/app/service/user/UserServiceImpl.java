package app.service.user;

import app.domain.dtos.output.query2.Query2SoldProductsDto;
import app.domain.dtos.output.query2.Query2SoldProductsListDto;
import app.domain.dtos.output.query2.Query2UserDto;
import app.domain.dtos.output.query2.Query2UserListDto;
import app.domain.dtos.output.query4.UsersAndProductsUserListDto;
import app.domain.dtos.output.query4.UsersAndProductsProductDto;
import app.domain.dtos.output.query4.UsersAndProductsProductsListDto;
import app.domain.dtos.output.query4.UsersAndProductsUserDto;
import app.domain.dtos.seeders.UserSeederDto;
import app.domain.entities.Product;
import app.domain.entities.User;
import app.modelmapperconfigs.CMapper;
import app.repository.UserRepository;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.lang.reflect.Type;
import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    @Autowired
    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public void saveAll(List<UserSeederDto> users) {
        Type listType = new TypeToken<List<User>>() {}.getType();
        List<User> entities = CMapper.mapper().map(users, listType);

        userRepository.saveAll(entities);
    }

    @Override
    public Query2UserListDto successfullySoldProducts() {
        List<User> userEntities = this.userRepository.successfullySoldProductsQuery2();

        Query2UserListDto result = new Query2UserListDto();

        for (User userEntity: userEntities){
            Query2SoldProductsListDto productsListResult = new Query2SoldProductsListDto();
            Query2UserDto userDto = new Query2UserDto();

            userDto = CMapper.mapper().map(userEntity, Query2UserDto.class);
            for (Product productEntity: userEntity.getProducts()){
                if(productEntity.getBuyer() == null){
                    continue;
                }
                Query2SoldProductsDto productDto = new Query2SoldProductsDto();
                productDto = CMapper.mapper().map(productEntity, Query2SoldProductsDto.class);
                productsListResult.getProducts().add(productDto);
            }
            userDto.setSoldProducts(productsListResult);
            result.getUsers().add(userDto);
        }
        return result;
    }

    @Override
    public UsersAndProductsUserListDto usersAndProducts() {
        List<User> userEntities = this.userRepository.UserAndProductsQuery4();
        UsersAndProductsUserListDto result = new UsersAndProductsUserListDto();
        result.setUseresCount(userEntities.size());
        for (User userEntity: userEntities){
            UsersAndProductsProductsListDto productsListDto = new UsersAndProductsProductsListDto();
            for (Product productEntity: userEntity.getProducts()){
                if(productEntity.getBuyer() == null){
                    continue;
                }
                UsersAndProductsProductDto productDto = CMapper.mapper().map(productEntity, UsersAndProductsProductDto.class);
                productsListDto.getProducts().add(productDto);
            }
            UsersAndProductsUserDto userDto = CMapper.mapper().map(userEntity, UsersAndProductsUserDto.class);
            userDto.setProducts(productsListDto);
            result.getUsers().add(userDto);
        }
        result.getUsers().sort((u1, u2)  -> {
            int sortResult =  u2.getProducts().getProducts().size() - u1.getProducts().getProducts().size();
            if(sortResult == 0){
                return u1.getLastName().compareTo(u2.getLastName());
            }
            return sortResult;
        });

        return result;
    }
}
