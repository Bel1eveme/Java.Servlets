package gamelibrary.model.entity.review;

import gamelibrary.model.entity.interfaces.Entity;
import lombok.Data;

@Data
public class Review implements Entity<Long> {

    private Long id;
    private String message;
    private String publisherUsername;
    private Long movieId;
}
