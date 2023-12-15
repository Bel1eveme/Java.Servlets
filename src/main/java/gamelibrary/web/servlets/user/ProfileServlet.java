package gamelibrary.web.servlets.user;

import gamelibrary.model.attributesholder.implementation.HttpSessionAttributesHolder;
import gamelibrary.model.entity.user.User;
import gamelibrary.service.factory.service.ServiceFactory;
import gamelibrary.service.user.interfaces.UserLoginService;
import gamelibrary.service.user.interfaces.UserRepositoryService;
import gamelibrary.service.utility.RequestUtility;
import jakarta.servlet.ServletConfig;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

public class ProfileServlet extends HttpServlet {

    private UserLoginService userLoginService;
    private UserRepositoryService userRepositoryService;

    @Override
    public void init(ServletConfig config) throws ServletException {
        super.init(config);
        userLoginService = ServiceFactory.getUserLoginService();
        userRepositoryService = ServiceFactory.getUserRepositoryService();
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        User currentUser = userLoginService.receiveLoggedUser(new HttpSessionAttributesHolder(req.getSession()));
        req.setAttribute("user",currentUser);
        req.getRequestDispatcher("/WEB-INF/pages/user/profile.jsp").forward(req,resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        User currentUser = userLoginService.receiveLoggedUser(new HttpSessionAttributesHolder(req.getSession()));
        currentUser.setEmail(req.getParameter("email"));
        currentUser.setBio(req.getParameter("bio"));
        userRepositoryService.update(currentUser);
        req.setAttribute("user",currentUser);
        RequestUtility.sendRedirect(resp,req,"profile");
    }
}
