package gamelibrary.model.entity.game;

import gamelibrary.model.entity.interfaces.Entity;
import lombok.Data;

import java.sql.Date;


@Data
public class Game implements Entity<Long> {

    private Long id;
    private String title;
    private String description;
    private String imageUrl;
    private Date releaseDate;
}
