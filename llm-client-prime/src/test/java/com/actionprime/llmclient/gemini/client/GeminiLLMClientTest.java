package com.actionprime.llmclient.gemini.client;


import com.actionprime.llmclient.gemini.config.GeminiLLMConfig;
import com.actionprime.llmclient.gemini.config.GeminiLLMProperties;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.TestPropertySource;

import static org.assertj.core.api.Assertions.assertThat;


@SpringBootTest(classes = {GeminiLLMClient.class, GeminiLLMConfig.class, GeminiLLMProperties.class})
@TestPropertySource(value = {"classpath:application-test.properties", "classpath:secrets-test.properties"})
@ActiveProfiles("test")
public class GeminiLLMClientTest {

    @Autowired
    private GeminiLLMClient geminiLLMClient;

    @Test
    @ConditionalOnProperty(name = "enable-llm-tests", havingValue = "true")
    void send_gemini_message_and_expect_response() {
        String responseMessage = geminiLLMClient.chat("only respond with 'hello world' and only that");
        assertThat(responseMessage).isEqualTo("hello world");
    }
}
