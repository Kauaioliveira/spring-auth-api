package com.github.kauaioliveira.coderjava.auth;

import java.util.Objects;

public final class LoginService {

    private final UserAccountRepository repository;

    public LoginService(UserAccountRepository repository) {
        this.repository = Objects.requireNonNull(repository, "repository");
    }

    public LoginResult authenticate(String username, String password) {
        if (username == null || username.isBlank()) {
            return new LoginResult.Failure("Informe um usuário válido.");
        }
        if (password == null || password.isBlank()) {
            return new LoginResult.Failure("Informe uma senha.");
        }
        String normalizedUser = username.trim();
        if (repository.matches(normalizedUser, password)) {
            return new LoginResult.Success(normalizedUser);
        }
        return new LoginResult.Failure("Usuário ou senha incorretos.");
    }
}
