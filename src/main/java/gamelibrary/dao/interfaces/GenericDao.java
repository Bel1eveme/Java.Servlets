package gamelibrary.dao.interfaces;

import gamelibrary.model.entity.interfaces.Entity;

import java.util.List;
import java.util.Optional;

public interface GenericDao<I,E extends Entity<I>> {

    Optional<E> findById(I id);

    void update(E entity);

    void save(E entity);

    List<E> findAll(int limit, int offset);

    void deleteById(I id);
}
