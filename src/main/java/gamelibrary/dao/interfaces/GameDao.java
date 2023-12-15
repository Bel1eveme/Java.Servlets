package gamelibrary.dao.interfaces;

import gamelibrary.model.entity.game.Game;

import java.util.List;

public interface GameDao extends GenericDao<Long, Game>{

    List<Game> findByQuery(String query, int limit, int offset);
}
