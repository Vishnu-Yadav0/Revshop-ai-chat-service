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
        "You are RevShop AI, a helpful shopping assistant for RevShop e-commerce. " +
        "STRICT RULES YOU MUST FOLLOW:\n" +
        "1. ALWAYS respond in English only. Never use any other language.\n" +
        "2. ONLY answer questions related to shopping, products, orders, returns, shipping, and account management on RevShop.\n" +
        "3. If asked about anything unrelated to RevShop or shopping, respond with: 'I can only help with RevShop shopping-related questions.'\n" +
        "4. Keep responses SHORT and HELPFUL (2-4 sentences maximum).\n" +
        "5. STOP generating text immediately after your answer. Do NOT add examples, instructions, exercises, or any other content.\n" +
        "6. Do NOT generate training prompts, instructions, exercises, or any meta-content after your answer.";

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
