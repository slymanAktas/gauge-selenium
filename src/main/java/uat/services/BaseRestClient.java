package uat.services;

import org.springframework.web.reactive.function.client.WebClient;

public class BaseRestClient {
    protected final WebClient webClient;

    public BaseRestClient(WebClient webClient) {
        this.webClient = webClient;
    }
}
