package gamelibrary.service.game.implementations;

import gamelibrary.dao.interfaces.GameDao;
import gamelibrary.model.entity.game.Game;
import gamelibrary.service.factory.dao.DaoFactory;
import gamelibrary.service.game.interfaces.GameService;

import java.util.List;
import java.util.Optional;

public class CustomGameService implements GameService {

    private final GameDao movieDao;

    private CustomGameService() {
        movieDao = DaoFactory.getGameDao();
    }

    public static CustomGameService getInstance() {
        return Holder.INSTANCE;
    }

    private static class Holder {
        private static final CustomGameService INSTANCE = new CustomGameService();
    }

    @Override
    public void save(Game game) {
        movieDao.save(game);
    }

    @Override
    public void deleteById(Long id) {
        movieDao.deleteById(id);
    }

    @Override
    public void update(Game game) {
        movieDao.update(game);
    }

    @Override
    public List<Game> findByQuery(String query, int limit, int offset) {
        return movieDao.findByQuery(query,limit,offset);
    }

    @Override
    public List<Game> findAll(int limit, int offset) {
        return movieDao.findAll(limit,offset);
    }


    @Override
    public Optional<Game> findById(Long id) {
        return movieDao.findById(id);
    }
}
