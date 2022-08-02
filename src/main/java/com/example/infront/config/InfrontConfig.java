package com.example.infront.config;

import com.example.infront.service.InstrumentService;
import com.example.infront.service.InstrumentServiceInterface;
import com.example.infront.webclient.SsrWebClient;
import com.example.infront.webclient.SsrWebClientInterface;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.client.WebClient;

@Configuration
public class InfrontConfig {
    @Bean
    public WebClient webClient() {
        return WebClient.builder()
                .baseUrl("https://ssr.finanstilsynet.no/api/v2")
                .build();
    }

    @Bean
    public SsrWebClientInterface ssrWebClient() {
        return new SsrWebClient();
    }

    @Bean
    public InstrumentServiceInterface instrumentService() {
        return new InstrumentService();
    }
}
