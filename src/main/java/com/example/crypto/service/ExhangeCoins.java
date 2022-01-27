package com.example.crypto.service;

import com.example.crypto.model.Coin;
import com.example.crypto.model.UserCoin;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.http.*;
import org.springframework.stereotype.Component;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;
import java.util.Arrays;
import java.util.*;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.stream.Collectors;

@Component
public class ExhangeCoins {

    private final RestTemplate restTemplate;
    private  static  final Logger log = Logger.getLogger(ExhangeCoins.class.getName());
    @Autowired
    private CoinService coinService;

    @Autowired
    private UserCoinService userCoinService;

    public ExhangeCoins(RestTemplateBuilder restTemplateBuilder) {
        this.restTemplate = restTemplateBuilder.build();
    }

    public void getEthereumCoins(){

        String url = "https://api.coinlore.net/api/ticker/?id=" + 80;
        HttpHeaders headers = new HttpHeaders();
        headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
        headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);

        MultiValueMap<String,  String> map = new LinkedMultiValueMap<>();

        HttpEntity<MultiValueMap<String,String>> entity = new HttpEntity<>(map, headers);
        ResponseEntity<String> responseEntity = restTemplate.exchange(url, HttpMethod.GET, entity, String.class);

        String string  = responseEntity.getBody().replaceAll("\"", "").replaceAll("[{}]", " ").replaceAll("\\[(.+)\\]", "$1");

        Map<String, String> map1 = Arrays.stream(string.split(","))
                .map(s -> s.split(":"))
                .collect(Collectors.toMap(s -> s[0], s -> s[1]));

        String price = "price_usd";
        Optional<String> priceCoin = map1.entrySet()
                     .stream()
                     .filter(e -> price.equals(e.getKey()))
                     .map(Map.Entry::getValue)
                     .findFirst();

        double ethereumPrice = Double.parseDouble(priceCoin.get());
        Coin coin = coinService.getCoin(80L);
        coin.setPrice(ethereumPrice);
        coinService.updateCoin(coin);
        List<UserCoin> userCoin = userCoinService.getUserCoinByCoin(coin);
        for (UserCoin u : userCoin) {
            double changedData = (((ethereumPrice - u.getPrice())/ethereumPrice)*100);
            if(changedData > 1||changedData < -1){
                log.log(Level.WARNING, "Код валюты: " + coin.getSymbol() + " Имя пользователя: " + u.getUserCoin().getUsername() + " Процент изменение с момента регистрации: " + changedData + "%");
            }
        }
    }

    public void getCoinsBitcoin(){
        String url = "https://api.coinlore.net/api/ticker/?id=" + 90;
        HttpHeaders headers = new HttpHeaders();
        headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
        headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);

        MultiValueMap<String,  String> map = new LinkedMultiValueMap<>();
        HttpEntity<MultiValueMap<String,String>> entity = new HttpEntity<>(map, headers);
        ResponseEntity<String> responseEntity = restTemplate.exchange(url, HttpMethod.GET, entity, String.class);

        String string  = responseEntity.getBody().replaceAll("\"", "").replaceAll("[{}]", " ").replaceAll("\\[(.+)\\]", "$1");

        Map<String, String> map1 = Arrays.stream(string.split(","))
                .map(s -> s.split(":"))
                .collect(Collectors.toMap(s -> s[0], s -> s[1]));
        String price = "price_usd";
        Optional<String> priceCoin = map1.entrySet()
                .stream()
                .filter(e -> price.equals(e.getKey()))
                .map(Map.Entry::getValue)
                .findFirst();
        double bitcoinPrice = Double.parseDouble(priceCoin.get());
        Coin coin = coinService.getCoin(90L);
        coin.setPrice(bitcoinPrice);
        coinService.updateCoin(coin);
        List<UserCoin> userCoin = userCoinService.getUserCoinByCoin(coin);
        for (UserCoin u : userCoin) {
            double changedData =(((bitcoinPrice - u.getPrice())/bitcoinPrice)*100);
            if(changedData > 1 ||  changedData < -1){
                log.log(Level.WARNING, "Код валюты: " + coin.getSymbol() + " Имя пользователя: " + u.getUserCoin().getUsername() + " Процент изменение с момента регистрации: " + changedData + "%");
            }
        }
    }

    public void getCoinsSolana(){
        String url = "https://api.coinlore.net/api/ticker/?id=" + 48543;
        HttpHeaders headers = new HttpHeaders();
        headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
        headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);

        MultiValueMap<String,  String> map = new LinkedMultiValueMap<>();
        HttpEntity<MultiValueMap<String,String>> entity = new HttpEntity<>(map, headers);
        ResponseEntity<String> responseEntity = restTemplate.exchange(url, HttpMethod.GET, entity, String.class);

        String string2 = responseEntity.getBody().replaceAll("\"", "").replaceAll("[{}]", " ").replaceAll("\\[(.+)\\]", "$1");
        Map<String, String> map1 = Arrays.stream(string2.split(","))
                .map(s -> s.split(":"))
                .collect(Collectors.toMap(s -> s[0], s -> s[1]));
        String price = "price_usd";

        Optional<String> priceCoin = map1.entrySet()
                .stream()
                .filter(e -> price.equals(e.getKey()))
                .map(Map.Entry::getValue)
                .findFirst();

        double Solana = Double.parseDouble(priceCoin.get());
        Coin coin = coinService.getCoin(48543L);
        coin.setPrice(Solana);
        coinService.updateCoin(coin);
        List<UserCoin> userCoin = userCoinService.getUserCoinByCoin(coin);
        for (UserCoin u : userCoin) {
            double changedData =(((Solana - u.getPrice())/Solana)*100);
            if(changedData > 1 ||  changedData < -1){
                log.log(Level.WARNING, "Код валюты: " + coin.getSymbol() + " Имя пользователя: " + u.getUserCoin().getUsername() + " Процент изменение с момента регистрации: " + changedData + "%");
            }
        }
    }


}
