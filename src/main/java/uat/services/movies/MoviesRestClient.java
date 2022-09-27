package uat.services.movies;


import org.springframework.web.reactive.function.client.WebClient;
import uat.dto.Movie;
import uat.services.BaseRestClient;

import java.util.List;

public class MoviesRestClient extends BaseRestClient {

    public MoviesRestClient(WebClient webClient) {
        super(webClient);
    }

    public List<Movie> retrieveAllMovies() {
        return webClient.get().uri("/movieservice/v1/allMovies")
                .retrieve()
                .bodyToFlux(Movie.class)
                .collectList()
                .block();
    }
}
