package com.example.myproject.ash;

import java.util.List;
import java.util.Map;

import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.reactive.function.client.WebClient;

import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/api")
public class ProductListController {

    private final WebClient webClient;

    public ProductListController(WebClient webClient) {
        this.webClient = webClient;
    }

    @GetMapping("/products")
    public Mono<ResponseEntity<Map<String,Object>>> getProducts() {

        String url = "https://commerce-2ac0.restdb.io/rest/products";

        return webClient.get()
                .uri(url)
                .header("x-apikey", "10bc272c4a1e03568378648911981cd254f36")
                .header("cache-control", "no-cache")
                .accept(org.springframework.http.MediaType.APPLICATION_JSON)                
                .retrieve()
                .bodyToMono(new ParameterizedTypeReference<List<Map<String, Object>>>() {})
                .map(products -> {
                    Map<String, Object> responseBody = Map.of(
                    "products", products,
                    "fetchedFrom", "external-api"
                    );
                return ResponseEntity.ok(responseBody);
                 })
            .onErrorResume(error -> {
                return Mono.just(ResponseEntity.status(HttpStatus.BAD_GATEWAY).body(
                    Map.of("error", error.getMessage())
                ));
            });
    }

}
