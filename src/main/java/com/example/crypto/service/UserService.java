package com.example.crypto.service;

import com.example.crypto.model.Coin;
import com.example.crypto.model.User;
import com.example.crypto.model.UserCoin;
import com.example.crypto.repository.CoinRepository;
import com.example.crypto.repository.UserCoinRepository;
import com.example.crypto.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import java.util.*;

@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private UserCoinRepository userCoinRepository;

    @Autowired
    private CoinRepository coinRepository;

    @Autowired
    private ExhangeCoins exhangeCoins;

    public void addUser(User user,String symbol) {
        UserCoin userCoin = new UserCoin();
        Coin coin = coinRepository.findBySymbol(symbol);

        userCoin.setUserCoin(user);
        userCoin.setCoinUser(coin);
        userCoin.setPrice(coin.getPrice());

        userRepository.save(user);
        userCoinRepository.save(userCoin);
    }
    public List getAllUsers(){
        List users  = new ArrayList<>();
        userRepository.findAll().forEach(users::add);
        return users;
    }

    @Scheduled(fixedDelay = 60000)
    void someJob()  {
        System.out.println("Now is " + new Date());
        exhangeCoins.getEthereumCoins();
        exhangeCoins.getCoinsBitcoin();
        exhangeCoins.getCoinsSolana();
    }
}
