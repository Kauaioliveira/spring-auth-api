package com.github.kauaioliveira.coderjava.auth;

/**
 * Abstrai onde as credenciais são validadas (memória, banco, LDAP, etc.).
 */
public interface UserAccountRepository {

    /**
     * @param username nome de usuário (já normalizado pelo chamador, se aplicável)
     * @param plainPassword senha em texto claro fornecida no login
     * @return true se usuário existe e a senha confere
     */
    boolean matches(String username, String plainPassword);
}
