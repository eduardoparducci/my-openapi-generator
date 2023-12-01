package com.example.myopenapigenerator.client;

import com.example.myopenapigenerator.model.UserRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.BodyInserters;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

@Slf4j
@Service
public class RandomAPIClient {

    private final WebClient webClient;

    public RandomAPIClient(WebClient.Builder webClient) {
        this.webClient = WebClient.create("http://localhost:10000");
    }





    public Boolean validateUser(UserRequest userRequest) {
        Mono<Boolean> response = webClient.method(HttpMethod.GET)
                .uri("/validate-user")
                .body(BodyInserters.fromValue(userRequest))
                .retrieve()
                .bodyToMono(Boolean.class);
        return response.block();
    }
}
