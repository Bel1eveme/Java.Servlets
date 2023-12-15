package gamelibrary.service.factory.service;

import gamelibrary.service.comment.implementations.ReviewServiceImpl;
import gamelibrary.service.comment.interfaces.ReviewService;
import gamelibrary.service.game.implementations.CustomGameService;
import gamelibrary.service.game.interfaces.GameService;
import gamelibrary.service.user.implementations.CustomUserLoginService;
import gamelibrary.service.user.implementations.CustomUserRegisterService;
import gamelibrary.service.user.implementations.CustomUserRepositoryService;
import gamelibrary.service.user.interfaces.UserLoginService;
import gamelibrary.service.user.interfaces.UserRegisterService;
import gamelibrary.service.user.interfaces.UserRepositoryService;

public class ServiceFactory {

    private ServiceFactory(){
    }

    public static UserLoginService getUserLoginService(){
        return CustomUserLoginService.getInstance();
    }

    public static UserRegisterService getUserRegisterService(){
        return CustomUserRegisterService.getInstance();
    }

    public static UserRepositoryService getUserRepositoryService(){
        return CustomUserRepositoryService.getInstance();
    }

    public static GameService getGameService(){
        return CustomGameService.getInstance();
    }

    public static ReviewService getReviewService(){
        return ReviewServiceImpl.getInstance();
    }
}
