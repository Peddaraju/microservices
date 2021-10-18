package com.app.ms.ccservice.feign;

import com.app.ms.ccservice.CurrencyConversion;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

//@FeignClient(name = "currency-exchange", url = "http://localhost:8000")
@FeignClient(name = "currency-exchange")   // load balance
public interface CurrencyExchangeProxy {

    @GetMapping("/currency-exchange/from/{from}/to/{to}")
    public CurrencyConversion currencyExchangeValue(@PathVariable String from, @PathVariable String to);
}
