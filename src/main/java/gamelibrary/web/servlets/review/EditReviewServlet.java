package gamelibrary.web.servlets.review;

import gamelibrary.model.entity.review.Review;
import gamelibrary.service.comment.interfaces.ReviewService;
import gamelibrary.service.factory.service.ServiceFactory;
import jakarta.servlet.ServletConfig;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.Optional;

public class EditReviewServlet extends HttpServlet {

    private ReviewService reviewService;

    @Override
    public void init(ServletConfig config) throws ServletException {
        super.init(config);
        reviewService = ServiceFactory.getReviewService();
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Long id = Long.parseLong(req.getPathInfo().substring(1));
        String message = req.getParameter("message");
        Optional<Review> commentOptional = reviewService.findById(id);
        if(commentOptional.isPresent()){
            commentOptional.get().setMessage(message);
            reviewService.update(commentOptional.get());
        }
        resp.sendRedirect(req.getHeader("Referer"));
    }
}
