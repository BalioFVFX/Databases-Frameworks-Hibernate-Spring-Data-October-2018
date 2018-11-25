package app.service;

import app.dto.CategoryDto;
import app.dto.ProductCreateDto;
import app.dto.ProductsInRangeDto;
import app.dto.SuccessfullySoldProductDetailDto;
import app.entity.Category;
import app.entity.Product;
import app.entity.User;
import app.repository.ProductRepository;
import app.repository.UserRepository;
import app.util.mapper.CMapper;
import com.google.gson.reflect.TypeToken;
import org.modelmapper.PropertyMap;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.lang.reflect.Type;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

@Service
public class ProductServiceImpl implements ProductService {
    final private UserRepository userRepository;
    final private ProductRepository productRepository;

    @Autowired
    public ProductServiceImpl(UserRepository userRepository, ProductRepository productRepository) {
        this.userRepository = userRepository;
        this.productRepository = productRepository;
    }

    @Override
    public void seedJson(List<ProductCreateDto> products, List<CategoryDto> categories) {
        List<User> userEntities = userRepository.findAll();
        Type type = TypeToken.getParameterized(ArrayList.class, Product.class).getType();
        List<Product> productEntities = CMapper.mapper().map(products, type);

        Type categoryListType = TypeToken.getParameterized(ArrayList.class, Category.class).getType();
        List<Category> categoryEntities = CMapper.mapper().map(categories, categoryListType);


        // Seed with buyers
        Random rn = new Random();
        for (int i = 0; i < productEntities.size() - 50; i++) {
            Integer randomBuyerId = 0;
            Integer randomSellerId = 0;

            while(randomBuyerId == randomSellerId){
                randomBuyerId = rn.nextInt(userEntities.size() - 1) + 1;
                randomSellerId = rn.nextInt(userEntities.size() - 1) + 1;
            }

            productEntities.get(i).setBuyer(userEntities.get(randomBuyerId));
            productEntities.get(i).setSeller(userEntities.get(randomSellerId));
            productEntities.get(i).setCategories(getRandomCategories(categoryEntities));
        }

        // Seed the rest without a buyer

        for (int i = productEntities.size() - 50; i < productEntities.size(); i++) {
            Integer randomSellerId = rn.nextInt(userEntities.size() - 1) + 1;
            productEntities.get(i).setSeller(userEntities.get(randomSellerId));
            productEntities.get(i).setCategories(getRandomCategories(categoryEntities));
        }

        // Seed with categories



        this.productRepository.saveAll(productEntities);
    }

    private List<Category> getRandomCategories(List<Category> categories){
        int category1 = 0;
        int category2 = 0;
        int category3 = 0;
        List<Category> result = new ArrayList<>();
        Random rn = new Random();
        while(category1 == category2 || category1 == category3 || category3 == category2){
            category1 = rn.nextInt(categories.size() - 1) + 1;
            category2 = rn.nextInt(categories.size() - 1) + 1;
            category3 = rn.nextInt(categories.size() - 1) + 1;
        }
        result.add(categories.get(category1));
        result.add(categories.get(category2));
        result.add(categories.get(category3));

        return result;
    }

    @Override
    public List<ProductsInRangeDto> productsInRange(BigDecimal lowerPrice, BigDecimal greaterPrice) {
        List<Product> entities = productRepository.findAllByPriceGreaterThanEqualAndPriceLessThanEqualOrderByPriceAsc(lowerPrice, greaterPrice);

        List<ProductsInRangeDto> products = new ArrayList<>();

        for (Product entity : entities) {
            ProductsInRangeDto product = new ProductsInRangeDto();
            CMapper.mapper().map(entity, product);
            product.setSeller(entity.getSeller().getFirstName() + " " + entity.getSeller().getLastName());
            products.add(product);
        }
        return products;
    }

}
