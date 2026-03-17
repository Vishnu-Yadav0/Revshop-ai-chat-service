package com.revshop.revshopaichatservice.service;

import reactor.core.publisher.Flux;
import org.springframework.ai.chat.client.ChatClient;
import org.springframework.ai.chat.model.ChatModel;
import org.springframework.stereotype.Service;
import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class AiChatService {

    private static final String SYSTEM_PROMPT =
        "You are RevShop AI, a helpful and friendly shopping assistant for RevShop, an e-commerce platform. " +
        "You help customers with product recommendations, order tracking, returns and refunds, " +
        "account management, shipping information, and general shopping advice. " +
        "Be concise, friendly, and accurate. If asked about something unrelated to shopping or RevShop, " +
        "politely redirect the conversation back to shopping assistance.";

    private final ChatClient chatClient;

    public AiChatService(ChatModel chatModel) {
        this.chatClient = ChatClient.builder(chatModel)
                .defaultSystem(SYSTEM_PROMPT)
                .build();
    }

    public String generateResponse(String message) {
        log.info("Generating response for message: {}", message);
        try {
            return chatClient.prompt()
                    .user(message)
                    .call()
                    .content();
        } catch (Exception e) {
            log.error("Error generating AI response: {}", e.getMessage());
            return "I'm sorry, I'm having trouble connecting to my brain right now. Please try again later.";
        }
    }

    public Flux<String> streamResponse(String message) {
        log.info("Streaming response for message: {}", message);
        try {
            return chatClient.prompt()
                    .user(message)
                    .stream()
                    .content()
                    .onErrorResume(e -> {
                        log.error("Error streaming AI response: {}", e.getMessage());
                        return Flux.just("Error connecting to AI service.");
                    });
        } catch (Exception e) {
            log.error("Error initializing stream: {}", e.getMessage());
            return Flux.just("Error connecting to AI service.");
        }
    }
}
