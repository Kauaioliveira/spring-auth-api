package com.github.kauaioliveira.coderjava.web.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

@Schema(description = "Credenciais enviadas no corpo da requisição (apenas demonstração).")
public record LoginRequest(
        @NotBlank @Size(max = 64) @Schema(example = "admin") String username,
        @NotBlank @Size(max = 128) @Schema(example = "1234", format = "password") String password
) {}
