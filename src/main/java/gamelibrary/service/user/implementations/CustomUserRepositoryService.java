package gamelibrary.service.user.implementations;

import gamelibrary.dao.interfaces.UserDao;
import gamelibrary.model.entity.user.User;
import gamelibrary.service.factory.dao.DaoFactory;
import gamelibrary.service.user.interfaces.UserRepositoryService;

import java.util.List;
import java.util.Optional;

public class CustomUserRepositoryService implements UserRepositoryService {

    private final UserDao userDao;

    private CustomUserRepositoryService() {
        userDao = DaoFactory.getUserDao();
    }

    private static class Holder {
        private static final CustomUserRepositoryService INSTANCE = new CustomUserRepositoryService();
    }

    public static CustomUserRepositoryService getInstance() {
        return Holder.INSTANCE;
    }

    @Override
    public Optional<User> findById(Long id) {
        return userDao.findById(id);
    }

    @Override
    public void save(User user) {
        userDao.save(user);
    }

    @Override
    public void deleteById(Long id) {
        userDao.deleteById(id);
    }

    @Override
    public void update(User user) {
        userDao.update(user);
    }

    @Override
    public List<User> findAll() {
        return userDao.findAll();
    }
}
