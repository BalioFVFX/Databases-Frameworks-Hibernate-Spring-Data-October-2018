package app.service;

import app.entity.Game;
import app.repository.GameRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class GameServiceImpl implements GameService{

    final private GameRepository gameRepository;

    @Autowired
    public GameServiceImpl(GameRepository gameRepository) {
        this.gameRepository = gameRepository;
    }

    @Override
    public void save(Game game) {
        this.gameRepository.save(game);
    }

    @Override
    public Game findById(Long id) {
        return this.gameRepository.findById(id).orElse(null);
    }

    @Override
    public void deleteGameWithId(Long id) {
        this.gameRepository.deleteById(id);
    }

    @Override
    public List<Game> findAllGames() {
        return this.gameRepository.findAll();
    }

    @Override
    public Game findByTitle(String title) {
        return this.gameRepository.findGameByTitle(title);
    }
}
