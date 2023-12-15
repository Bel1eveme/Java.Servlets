package gamelibrary.web.servlets.game;

import gamelibrary.model.entity.game.Game;
import gamelibrary.service.factory.service.ServiceFactory;
import gamelibrary.service.game.interfaces.GameService;
import jakarta.servlet.ServletConfig;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.sql.Date;

public class GameCreationServlet extends HttpServlet {

    private GameService movieService;

    @Override
    public void init(ServletConfig config) throws ServletException {
        super.init(config);
        movieService = ServiceFactory.getGameService();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher("/WEB-INF/pages/game/gameCreate.jsp").forward(req,resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String title = req.getParameter("title");
        String description = req.getParameter("description");
        String imageUrl = req.getParameter("imageUrl");
        Date releaseDate = Date.valueOf(req.getParameter("releaseDate"));
        Game game = new Game();
        game.setDescription(description);
        game.setTitle(title);
        game.setImageUrl(imageUrl);
        game.setReleaseDate(releaseDate);
        movieService.save(game);
        resp.sendRedirect(String.format("%s/game/create",req.getContextPath()));
    }
}
