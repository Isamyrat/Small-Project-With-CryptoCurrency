package com.example.crypto.service;

import com.example.crypto.exception.ResourceNotFoundException;
import com.example.crypto.model.Coin;
import com.example.crypto.model.UserCoin;
import com.example.crypto.repository.UserCoinRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class UserCoinService {

    @Autowired
    private UserCoinRepository userCoinRepository;

    public List<UserCoin> getAllUserCoins(){
        return userCoinRepository.findAll();
    }

    public List<UserCoin> getUserCoinByCoin(Coin coin){
        return  userCoinRepository.findByCoinUser(coin);
    }
}
