package app.system;

import app.dtos.UserDto;
import app.dtos.UserRegisterDto;
import app.entity.ShoppingCart;
import app.entity.User;
import app.service.GameService;
import app.service.UserService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;

import javax.validation.ConstraintViolationException;

public class UserManager {
    private static boolean loggedIn = false;
    private static boolean isAdmin = false;
    private static ModelMapper modelMapper;

    UserService userService;
    GameService gameService;
    private UserDto userDto;


    @Autowired
    public UserManager(UserService userService, GameService gameService) {
        this.userService = userService;
        this.gameService = gameService;
    }

    static{
        modelMapper = new ModelMapper();
    }

    public void registerUser(UserRegisterDto userRegisterDto){

        if(!userRegisterDto.getPassword().equals(userRegisterDto.getRepeatPassword())){
            System.out.println("Passwords does not match!");
        }
        else{
            User entity = modelMapper.map(userRegisterDto, User.class);

            try{
                userService.save(entity);
            }
            catch(ConstraintViolationException exception){
                exception.getConstraintViolations().forEach(c -> {
                    System.out.println(c.getMessageTemplate());
                });
            }
        }
    }

    public void loginUser(String email, String password){
        User entity = userService.loginUser(email, password);
        try{
            if(entity == null){
                throw new Exception("Invalid username/password");
            }

            this.userDto = modelMapper.createTypeMap(User.class, UserDto.class).addMapping(User::getGames, UserDto::setGames)
                    .map(entity);
            System.out.println(userDto.getFullName() + " successfully logged in");
            this.userDto.setShoppingCart(new ShoppingCart(userDto, userService, gameService));
            loggedIn = true;
            isAdmin = userDto.isAdmin();
        }
        catch(Exception exception){
            System.out.println(exception.getMessage());
        }

    }

    public void logOut(){
        try{
            if(loggedIn == false){
                throw new Exception("No logged in user!");
            }
            System.out.println(String.format("User %s successfully logged out", userDto.getFullName()));
            loggedIn = false;
            isAdmin = false;
            userDto = null;
        }
        catch(Exception exception){
            System.out.println(exception.getMessage());
        }

    }

    public boolean isAdmin() {
        return isAdmin;
    }

    public boolean isLoggedIn(){
        return loggedIn;
    }

    public void printOwnedGames(){
        this.userDto.getGames().forEach(game -> System.out.println(game.getTitle()));
    }

    public ShoppingCart getShoppingCart(){
        return this.userDto.getShoppingCart();
    }

}
