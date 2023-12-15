package gamelibrary.dao.interfaces;

import gamelibrary.model.entity.review.Review;

import java.util.List;

public interface ReviewDao extends GenericDao<Long, Review> {

    List<Review> findByMovieId(Long id);

    List<Review> findByMovieId(Long id, int limit, int offset);
}
