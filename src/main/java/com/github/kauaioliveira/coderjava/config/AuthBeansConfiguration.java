package com.github.kauaioliveira.coderjava.config;

import com.github.kauaioliveira.coderjava.auth.InMemoryUserAccountRepository;
import com.github.kauaioliveira.coderjava.auth.LoginService;
import com.github.kauaioliveira.coderjava.auth.UserAccountRepository;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AuthBeansConfiguration {

    @Bean
    UserAccountRepository userAccountRepository() {
        return InMemoryUserAccountRepository.withDemoAdmin();
    }

    @Bean
    LoginService loginService(UserAccountRepository repository) {
        return new LoginService(repository);
    }
}
