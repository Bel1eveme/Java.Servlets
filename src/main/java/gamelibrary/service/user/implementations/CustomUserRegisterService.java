package gamelibrary.service.user.implementations;

import gamelibrary.dao.interfaces.UserDao;
import gamelibrary.model.entity.user.Role;
import gamelibrary.model.entity.user.Status;
import gamelibrary.model.entity.user.User;
import gamelibrary.service.factory.dao.DaoFactory;
import gamelibrary.service.user.interfaces.UserRegisterService;

import java.util.Base64;

public class CustomUserRegisterService implements UserRegisterService {

    private UserDao userDao;

    private CustomUserRegisterService() {
        userDao = DaoFactory.getUserDao();
    }

    public static CustomUserRegisterService getInstance() {
        return Holder.INSTANCE;
    }

    private static class Holder {
        private static final CustomUserRegisterService INSTANCE = new CustomUserRegisterService();
    }

    @Override
    public boolean isUsernameUnique(String username) {
        return userDao.findByUsername(username).isEmpty();
    }

    @Override
    public void register(String username, String password) {
        User user = new User();
        user.setUsername(username);
        user.setPassword(new String(Base64.getEncoder().encode(password.getBytes())));
        user.setRole(Role.USER);
        user.setStatus(Status.UNBLOCKED);
        userDao.save(user);
    }
}
