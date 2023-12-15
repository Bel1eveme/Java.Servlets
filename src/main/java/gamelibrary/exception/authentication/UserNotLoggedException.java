package gamelibrary.exception.authentication;

public class UserNotLoggedException extends RuntimeException{

    public UserNotLoggedException(){
        super("User is not logged");
    }
}
