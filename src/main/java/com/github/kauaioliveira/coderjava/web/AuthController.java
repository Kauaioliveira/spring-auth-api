package com.github.kauaioliveira.coderjava.web;

import com.github.kauaioliveira.coderjava.auth.LoginResult;
import com.github.kauaioliveira.coderjava.auth.LoginService;
import com.github.kauaioliveira.coderjava.web.dto.LoginRequest;
import com.github.kauaioliveira.coderjava.web.dto.LoginResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1")
@Validated
@Tag(name = "Autenticação", description = "Endpoints de login (demonstração)")
public class AuthController {

    private final LoginService loginService;

    public AuthController(LoginService loginService) {
        this.loginService = loginService;
    }

    @PostMapping("/auth/login")
    @Operation(summary = "Realizar login", description = "Valida usuário e senha contra o repositório em memória (demo).")
    public ResponseEntity<LoginResponse> login(@Valid @RequestBody LoginRequest request) {
        LoginResult result = loginService.authenticate(request.username(), request.password());
        if (result instanceof LoginResult.Success success) {
            return ResponseEntity.ok(new LoginResponse(true, "Login realizado com sucesso.", success.username()));
        }
        if (result instanceof LoginResult.Failure failure) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
                    .body(new LoginResponse(false, failure.message(), null));
        }
        return ResponseEntity.internalServerError().body(new LoginResponse(false, "Estado inesperado.", null));
    }
}
