package com.actionprime.llmclient.gemini.config;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestClient;

/**
 * Configuration class for the Gemini LLM client.
 */
@Configuration
@RequiredArgsConstructor
public class GeminiLLMConfig {

    /**
     * Gemini LLM properties.
     */
    private final GeminiLLMProperties properties;

    /**
     * Creates a REST client for the Gemini LLM API.
     *
     * @return the REST client
     */
    @Bean
    public RestClient geminiRestClient() {
        return RestClient.builder()
                .baseUrl(properties.getUrl())
                .defaultHeader("x-goog-api-key", properties.getKey())
                .build();
    }
}
