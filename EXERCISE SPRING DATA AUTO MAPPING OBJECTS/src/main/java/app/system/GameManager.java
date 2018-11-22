package app.system;

import app.config.MyModelMapper;
import app.entity.Game;
import app.gamedtos.AllGameDto;
import app.gamedtos.EditGameDto;
import app.gamedtos.GameDetailsDto;
import app.gamedtos.RegisterGameDto;
import app.service.GameService;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;

import java.lang.reflect.Type;
import java.math.BigDecimal;
import java.util.List;

public class GameManager {

    private GameService gameService;
    private static ModelMapper modelMapper;

    @Autowired
    public GameManager(GameService gameService) {
        this.gameService = gameService;

    }

    static{
        modelMapper = new ModelMapper();
    }


    public void registerGame(RegisterGameDto registerGameDto){
        if (registerGameDto.getTrailer().startsWith("https://www.youtube.com/")){
            registerGameDto.setTrailer(registerGameDto.getTrailer().substring(registerGameDto.getTrailer().lastIndexOf("/watch?v=") + 9));

            Game entity = MyModelMapper.mapper.map(registerGameDto, Game.class);

            try{
                gameService.save(entity);
            }catch(Exception exception){
                System.out.println(exception.getMessage());
            }
        }
        else{
            System.out.println("URL must starts with https://www.youtube.com/");
        }

    }

    public void editGame(Long id, String[] params){
        try{
            Game entity = this.gameService.findById(id);
            if(entity == null){
                throw new Exception("Game not found");
            }
            EditGameDto editGameDto = modelMapper.map(entity, EditGameDto.class);

            for (int i = 0; i < params.length; i++) {
                String param = params[i];

                if(param.startsWith("price=")){
                    editGameDto.setPrice(new BigDecimal(param.substring(param.lastIndexOf("=") + 1)));
                }
                else if(param.startsWith("size=")){
                    editGameDto.setSize(new BigDecimal(param.substring(param.lastIndexOf("=") + 1)));
                }
                else{
                    System.out.println("invalid parameter: " + param);
                }
            }
            entity = modelMapper.map(editGameDto, Game.class);
            this.gameService.save(entity);
        }
        catch(Exception exc){
            System.out.println(exc.getMessage());
        }

    }
    public void deleteGame(Long id){
        try{
            Game entity = this.gameService.findById(id);
            if(entity == null){
                throw new Exception("No game with id " + id);
            }
            this.gameService.deleteGameWithId(id);
            System.out.println("Deleted " + entity.getTitle());
        }catch(Exception exception){
            System.out.println(exception.getMessage());
        }
    }

    public void printAllGames(){
        List<Game> entityGames = this.gameService.findAllGames();
        Type listType = new TypeToken<List<AllGameDto>>() {}.getType();
        List<AllGameDto> games = modelMapper.map(entityGames, listType);
        games.forEach(game -> System.out.println(game.toString()));
    }

    public void printGameDetails(String title){
        Game entity = this.gameService.findByTitle(title);
        try {
            if (entity == null) {
                throw new Exception("Game with title " + title + " not found!");
            }

            GameDetailsDto gameDetailsDto = modelMapper.map(entity, GameDetailsDto.class);
            System.out.println(gameDetailsDto.toString());
        }
        catch(Exception exception){
            System.out.println(exception.getMessage());
        }
    }
}
