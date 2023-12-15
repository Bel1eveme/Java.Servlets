package gamelibrary.service.user.implementations;

import gamelibrary.configuration.SessionAttributeNames;
import gamelibrary.dao.interfaces.UserDao;
import gamelibrary.exception.authentication.UserNotLoggedException;
import gamelibrary.model.attributesholder.interfaces.AttributesHolder;
import gamelibrary.model.entity.user.Role;
import gamelibrary.model.entity.user.Status;
import gamelibrary.model.entity.user.User;
import gamelibrary.service.factory.dao.DaoFactory;
import gamelibrary.service.user.interfaces.UserLoginService;
import lombok.extern.java.Log;

import java.util.Base64;
import java.util.Optional;

@Log
public class CustomUserLoginService implements UserLoginService {

    private UserDao userDao;

    private CustomUserLoginService() {
        userDao = DaoFactory.getUserDao();
    }

    public static CustomUserLoginService getInstance() {
        return Holder.INSTANCE;
    }

    private static class Holder {
        private static final CustomUserLoginService INSTANCE = new CustomUserLoginService();
    }

    @Override
    public boolean isUserLogged(AttributesHolder attributesHolder) {
        return attributesHolder.containsAttribute(SessionAttributeNames.USER_ATTRIBUTE_NAME);
    }

    @Override
    public User receiveLoggedUser(AttributesHolder attributesHolder) throws UserNotLoggedException {
        User user = (User) attributesHolder.getAttribute(SessionAttributeNames.USER_ATTRIBUTE_NAME);
        if (user == null) {
            log.severe("User is not logged");
            throw new UserNotLoggedException();
        }
        return user;
    }

    @Override
    public boolean doesUserExist(String username, String password) {
        Optional<User> user = userDao.findByUsername(username);
        String encodedPassword =  new String(Base64.getEncoder().encode(password.getBytes()));
        return user.isPresent() && user.get().getPassword().equals(encodedPassword);
    }

    @Override
    public LoginResult login(String username, String password, AttributesHolder attributesHolder) {
        Optional<User> user = userDao.findByUsername(username);
        if (user.isEmpty() || !isPasswordCorrect(user.get(),password)) {
            return LoginResult.WRONG_USERNAME_OR_PASSWORD;
        } else if(user.get().getStatus().equals(Status.BLOCKED)){
            return LoginResult.USER_IS_BLOCKED;
        }
        attributesHolder.setAttribute(SessionAttributeNames.USER_ATTRIBUTE_NAME, user.get());
        attributesHolder.setAttribute(SessionAttributeNames.IS_USER_ADMIN_ATTRIBUTE_NAME,user.get().getRole().equals(Role.ADMIN));
        return LoginResult.SUCCESS;
    }
    private boolean isPasswordCorrect(User user,String password){
        return new String(Base64.getEncoder().encode(password.getBytes())).equals(user.getPassword());
    }

    @Override
    public void logout(AttributesHolder attributesHolder) {
        attributesHolder.deleteAttribute(SessionAttributeNames.USER_ATTRIBUTE_NAME);
    }
}
