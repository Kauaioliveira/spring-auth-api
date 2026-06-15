package com.github.kauaioliveira.coderjava.auth;

/**
 * Resultado de uma tentativa de autenticação.
 */
public sealed interface LoginResult permits LoginResult.Success, LoginResult.Failure {

    record Success(String username) implements LoginResult {}

    record Failure(String message) implements LoginResult {}
}
