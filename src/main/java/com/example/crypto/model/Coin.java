package com.example.crypto.model;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "coin")
public class Coin {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name = "symbol")
    private String symbol;

    @Column(name = "name")
    private String name;

    @Column(name = "price_usd")
    private Double price;

    @OneToMany(mappedBy = "coinUser")
    private List<UserCoin> userCoins;

    public Coin() {
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getSymbol() {
        return symbol;
    }

    public void setSymbol(String symbol) {
        this.symbol = symbol;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public List<UserCoin> getUserCoins() {
        return userCoins;
    }

    public void setUserCoins(List<UserCoin> userCoins) {
        this.userCoins = userCoins;
    }
}
