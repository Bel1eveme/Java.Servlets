package gamelibrary.web.servlets.game;

import gamelibrary.model.entity.game.Game;
import gamelibrary.service.factory.service.ServiceFactory;
import gamelibrary.service.game.interfaces.GameService;
import gamelibrary.service.utility.RequestUtility;
import jakarta.servlet.ServletConfig;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.sql.Date;
import java.util.Optional;

public class GameEditServlet extends HttpServlet {

    private GameService movieService;

    @Override
    public void init(ServletConfig config) throws ServletException {
        super.init(config);
        movieService = ServiceFactory.getGameService();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Optional<Long> optionalId = RequestUtility.readResourceId(req.getPathInfo().substring(1),resp);
        if(optionalId.isPresent()) {
            Optional<Game> movieOptional = movieService.findById(optionalId.get());
            if (movieOptional.isPresent()) {
                req.setAttribute("movie", movieOptional.get());
                req.getRequestDispatcher("/WEB-INF/pages/movie/movieEdit.jsp").forward(req, resp);
            } else {
                resp.sendError(HttpServletResponse.SC_NOT_FOUND);
            }
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Optional<Long> optionalId = RequestUtility.readResourceId(req.getPathInfo().substring(1),resp);
        if(optionalId.isPresent()) {
            Long id = optionalId.get();
            Optional<Game> movieOptional = movieService.findById(id);
            if (movieOptional.isPresent()) {
                String title = req.getParameter("title");
                String description = req.getParameter("description");
                String imageUrl = req.getParameter("imageUrl");
                Date releaseDate = Date.valueOf(req.getParameter("releaseDate"));
                Game game = movieOptional.get();
                game.setDescription(description);
                game.setTitle(title);
                game.setImageUrl(imageUrl);
                game.setReleaseDate(releaseDate);
                movieService.update(game);
                resp.sendRedirect(String.format("%s/movie/edit/%d", req.getContextPath(), id));
            } else {
                resp.sendError(HttpServletResponse.SC_NOT_FOUND);
            }
        }
    }
}
