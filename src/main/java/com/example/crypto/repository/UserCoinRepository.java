package com.example.crypto.repository;

import com.example.crypto.model.Coin;
import com.example.crypto.model.UserCoin;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface UserCoinRepository extends JpaRepository<UserCoin, Long> {
    List<UserCoin> findByCoinUser(Coin coin);
}
