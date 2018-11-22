package app.entity;

import app.dtos.UserDto;
import app.gamedtos.GameDetailsDto;
import app.service.GameService;
import app.service.UserService;
import org.modelmapper.ModelMapper;
import org.springframework.data.repository.query.Param;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

public class ShoppingCart {
    private List<GameDetailsDto> games;
    private UserDto userDto;
    private UserService userService;
    private GameService gameService;
    private ModelMapper modelMapper;

    public ShoppingCart(UserDto userDto, UserService userService, GameService gameService) {
        this.games = new ArrayList<>();
        this.userDto = userDto;
        this.userService = userService;
        this.gameService = gameService;
        this.modelMapper = new ModelMapper();
    }

    public ShoppingCart() {
        this.games = new ArrayList<>();
        this.modelMapper = new ModelMapper();
    }

    public List<GameDetailsDto> getGames() {
        return games;
    }

    public void setGames(List<GameDetailsDto> games) {
        this.games = games;
    }

    public BigDecimal getPrice(){
        BigDecimal price = new BigDecimal(0);

        for (int i = 0; i < this.games.size(); i++) {
            price = price.add(this.games.get(i).getPrice());
        }
        return price;
    }

    public void addGame(String title){
        try{
            Game entity = gameService.findByTitle(title);
            if(entity == null){
                throw new Exception("Game with title " + title + " not found");
            }
            GameDetailsDto gameDetailsDto = modelMapper.map(entity, GameDetailsDto.class);
            for (int i = 0; i < userDto.getGames().size(); i++) {
                if(userDto.getGames().get(i).getTitle().equals(entity.getTitle())){
                    throw new Exception("User already has that game");
                }
            }
            for (int i = 0; i < games.size(); i++) {
                if(games.get(i).getTitle().equals(entity.getTitle())){
                    throw new Exception("Game already in the cart!");
                }
            }

            System.out.println(entity.getTitle() + " added to cart");
            games.add(gameDetailsDto);
        }
        catch(Exception exception){
            System.out.println(exception.getMessage());
        }
    }

    public void removeGame(String title){
        Game entity = gameService.findByTitle(title);
        try {
            if (entity == null) {
                throw new Exception("Game with title " + title + " not found");
            }
            GameDetailsDto gameDetailsDto = modelMapper.map(entity, GameDetailsDto.class);
            int validCounter = 0;
            for (int i = 0; i < games.size(); i++) {
                if(games.get(i).getTitle().equals(entity.getTitle())){
                    System.out.println(gameDetailsDto.getTitle() + " removed from the cart");
                    this.games.remove(gameDetailsDto);
                    validCounter++;
                    break;
                }
            }
            if(validCounter == 0){
                throw new Exception("Game is not in the cart");
            }
        }catch(Exception exception){
            System.out.println(exception.getMessage());
        }
    }

    public void buyItem(){
        if(games.size() <= 0){
            System.out.println("Empty cart");
        }else{
            for (int i = 0; i < games.size(); i++) {
                 Game game = modelMapper.map(games.get(i), Game.class);
                userDto.getGames().add(game);
            }
            User user = modelMapper.map(userDto, User.class);
            userService.save(user);
            this.games.clear();
        }

    }
}
