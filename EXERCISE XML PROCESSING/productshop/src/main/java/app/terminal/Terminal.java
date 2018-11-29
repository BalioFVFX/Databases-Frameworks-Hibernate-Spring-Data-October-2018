package app.terminal;


import app.domain.dtos.output.query1.ProductInRangeListDto;
import app.domain.dtos.output.query2.Query2UserListDto;
import app.domain.dtos.output.query3.CategoriesByProductsCountListDto;
import app.domain.dtos.output.query4.UsersAndProductsUserListDto;
import app.domain.dtos.seeders.*;
import app.service.category.CategoryService;
import app.service.product.ProductService;
import app.service.user.UserService;
import app.xmlconfigs.XmlTool;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.io.File;
import java.util.List;


@Component
public class Terminal implements CommandLineRunner {

    private final UserService userService;
    private final ProductService productService;
    private final CategoryService categoryService;

    @Autowired
    public Terminal(UserService userService, ProductService productService, CategoryService categoryService) {
        this.userService = userService;
        this.productService = productService;
        this.categoryService = categoryService;
    }

    @Override
    public void run(String... args) throws Exception {

        // Seed the users
        File usersSeedFile = new File("C:\\Users\\Erik\\Desktop\\productshop\\src\\main\\resources\\users.xml");
        UserSeederListDto userSeederListDto = XmlTool.unmarshalMany(usersSeedFile, UserSeederListDto.class);
        List<UserSeederDto> usersToSeed = userSeederListDto.getUsers();
        this.userService.saveAll(usersToSeed);


        // Seed the Products
        File productsSeedFile = new File("C:\\Users\\Erik\\Desktop\\productshop\\src\\main\\resources\\products.xml");
        ProductSeederListDto productSeederListDto = XmlTool.unmarshalMany(productsSeedFile, ProductSeederListDto.class);
        List<ProductSeederDto> productsToSeed = productSeederListDto.getProducts();
        this.productService.saveAll(productsToSeed);

        // Seed the categories
        File categoriesSeedFile = new File("C:\\Users\\Erik\\Desktop\\productshop\\src\\main\\resources\\categories.xml");
        CategorySeederListDto categorySeederListDto = XmlTool.unmarshalMany(categoriesSeedFile, CategorySeederListDto.class);
        List<CategorySeederDto> cateogoriesToSeed = categorySeederListDto.getCategories();
        this.categoryService.saveAll(cateogoriesToSeed);


        // Query 1
        ProductInRangeListDto query1ObjectResult = this.productService.productsInRange();
        File fileProductsInRange = new File("C:\\Users\\Erik\\Desktop\\productshop\\src\\main\\resources\\output\\products-in-range.xml");
        XmlTool.marshall(fileProductsInRange, query1ObjectResult);

        // Query 2
        Query2UserListDto query2ObjectResult = this.userService.successfullySoldProducts();
        File successfullySoldProductsFile = new File("C:\\Users\\Erik\\Desktop\\productshop\\src\\main\\resources\\output\\user-sold-products.xml");
        XmlTool.marshall(successfullySoldProductsFile, query2ObjectResult);
        System.out.println();

         //Query 3
        CategoriesByProductsCountListDto query3ObjectResult = this.categoryService.categoriesByProductsCount();
        File cateogiresByProductsCountFile = new File("C:\\Users\\Erik\\Desktop\\productshop\\src\\main\\resources\\output\\categories-by-products.xml");
        XmlTool.marshall(cateogiresByProductsCountFile, query3ObjectResult);

        // Query 4
        UsersAndProductsUserListDto query4ObjectResult = this.userService.usersAndProducts();
        File usersAndProductsFile = new File("C:\\Users\\Erik\\Desktop\\productshop\\src\\main\\resources\\output\\users-and-products.xml");
        XmlTool.marshall(usersAndProductsFile, query4ObjectResult);


    }
}
