package com.example.crypto.controller;

import com.example.crypto.model.Coin;
import com.example.crypto.service.CoinService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.util.*;
@RestController
@RequestMapping("/v1/coin")
public class CoinController {
    @Autowired
    private CoinService coinService;

    @Autowired
    private RestTemplate restTemplate;

    @RequestMapping(value = "/getAll", method = RequestMethod.GET)
    public List getAllReservations() {
        return coinService.getAllCoins();
    }


    @RequestMapping(value = "/getById/{id}", method = RequestMethod.GET)
    public Coin getReservation(@PathVariable Long id) {
        return coinService.getCoin(id);
    }
    @RequestMapping(value = "/getBySymbol", method = RequestMethod.GET)
    public Coin getReservation(@RequestParam(required=false) String symbol) {
        return coinService.getCoin(symbol);
    }
}
