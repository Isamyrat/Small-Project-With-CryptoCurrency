package com.example.crypto.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.springframework.lang.NonNull;
import javax.persistence.*;

@Entity
@Table(name = "user_coin")
public class UserCoin {
    @Id
    @NonNull
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name = "price_usd")
    private Double price;

    @OneToOne
    @JoinColumn(name = "user_id")
    @JsonIgnore
    private User userCoin;

    @ManyToOne
    @JoinColumn(name = "coin_id")
    @JsonIgnore
    private Coin coinUser;

    public UserCoin() {
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public Coin getCoinUser() {
        return coinUser;
    }

    public void setCoinUser(Coin coinUser) {
        this.coinUser = coinUser;
    }

    public User getUserCoin() {
        return userCoin;
    }

    public void setUserCoin(User userCoin) {
        this.userCoin = userCoin;
    }
}
