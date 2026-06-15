package com.github.kauaioliveira.coderjava.auth;

import org.junit.jupiter.api.Test;

import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertInstanceOf;

class LoginServiceTest {

    @Test
    void autenticacaoComCredenciaisDemoAdmin1234RetornaSucesso() {
        var repo = InMemoryUserAccountRepository.withDemoAdmin();
        var service = new LoginService(repo);
        LoginResult r = service.authenticate("admin", "1234");
        assertInstanceOf(LoginResult.Success.class, r);
    }

    @Test
    void senhaIncorretaRetornaFalha() {
        var repo = InMemoryUserAccountRepository.withDemoAdmin();
        var service = new LoginService(repo);
        LoginResult r = service.authenticate("admin", "wrong");
        assertInstanceOf(LoginResult.Failure.class, r);
    }

    @Test
    void usuarioDesconhecidoRetornaFalha() {
        var repo = new InMemoryUserAccountRepository(Map.of("x", "0".repeat(64)));
        var service = new LoginService(repo);
        LoginResult r = service.authenticate("nobody", "1234");
        assertInstanceOf(LoginResult.Failure.class, r);
    }

    @Test
    void usuarioOuSenhaEmBrancoRetornaFalha() {
        var repo = InMemoryUserAccountRepository.withDemoAdmin();
        var service = new LoginService(repo);
        assertInstanceOf(LoginResult.Failure.class, service.authenticate("", "1234"));
        assertInstanceOf(LoginResult.Failure.class, service.authenticate("admin", ""));
    }
}
