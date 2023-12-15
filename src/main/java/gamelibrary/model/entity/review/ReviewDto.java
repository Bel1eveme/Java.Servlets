package gamelibrary.model.entity.review;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ReviewDto {

    public ReviewDto(Review review, boolean isUserThePublisher){
        this.isUserThePublisher = isUserThePublisher;
        id = review.getId();
        message = review.getMessage();
        publisherUsername = review.getPublisherUsername();
        movieId = review.getMovieId();
    }

    private Long id;
    private String message;
    private String publisherUsername;
    private Long movieId;
    private boolean isUserThePublisher;
}
