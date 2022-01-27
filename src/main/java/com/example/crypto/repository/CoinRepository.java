package com.example.crypto.repository;

import com.example.crypto.model.Coin;
import org.springframework.data.repository.CrudRepository;

public interface CoinRepository extends CrudRepository<Coin, Long> {
    Coin findBySymbol(String symbol);
}
