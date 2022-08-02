package com.example.infront.webclient;

import com.example.infront.model.Instrument;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Flux;

public class SsrWebClient implements SsrWebClientInterface{
    @Autowired
    WebClient webClient;

    public Flux<Instrument> getAllInstruments() {
        return this.webClient.get().uri("/instruments")
                .retrieve().bodyToFlux(Instrument.class);
    }
}
