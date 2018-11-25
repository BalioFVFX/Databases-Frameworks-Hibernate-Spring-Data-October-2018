package app.service;

import app.dto.*;
import app.entity.Product;
import app.entity.User;
import app.repository.ProductRepository;
import app.repository.UserRepository;
import app.util.mapper.CMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserServiceImpl implements UserService{

    final private UserRepository userRepository;
    final private ProductRepository productRepository;

    @Autowired
    public UserServiceImpl(UserRepository userRepository, ProductRepository productRepository) {
        this.userRepository = userRepository;
        this.productRepository = productRepository;
    }

    @Override
    public void saveAll(List<UserCreateDto> users) {
        User[] entities = CMapper.mapper().map(users, User[].class);

        userRepository.saveAll(Arrays.stream(entities).collect(Collectors.toList()));
    }

    @Override
    public List<SuccessfullySoldProducts> successfullySoldProduct() {
        List<User> users = this.userRepository.successfullySoldProducts();
        List<SuccessfullySoldProducts> result = new ArrayList<>();

        java.lang.reflect.Type targetListType = new TypeToken<List<SuccessfullySoldProductDetailDto>>() {}.getType();
        for (User entity: users){
            List<Product> entityProducts = entity.getProducts();
            List<SuccessfullySoldProductDetailDto> productsResult = new ArrayList<>();
            productsResult = CMapper.mapper().map(entityProducts, targetListType);

            for (int i = 0; i < entityProducts.size(); i++) {
               if(entityProducts.get(i).getBuyer() == null){
                   continue;
               }
                productsResult.get(i).setBuyerFirstName(entityProducts.get(i).getBuyer().getFirstName());
                productsResult.get(i).setBuyerLastName(entityProducts.get(i).getBuyer().getLastName());
            }
            SuccessfullySoldProducts sProduct = CMapper.mapper().map(entity, SuccessfullySoldProducts.class);
            sProduct.setSoldProducts(productsResult);
            result.add(sProduct);
        }

            return result;
        }

    @Override
    public Query4AbstractDto query4() {
        List<User> users = this.userRepository.query4();
        List<Query4Dto> query4DtoList = new ArrayList<>();
        List<Query4AbstractDto> result = new ArrayList<>();
        for (User entity: users){
            List<Product> entityProducts = entity.getProducts().stream().filter(p -> p.getBuyer() != null).collect(Collectors.toList());

            List<Query4SoldProductsDto> query4SoldProductsDtos = new ArrayList<>();

            Type query4SoldProductsDtoType = new TypeToken<List<Query4SoldProductsDto>>() {}.getType();

            query4SoldProductsDtos = CMapper.mapper().map(entityProducts, query4SoldProductsDtoType);

            Query4SoldProductsCountDto query4SoldProductsCountDto = new Query4SoldProductsCountDto(query4SoldProductsDtos.size(), query4SoldProductsDtos);

            Query4Dto query4User = CMapper.mapper().map(entity, Query4Dto.class);
            query4User.setSoldProducts(query4SoldProductsCountDto);

            query4DtoList.add(query4User);
        }
        Query4AbstractDto query4AbstractDto = new Query4AbstractDto(query4DtoList.size(), query4DtoList);
        return query4AbstractDto;
    }
}
