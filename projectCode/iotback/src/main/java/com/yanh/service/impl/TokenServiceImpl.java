package com.yanh.service.impl;


import com.yanh.mapper.TokenMapper;
import com.yanh.service.TokenService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TokenServiceImpl implements TokenService {

    @Autowired
    private TokenMapper tokenMapper;

    @Override
    public void addToken(String token) {
        tokenMapper.add(token);
    }

    @Override
    public void deleteToken(String token) {
        tokenMapper.delete(token);
    }
    
    @Override
    public boolean checkToken(String token) {
        return tokenMapper.check(token);
    }
}
