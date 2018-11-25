package app.terminal;

import app.dto.*;
import app.service.CategoryService;
import app.service.ProductService;
import app.service.UserService;
import app.util.files.FileManager;
import app.util.gson.GsonManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.io.File;
import java.io.FileWriter;
import java.math.BigDecimal;
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
        FileManager fileManager = new FileManager();
        fileManager.setPath("C:\\Users\\Erik\\Desktop\\produtcsShop\\src\\main\\resources\\users.json");
        GsonManager gsonManager = new GsonManager();
        String usersJson = fileManager.getString();
        List<UserCreateDto> users = gsonManager.parseMany(usersJson, UserCreateDto.class);
        userService.saveAll(users);


        // Seed the products
        fileManager.setPath("C:\\Users\\Erik\\Desktop\\produtcsShop\\src\\main\\resources\\products.json");
        String productsJson = fileManager.getString();
        List<ProductCreateDto> products = gsonManager.parseMany(productsJson, ProductCreateDto.class);

        fileManager.setPath("C:\\Users\\Erik\\Desktop\\produtcsShop\\src\\main\\resources\\categories.json");
        String categoriesJson = fileManager.getString();

        List<CategoryDto> categories = gsonManager.parseMany(categoriesJson, CategoryDto.class);
        productService.seedJson(products, categories);


        // PRODUCTS IN RANGE

        List<ProductsInRangeDto> query1List = productService.productsInRange(new BigDecimal(500), new BigDecimal(1000));
        String queryList1Json = gsonManager.toJsonFile(query1List);

        File file = new File("products-in-range.json");

        // creates the file
        file.createNewFile();

        // creates a FileWriter Object
        FileWriter writer = new FileWriter(file);

        // Writes the content to the file
        writer.write(queryList1Json);
        writer.flush();


        // Successfully Sold products

       List<SuccessfullySoldProducts> successfullySoldProducts = this.userService.successfullySoldProduct();

       String query2Json = gsonManager.toJsonFile(successfullySoldProducts);


        File file2 = new File("users-sold-products.json");

        // creates the file
        file2.createNewFile();

        // creates a FileWriter Object
        FileWriter writer2 = new FileWriter(file2);

        // Writes the content to the file
        writer2.write(query2Json);
        writer2.flush();


        // QUERY 3

        File file3 = new File("categories-by-products.json");

        // creates the file
        file3.createNewFile();

        // creates a FileWriter Object
        FileWriter writer3 = new FileWriter(file3);

        // Writes the content to the file
        writer3.write(gsonManager.toJsonFile(this.categoryService.query3()));
        writer3.flush();

        Query4AbstractDto query4AbstractDto = userService.query4();

        // Query 4
        String query4Json = gsonManager.toJsonFile(this.userService.query4());

        File file4 = new File("users-and-products.json");

        // creates the file
        file4.createNewFile();

        // creates a FileWriter Object
        FileWriter writer4 = new FileWriter(file4);

        // Writes the content to the file
        writer4.write(query4Json);
        writer4.flush();
        System.out.println();
    }
}
