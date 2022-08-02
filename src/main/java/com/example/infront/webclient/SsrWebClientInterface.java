package com.example.infront.webclient;

import com.example.infront.model.Instrument;
import reactor.core.publisher.Flux;

public interface SsrWebClientInterface {
    Flux<Instrument> getAllInstruments();
}
