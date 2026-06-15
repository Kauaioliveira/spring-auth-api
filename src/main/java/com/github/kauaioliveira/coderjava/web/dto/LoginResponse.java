package com.github.kauaioliveira.coderjava.web.dto;

import io.swagger.v3.oas.annotations.media.Schema;

@Schema(description = "Resultado da tentativa de login.")
public record LoginResponse(
        @Schema(description = "Indica se a autenticação foi bem-sucedida") boolean success,
        @Schema(description = "Mensagem para o cliente") String message,
        @Schema(description = "Usuário autenticado, se success = true", nullable = true) String username
) {}
