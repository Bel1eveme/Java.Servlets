package gamelibrary.web.servlets.review;

import gamelibrary.service.comment.interfaces.ReviewService;
import gamelibrary.service.factory.service.ServiceFactory;
import jakarta.servlet.ServletConfig;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

public class DeleteReviewServlet extends HttpServlet {

    private ReviewService reviewService;

    @Override
    public void init(ServletConfig config) throws ServletException {
        super.init(config);
        reviewService = ServiceFactory.getReviewService();
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Long id = Long.parseLong(req.getPathInfo().substring(1));
        reviewService.deleteById(id);
        resp.sendRedirect(req.getHeader("referer"));
    }
}
