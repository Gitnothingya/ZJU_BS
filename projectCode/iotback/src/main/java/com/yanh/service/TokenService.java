package com.yanh.service;

public interface TokenService {
    void addToken(String token);

    void deleteToken(String token);

    boolean checkToken(String token);
}
