package gamelibrary.exception.entity;

public class EntityNotFoundException extends RuntimeException {

    public EntityNotFoundException(Long id){
        super(String.format("Entity with id %d was not found", id));
    }
}
