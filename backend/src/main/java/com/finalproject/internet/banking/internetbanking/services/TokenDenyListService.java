package com.finalproject.internet.banking.internetbanking.services;

import org.springframework.stereotype.Service;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

@Service
public class TokenDenyListService {
    private final Set<String> denyList = ConcurrentHashMap.newKeySet();

    public void addToDenyList(String token) {
        denyList.add(token);
    }

    public boolean isTokenDenied(String token) {
        return denyList.contains(token);
    }
}