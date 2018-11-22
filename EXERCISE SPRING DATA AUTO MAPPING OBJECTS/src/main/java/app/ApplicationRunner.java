package app;

import app.dtos.UserRegisterDto;
import app.entity.ShoppingCart;
import app.gamedtos.RegisterGameDto;
import app.service.GameService;
import app.service.UserService;
import app.system.GameManager;
import app.system.UserManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.Scanner;

@Component
public class ApplicationRunner implements CommandLineRunner {

    private UserService userService;
    private GameService gameService;

    @Autowired
    public ApplicationRunner(UserService userService, GameService gameService) {
        this.userService = userService;
        this.gameService = gameService;
    }


    // EXAMPLES:
    // VALID (ADD GAME):
    //AddGame|Overwatch|100.00|15.5|https://www.youtube.com/watch?v=FqnKB22pOC0|https://us.battle.net/forums/static/images/social-thumbs/overwatch.png|Overwatch is a team-based multiplayer online first-person shooter video game developed and published by Blizzard Entertainment.|24-05-2016
    // VALID (REGISTER USER)
    // RegisterUser|pesho@abv.bg|Pesho123|Pesho123|Pesho
    // VALID (LOGIN USER)
    // LoginUSER|pesho@abv.bg|Pesho123

    //EditGame|1|price=80.00|size=12.1
    //DeleteGame|1

    //AllGame
    //DetailGame|Overwatch
    //OwnedGame

    //AddItem|Overwatch
    //RemoveItem|Overwatch
    //AddItem|Overwatch
    //BuyItem
    @Override
    public void run(String... args) throws Exception {
        UserManager userManager = new UserManager(userService, gameService);
        GameManager gameManager = new GameManager(gameService);
        ShoppingCart shoppingCart = new ShoppingCart();
        Scanner scanner = new Scanner(System.in);
        while (true) {
            String[] input = scanner.nextLine().split("\\|");

            switch (input[0]) {
                case "RegisterUser":
                    UserRegisterDto userRegisterDto = new UserRegisterDto(input[1], input[2], input[3], input[4]);
                    userManager.registerUser(userRegisterDto);
                    break;
                case "LoginUser":
                    userManager.loginUser(input[1], input[2]);
                    break;
                case "LogoutUser":
                    userManager.logOut();
                    break;
                case "AddGame":
                    if (!userManager.isLoggedIn()) {
                        System.out.println("You must be logged in!");
                        break;
                    } else if (!userManager.isAdmin()) {
                        System.out.println("You must be an administrator!");
                        break;
                    }
                    gameManager.registerGame(new RegisterGameDto(input[1], new BigDecimal(input[2]), new BigDecimal(input[3]), input[4], input[5], input[6], input[7]));
                    break;
                case "EditGame":
                    if (!userManager.isLoggedIn()) {
                        System.out.println("You must be logged in!");
                        break;
                    } else if (!userManager.isAdmin()) {
                        System.out.println("You must be an administrator!");
                        break;
                    }
                    Long id = Long.parseLong(input[1]);
                    String[] params = Arrays.stream(input).skip(2).toArray(String[]::new);
                    gameManager.editGame(id, params);
                    break;
                case "DeleteGame":
                    if (!userManager.isLoggedIn()) {
                        System.out.println("You must be logged in!");
                        break;
                    } else if (!userManager.isAdmin()) {
                        System.out.println("You must be an administrator!");
                        break;
                    }
                    gameManager.deleteGame(Long.parseLong(input[1]));
                    break;
                case "AllGame":
                    gameManager.printAllGames();
                    break;
                case "DetailGame":
                    gameManager.printGameDetails(input[1]);
                    break;
                case "OwnedGame":
                    if (!userManager.isLoggedIn()) {
                        System.out.println("You must be logged in!");
                        break;
                    }
                    userManager.printOwnedGames();
                    break;
                case "AddItem":
                    if (!userManager.isLoggedIn()) {
                        System.out.println("You must be logged in!");
                        break;
                    }
                    userManager.getShoppingCart().addGame(input[1]);
                    break;
                case "RemoveItem":
                    if (!userManager.isLoggedIn()) {
                        System.out.println("You must be logged in!");
                        break;
                    }
                    userManager.getShoppingCart().removeGame(input[1]);
                    break;
                case "BuyItem":
                    if (!userManager.isLoggedIn()) {
                        System.out.println("You must be logged in!");
                        break;
                    }
                    userManager.getShoppingCart().buyItem();
                    break;
                default:
                    System.out.println("Invalid command!");
                    break;
            }
        }
    }
}
