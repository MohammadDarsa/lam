package com.actionprime.llmclient.gemini.client;

import com.actionprime.llmclient.gemini.dto.GeminiGenerateRequest;
import com.actionprime.llmclient.gemini.dto.GeminiGenerateResponse;
import com.actionprime.llmclient.gemini.exception.GeminiRequestException;
import com.actionprime.llmclient.llm.LLMClient;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClient;

import java.util.Optional;

/**
 * Implementation of the LLMClient interface for interacting with the Gemini language model.
 */
@Service
@RequiredArgsConstructor
public class GeminiLLMClient implements LLMClient {

    private final RestClient geminiRestClient;

    /**
     * Sends the given prompt to the Gemini language model and returns the response.
     *
     * @param prompt the prompt to send to the language model
     * @return the response from the language model
     * @throws GeminiRequestException if there was an error sending the request to the language model
     */
    @Override
    public String chat(String prompt) {
        GeminiGenerateRequest request = new GeminiGenerateRequest(prompt);
        Optional<GeminiGenerateResponse> response = Optional.ofNullable(geminiRestClient.post().uri(":generateContent")
                .body(request)
                .retrieve()
                .toEntity(GeminiGenerateResponse.class).getBody());
        return response.orElseThrow(() -> new GeminiRequestException("Error while sending request to Gemini")).getResult();
    }
}
