package gamelibrary.web.servlets.review;

import gamelibrary.model.attributesholder.implementation.HttpSessionAttributesHolder;
import gamelibrary.model.entity.review.Review;
import gamelibrary.service.comment.interfaces.ReviewService;
import gamelibrary.service.factory.service.ServiceFactory;
import jakarta.servlet.ServletConfig;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

public class ReviewServlet extends HttpServlet {

    private ReviewService reviewService;

    @Override
    public void init(ServletConfig config) throws ServletException {
        super.init(config);
        reviewService = ServiceFactory.getReviewService();
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Review review = new Review();
        review.setMessage(req.getParameter("message"));
        review.setMovieId(Long.parseLong(req.getParameter("gameId")));
        reviewService.saveCurrentUserComment(review,new HttpSessionAttributesHolder(req.getSession()));
        resp.sendRedirect(req.getHeader("referer"));
    }
}
