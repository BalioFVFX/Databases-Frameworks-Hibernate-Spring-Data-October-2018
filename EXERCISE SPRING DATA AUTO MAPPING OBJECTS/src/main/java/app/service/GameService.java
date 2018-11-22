package app.service;

import app.entity.Game;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface GameService {
    void save(Game game);
    Game findById(Long id);
    void deleteGameWithId(Long id);
    List<Game> findAllGames();
    Game findByTitle(String title);
}
