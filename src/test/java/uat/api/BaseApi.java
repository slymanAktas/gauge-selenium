package uat.api;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;
import org.springframework.web.reactive.function.client.WebClient;

@Data
@SuperBuilder
@NoArgsConstructor
public class BaseApi {
    protected WebClient webClient;

    public BaseApi(WebClient webClient) {
        this.webClient = webClient;
    }
}
