package gamelibrary.dao.implementations;

import gamelibrary.configuration.TableNamesConfiguration;
import gamelibrary.dao.interfaces.GameDao;
import gamelibrary.model.entity.game.Game;
import gamelibrary.service.factory.sql.SqlObjectsFactory;
import lombok.extern.java.Log;

import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.function.Consumer;

@Log
public class GameDaoImpl extends BaseDaoImpl<Long, Game> implements GameDao {

    private GameDaoImpl() {
        super(SqlObjectsFactory.getEntitySqlExecutor(), TableNamesConfiguration.GAME_TABLE_NAME);
    }

    public static GameDaoImpl getInstance() {
        return Holder.INSTANCE;
    }

    @Override
    public List<Game> findByQuery(String query, int limit, int offset) {
        List<Game> result = new ArrayList<>();
        query = "%"+query+"%";
        query = String.format("'%s'",query);
        String sql = String.format("SELECT * FROM %s WHERE title LIKE %s OR description LIKE %s LIMIT %d OFFSET %d",getTable(),query,query,limit,offset);
        Consumer<ResultSet> consumer = (resultSet)->{
            Optional<Game> entity = createEntity(resultSet);
            while(entity.isPresent()){
                result.add(entity.get());
                entity = createEntity(resultSet);
            }
        };
        try {
            getEntitySqlExecutor().executeSql(sql, consumer);
        } catch (SQLException exception){
            log.warning(exception.getMessage());
        }
        return result;
    }

    private static class Holder {
        private static final GameDaoImpl INSTANCE = new GameDaoImpl();
    }

    @Override
    protected Optional<Game> createEntity(ResultSet resultSet) {
        try{
            if (resultSet.next()) {
                Game game = new Game();
                game.setReleaseDate(Date.valueOf(resultSet.getString("releaseDate")));
                game.setTitle(resultSet.getString("title"));
                game.setImageUrl(resultSet.getString("imageUrl"));
                game.setDescription(resultSet.getString("description"));
                game.setId(Long.valueOf(resultSet.getString("id")));
                return Optional.of(game);
            }
        } catch (SQLException e){
            log.warning(e.getMessage());
        }

        return Optional.empty();
    }
}
