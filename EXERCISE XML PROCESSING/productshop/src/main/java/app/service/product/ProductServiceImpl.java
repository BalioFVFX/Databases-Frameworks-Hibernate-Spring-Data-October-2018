package app.service.product;

import app.domain.dtos.output.query1.ProductInRangeListDto;
import app.domain.dtos.output.query1.ProductsInRangeDto;
import app.domain.dtos.seeders.ProductSeederDto;
import app.domain.entities.Product;
import app.domain.entities.User;
import app.modelmapperconfigs.CMapper;
import app.repository.ProductRepository;
import app.repository.UserRepository;
import org.modelmapper.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

@Service
public class ProductServiceImpl implements ProductService {
    private final ProductRepository productRepository;
    private final UserRepository userRepository;

    @Autowired
    public ProductServiceImpl(ProductRepository productRepository, UserRepository userRepository) {
        this.productRepository = productRepository;
        this.userRepository = userRepository;
    }

    @Override
    public void saveAll(List<ProductSeederDto> products) {
        List<User> userEntities = this.userRepository.findAll();
        Type listType = new TypeToken<List<Product>>() {}.getType();
        List<Product> productEntities = CMapper.mapper().map(products, listType);

        Random rnd = new Random();
        for (int i = 0; i < productEntities.size() - 50; i++) {
            Integer randomBuyerIndex = rnd.nextInt(userEntities.size() - 1);
            Integer randomSellerIndex = rnd.nextInt(userEntities.size() - 1);

            while(randomBuyerIndex.equals(randomSellerIndex)){
                randomBuyerIndex =rnd.nextInt(userEntities.size() - 1);
                randomSellerIndex = rnd.nextInt(userEntities.size() - 1);
            }

            User randomBuyer = userEntities.get(randomBuyerIndex);
            User randomSeller = userEntities.get(randomSellerIndex);

            productEntities.get(i).setBuyer(randomBuyer);
            productEntities.get(i).setSeller(randomSeller);
        }

        for (int i = productEntities.size() - 50; i < productEntities.size(); i++) {
            Integer randomSellerIndex = rnd.nextInt(userEntities.size() - 1);
            User randomSeller = userEntities.get(randomSellerIndex);
            productEntities.get(i).setSeller(randomSeller);
        }

        this.productRepository.saveAll(productEntities);
    }

    @Override
    public ProductInRangeListDto productsInRange() {
        List<Product> productEntities = this.productRepository.query1ProductsInRange();

        List<ProductsInRangeDto> productsInRangeDtos = new ArrayList<>();
        for (int i = 0; i < productEntities.size(); i++) {
            productsInRangeDtos.add(CMapper.mapper().map(productEntities.get(i), ProductsInRangeDto.class));
        }
        ProductInRangeListDto productInRangeListDto = new ProductInRangeListDto();
        productInRangeListDto.setProducts(productsInRangeDtos);

        return productInRangeListDto;
    }
}
