package gamelibrary.dao.implementations;

import gamelibrary.configuration.TableNamesConfiguration;
import gamelibrary.dao.interfaces.ReviewDao;
import gamelibrary.model.entity.review.Review;
import gamelibrary.service.factory.sql.SqlObjectsFactory;
import lombok.extern.java.Log;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.function.Consumer;

@Log
public class ReviewDaoImpl extends BaseDaoImpl<Long, Review> implements ReviewDao {

    private ReviewDaoImpl() {
        super(SqlObjectsFactory.getEntitySqlExecutor(), TableNamesConfiguration.COMMENT_TABLE_NAME);
    }

    public static ReviewDaoImpl getInstance() {
        return Holder.INSTANCE;
    }

    private static class Holder {
        private static final ReviewDaoImpl INSTANCE = new ReviewDaoImpl();
    }

    @Override
    protected Optional<Review> createEntity(ResultSet resultSet) {
        try {
            if (resultSet.next()) {
                Review review = new Review();
                review.setMessage(resultSet.getString("message"));
                review.setMovieId(resultSet.getLong("movieId"));
                review.setId(resultSet.getLong("id"));
                review.setPublisherUsername(resultSet.getString("publisherUsername"));
                return Optional.of(review);
            }
        } catch (SQLException e){
            log.warning(e.getMessage());
        }
        return Optional.empty();
    }

    @Override
    public List<Review> findByMovieId(Long id) {
        List<Review> result = new ArrayList<>();
        Consumer<ResultSet> consumer = (resultSet)->{
            Optional<Review> comment = createEntity(resultSet);
            while(comment.isPresent()){
                result.add(comment.get());
                comment = createEntity(resultSet);
            }
        };
        try {
            getEntitySqlExecutor().select(getTable(), List.of("movieId"), List.of(id.toString()), consumer);
        } catch (SQLException sqlException){
            log.warning(sqlException.getMessage());
        }
        return result;
    }

    @Override
    public List<Review> findByMovieId(Long id, int limit, int offset) {
        List<Review> result = new ArrayList<>();
        Consumer<ResultSet> consumer = (resultSet)->{
            Optional<Review> comment = createEntity(resultSet);
            while(comment.isPresent()){
                result.add(comment.get());
                comment = createEntity(resultSet);
            }
        };
        try {
            getEntitySqlExecutor().select(getTable(), List.of("movieId"), List.of(id.toString()), limit,offset,consumer);
        } catch (SQLException sqlException){
            log.warning(sqlException.getMessage());
        }
        return result;
    }
}
