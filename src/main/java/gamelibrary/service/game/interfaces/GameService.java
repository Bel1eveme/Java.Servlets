package gamelibrary.service.game.interfaces;

import gamelibrary.model.entity.game.Game;

import java.util.List;
import java.util.Optional;

public interface GameService {

    void save(Game game);

    void deleteById(Long id);

    void update(Game game);

    List<Game> findByQuery(String query, int limit, int offset);

    List<Game> findAll(int limit, int offset);

    Optional<Game> findById(Long id);
}
