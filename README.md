![Banner](https://raw.githubusercontent.com/Vishnu-Yadav0/Revshop-ai-chat-service/main/banner.png)

# 🤖 RevShop — AI Chat Service

A state-of-the-art shopping assistant built with **Spring AI** and **Ollama**, providing intelligent, real-time responses to help buyers find products and track orders.

[![Java](https://img.shields.io/badge/Java-21-orange?style=flat-square&logo=openjdk)](https://openjdk.org/)
[![Spring AI](https://img.shields.io/badge/Spring-AI-brightgreen?style=flat-square)](https://spring.io/projects/spring-ai)
[![Ollama](https://img.shields.io/badge/Ollama-Local%20LLM-blue?style=flat-square)](https://ollama.com/)
[![Reactive](https://img.shields.io/badge/Spring-WebFlux-green?style=flat-square)](https://spring.io/projects/spring-framework)

---

## AI Features

- **Shopping Assistant:** Smart answers for products, returns, and platform info.
- **Streaming Responses:** Powered by **WebFlux** for ultra-low-latency "typing" effect in the UI.
- **Local LLM:** Integrated with **Ollama** for privacy-focused, local-first AI processing.
- **System Guardrails:** Custom prompts ensure the assistant stays on-topic for commerce queries.

## Tech Specs

| Field | Tech |
|---|---|
| AI Framework | Spring AI |
| Model Backend | Ollama (Llama/Mistral) |
| Web Layer | Spring WebFlux (Reactive) |
| Distributed Tracing | Zipkin |
| Discovery | Eureka |

## Local Prerequisites

> Requires **Ollama** running with a compatible model (e.g., `llama3` or `mistral`).

```bash
ollama serve
ollama pull llama3
```

---

## RevShop Project Links

- 🌐 [Revshop-frontend](https://github.com/Vishnu-Yadav0/Revshop-frontend)
- 👤 [Identity Mgmt](https://github.com/Vishnu-Yadav0/Revshop-user-service)
- 🛍️ [Product Catalog](https://github.com/Vishnu-Yadav0/Revshop-product-catalog-service)

