package com.example.crypto.model;

import javax.persistence.*;

@Entity
@Table(name = "user")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name = "username")
    private String username;
    @OneToOne(mappedBy = "userCoin")

    private UserCoin userCoin;
    public User() {
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public UserCoin getUserCoin() {
        return userCoin;
    }

    public void setUserCoin(UserCoin userCoin) {
        this.userCoin = userCoin;
    }
}
