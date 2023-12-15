package gamelibrary.service.comment.implementations;

import gamelibrary.dao.interfaces.ReviewDao;
import gamelibrary.model.attributesholder.interfaces.AttributesHolder;
import gamelibrary.model.entity.review.Review;
import gamelibrary.service.comment.interfaces.ReviewService;
import gamelibrary.service.factory.dao.DaoFactory;
import gamelibrary.service.factory.service.ServiceFactory;
import gamelibrary.service.user.interfaces.UserLoginService;

import java.util.List;
import java.util.Optional;

public class ReviewServiceImpl implements ReviewService {

    private final ReviewDao commentDao;
    private final UserLoginService userLoginService;

    private ReviewServiceImpl() {
        commentDao = DaoFactory.getReviewDao();
        userLoginService = ServiceFactory.getUserLoginService();
    }

    public static ReviewServiceImpl getInstance() {
        return Holder.INSTANCE;
    }

    private static class Holder {
        private static final ReviewServiceImpl INSTANCE = new ReviewServiceImpl();
    }

    @Override
    public Optional<Review> findById(Long id) {
        return commentDao.findById(id);
    }

    @Override
    public void deleteById(Long id) {
        commentDao.deleteById(id);
    }

    @Override
    public List<Review> findAllCommentsForMovie(Long id) {
        return commentDao.findByMovieId(id);
    }

    @Override
    public List<Review> findCommentsForMovie(Long gameId, int limit, int offset) {
        return commentDao.findByMovieId(gameId,limit,offset);
    }

    @Override
    public List<Review> findAll(int limit, int offset) {
        return commentDao.findAll(limit,offset);
    }

    @Override
    public void saveCurrentUserComment(Review review, AttributesHolder attributesHolder) {
        review.setPublisherUsername(userLoginService.receiveLoggedUser(attributesHolder).getUsername());
        commentDao.save(review);
    }

    @Override
    public void update(Review review) {
        commentDao.update(review);
    }
}
