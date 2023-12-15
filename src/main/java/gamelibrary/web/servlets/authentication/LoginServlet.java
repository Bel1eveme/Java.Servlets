package gamelibrary.web.servlets.authentication;

import gamelibrary.model.attributesholder.implementation.HttpSessionAttributesHolder;
import gamelibrary.model.attributesholder.interfaces.AttributesHolder;
import gamelibrary.service.factory.service.ServiceFactory;
import gamelibrary.service.user.implementations.LoginResult;
import gamelibrary.service.user.interfaces.UserLoginService;
import jakarta.servlet.ServletConfig;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;


import java.io.IOException;

public class LoginServlet extends HttpServlet {

    private  UserLoginService userLoginService;

    @Override
    public void init(ServletConfig config) throws ServletException {
        super.init(config);
        userLoginService = ServiceFactory.getUserLoginService();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException,IOException {
        request.getRequestDispatcher("/WEB-INF/pages/authentication/login.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        AttributesHolder attributesHolder = new HttpSessionAttributesHolder(request.getSession());
        LoginResult loginResult = userLoginService.login(request.getParameter("username"),request.getParameter("password"),attributesHolder);
        if(loginResult.equals(LoginResult.SUCCESS)) {
            response.sendRedirect(String.format("%s/profile",request.getContextPath()));
        } else{
            String message = loginResult.equals(LoginResult.USER_IS_BLOCKED)?"User is blocked":"Wrong username or password";
            request.setAttribute("errorMessage", message);
            doGet(request, response);
        }
    }
}
