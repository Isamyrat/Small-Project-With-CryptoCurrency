package com.example.crypto.service;

import com.example.crypto.model.Coin;
import com.example.crypto.repository.CoinRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.example.crypto.exception.ResourceNotFoundException;
import java.util.List;
import java.util.ArrayList;

@Service
public class CoinService {
    @Autowired
    private CoinRepository coinRepository;

    public List getAllCoins(){
        List coins  = new ArrayList<>();
        coinRepository.findAll().forEach(coins::add);
        return coins;
    }

    public Coin  getCoin(Long id){
        return coinRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Coin not exist with id:" + id));
    }
    public Coin  getCoin(String symbol){
        return coinRepository.findBySymbol(symbol);
    }

    public void updateCoin(Coin coin) {
        coinRepository.save(coin);
    }
}
