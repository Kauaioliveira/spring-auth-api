package com.github.kauaioliveira.coderjava.web.dto;

import io.swagger.v3.oas.annotations.media.Schema;

import java.util.List;

@Schema(description = "Erro de validação ou de processamento.")
public record ErrorResponse(String message, List<FieldError> fieldErrors) {

    public record FieldError(String field, String message) {}
}
