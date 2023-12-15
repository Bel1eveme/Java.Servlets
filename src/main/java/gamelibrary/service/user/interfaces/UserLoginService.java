package gamelibrary.service.user.interfaces;

import gamelibrary.model.attributesholder.interfaces.AttributesHolder;
import gamelibrary.model.entity.user.User;
import gamelibrary.service.user.implementations.LoginResult;

public interface UserLoginService {

    boolean isUserLogged(AttributesHolder attributesHolder);

    User receiveLoggedUser(AttributesHolder attributesHolder);

    boolean doesUserExist(String username, String password);

    LoginResult login(String username, String password, AttributesHolder attributesHolder);

    void logout(AttributesHolder attributesHolder);
}
