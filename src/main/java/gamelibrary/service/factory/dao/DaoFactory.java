package gamelibrary.service.factory.dao;

import gamelibrary.dao.implementations.ReviewDaoImpl;
import gamelibrary.dao.implementations.GameDaoImpl;
import gamelibrary.dao.implementations.UserDaoImpl;
import gamelibrary.dao.interfaces.ReviewDao;
import gamelibrary.dao.interfaces.GameDao;
import gamelibrary.dao.interfaces.UserDao;

public class DaoFactory {

    private DaoFactory(){}

    public static GameDao getGameDao(){
        return GameDaoImpl.getInstance();
    }

    public static ReviewDao getReviewDao(){
        return ReviewDaoImpl.getInstance();
    }

    public static UserDao getUserDao(){
        return UserDaoImpl.getInstance();
    }
}
