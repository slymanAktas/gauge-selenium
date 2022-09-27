package uat.api.movies;

import com.thoughtworks.gauge.BeforeScenario;
import com.thoughtworks.gauge.Step;

import org.springframework.web.reactive.function.client.WebClient;
import uat.api.BaseApi;
import uat.dto.Movie;
import uat.services.movies.MoviesRestClient;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;


public class MoviesSteps extends BaseApi {
    private MoviesRestClient moviesRestClient;
    private List<Movie> movies;

    @BeforeScenario
    public void setUp() {
        String baseUrl = "http://localhost:8081";
        webClient = WebClient.create(baseUrl);
        moviesRestClient = new MoviesRestClient(webClient);
    }

    @Step("Get all movies")
    public void getAllMovies() {
        movies = moviesRestClient.retrieveAllMovies();
    }

    @Step("Check <10> movies are listed")
    public void checkMovieCount(int expectedCount) {
        assertEquals(movies.size(), expectedCount, "Eksik geldi.");
    }
}
