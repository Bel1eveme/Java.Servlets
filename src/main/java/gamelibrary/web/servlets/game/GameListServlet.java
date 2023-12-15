package gamelibrary.web.servlets.game;

import gamelibrary.configuration.FetchingConfiguration;
import gamelibrary.configuration.SessionAttributeNames;
import gamelibrary.model.entity.game.Game;
import gamelibrary.service.factory.service.ServiceFactory;
import gamelibrary.service.game.interfaces.GameService;
import jakarta.servlet.ServletConfig;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.List;


public class GameListServlet extends HttpServlet {

    private GameService movieService;

    @Override
    public void init(ServletConfig config) throws ServletException {
        super.init(config);
        movieService = ServiceFactory.getGameService();
    }


    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String query = req.getParameter("query");
        String pageString = req.getParameter("page");
        int page = pageString==null?1:Integer.parseInt(pageString);
        List<Game> games = findGames(query,page);
        req.setAttribute("games", games);
        req.setAttribute("page",page);
        req.setAttribute("isAdmin",req.getSession().getAttribute(SessionAttributeNames.IS_USER_ADMIN_ATTRIBUTE_NAME));
        req.getRequestDispatcher("/WEB-INF/pages/game/gameList.jsp").forward(req,resp);
    }

    private List<Game> findGames(String query, int page){
        int offset = (page-1)* FetchingConfiguration.ENTITIES_SELECT_COUNT;
        if(query == null){
            return movieService.findAll(FetchingConfiguration.ENTITIES_SELECT_COUNT,offset);
        } else{
            return movieService.findByQuery(query,FetchingConfiguration.ENTITIES_SELECT_COUNT,offset);
        }
    }
}
