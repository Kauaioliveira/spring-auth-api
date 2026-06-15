package com.github.kauaioliveira.coderjava.auth;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.HexFormat;
import java.util.Map;
import java.util.Objects;

/**
 * Repositório em memória com senhas armazenadas como SHA-256 em hexadecimal.
 * <p>
 * Uso apenas didático: em produção prefira algoritmos adaptativos (ex.: bcrypt, Argon2)
 * com sal por usuário.
 */
public final class InMemoryUserAccountRepository implements UserAccountRepository {

    private final Map<String, String> usernameToPasswordSha256Hex;

    public InMemoryUserAccountRepository(Map<String, String> usernameToPasswordSha256Hex) {
        this.usernameToPasswordSha256Hex = Map.copyOf(Objects.requireNonNull(usernameToPasswordSha256Hex));
    }

    /**
     * Usuário demo: {@code admin} / {@code 1234} (hash pré-calculado).
     */
    public static InMemoryUserAccountRepository withDemoAdmin() {
        return new InMemoryUserAccountRepository(
                Map.of("admin", "03ac674216f3e15c761ee1a5e255f067953623c8b388b4459e13f978d7c846f4"));
    }

    @Override
    public boolean matches(String username, String plainPassword) {
        if (username == null || plainPassword == null) {
            return false;
        }
        String expectedHex = usernameToPasswordSha256Hex.get(username);
        if (expectedHex == null) {
            return false;
        }
        try {
            byte[] expected = HexFormat.of().parseHex(expectedHex);
            byte[] actual = sha256(plainPassword);
            return MessageDigest.isEqual(expected, actual);
        } catch (IllegalArgumentException ignored) {
            return false;
        }
    }

    private static byte[] sha256(String input) {
        try {
            MessageDigest md = MessageDigest.getInstance("SHA-256");
            return md.digest(input.getBytes(StandardCharsets.UTF_8));
        } catch (NoSuchAlgorithmException e) {
            throw new IllegalStateException("SHA-256 não disponível na JVM", e);
        }
    }
}
