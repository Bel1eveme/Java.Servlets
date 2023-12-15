package gamelibrary.web.servlets.game;

import gamelibrary.configuration.FetchingConfiguration;
import gamelibrary.configuration.SessionAttributeNames;
import gamelibrary.model.attributesholder.implementation.HttpSessionAttributesHolder;
import gamelibrary.model.entity.review.ReviewDto;
import gamelibrary.model.entity.game.Game;
import gamelibrary.service.comment.interfaces.ReviewService;
import gamelibrary.service.factory.service.ServiceFactory;
import gamelibrary.service.game.interfaces.GameService;
import gamelibrary.service.user.interfaces.UserLoginService;
import gamelibrary.service.utility.RequestUtility;
import jakarta.servlet.ServletConfig;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

public class GameServlet extends HttpServlet {

    private GameService gameService;
    private ReviewService reviewSevice;
    private UserLoginService userLoginService;

    @Override
    public void init(ServletConfig config) throws ServletException {
        super.init(config);
        gameService = ServiceFactory.getGameService();
        reviewSevice = ServiceFactory.getReviewService();
        userLoginService = ServiceFactory.getUserLoginService();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Optional<Long> optionalId = RequestUtility.readResourceId(req.getPathInfo().substring(1),resp);
        if(optionalId.isPresent()) {
            Long id = optionalId.get();
            Optional<Game> movie = gameService.findById(id);
            String pageStr = req.getParameter("page");
            if (movie.isPresent()) {
                req.setAttribute("game", movie.get());
                int page = pageStr == null ? 1 : Integer.parseInt(pageStr);
                int offset = (page - 1) * FetchingConfiguration.ENTITIES_SELECT_COUNT;
                String username = userLoginService.receiveLoggedUser(new HttpSessionAttributesHolder(req.getSession())).getUsername();
                List<ReviewDto> reviews = reviewSevice.findCommentsForMovie(id, FetchingConfiguration.ENTITIES_SELECT_COUNT, offset).stream().map(x -> new ReviewDto(x, x.getPublisherUsername().equals(username))).toList();
                req.setAttribute("reviews", reviews);
                req.setAttribute("page", page);
                req.setAttribute("isAdmin", req.getSession().getAttribute(SessionAttributeNames.IS_USER_ADMIN_ATTRIBUTE_NAME));
                req.getRequestDispatcher("/WEB-INF/pages/game/game.jsp").forward(req, resp);
            } else {
                resp.sendError(HttpServletResponse.SC_NOT_FOUND);
            }
        }
    }
}
