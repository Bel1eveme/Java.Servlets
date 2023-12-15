package gamelibrary.service.comment.interfaces;

import gamelibrary.model.attributesholder.interfaces.AttributesHolder;
import gamelibrary.model.entity.review.Review;

import java.util.List;
import java.util.Optional;

public interface ReviewService {

    Optional<Review> findById(Long id);

    void deleteById(Long id);

    List<Review> findAllCommentsForMovie(Long movieId);

    List<Review> findCommentsForMovie(Long movieId, int limit, int offset);

    List<Review> findAll(int limit, int offset);

    void saveCurrentUserComment(Review review, AttributesHolder attributesHolder);

    void update(Review review);
}
