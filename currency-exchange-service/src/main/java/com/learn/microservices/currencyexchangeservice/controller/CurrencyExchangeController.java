package com.learn.microservices.currencyexchangeservice.controller;

import com.learn.microservices.currencyexchangeservice.bean.CurrencyExchange;
import com.learn.microservices.currencyexchangeservice.repository.CurrencyExchangeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigDecimal;

@RestController
public class CurrencyExchangeController {

    @Autowired
    private Environment environment;

    @Autowired
    private CurrencyExchangeRepository repository;

    @GetMapping("/currency-exchange/from/{from}/to/{to}")
    public CurrencyExchange retrieveExchangeValue(@PathVariable String from, @PathVariable String to){
        CurrencyExchange exchange= repository.findByFromAndTo(from,to);
        exchange.setEnvironment(environment.getProperty("local.server.port"));
        if(exchange == null){
            throw new RuntimeException("Unable to Find data for from"+from+" To"+to);
        }
        //return new CurrencyExchange(1L,"USD","INR", BigDecimal.valueOf(50),environment.getProperty("local.server.port"));
        return exchange;
    }
}
