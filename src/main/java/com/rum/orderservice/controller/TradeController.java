package com.rum.orderservice.controller;

import com.rum.orderservice.dto.TradeDTO;
import com.rum.orderservice.dto.TradeRefDTO;
import com.rum.orderservice.service.AuthService;
import com.rum.orderservice.service.TradeService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RequestMapping("trades")
@RestController
public class TradeController {

    @Autowired
    private TradeService tradeService;

    @PostMapping
    public ResponseEntity<TradeRefDTO> createTrade(@RequestBody TradeDTO tradeDTO) {
        return new ResponseEntity<>(tradeService.createTrade(tradeDTO), HttpStatus.CREATED);
    }

    @PutMapping("/{transactionRef}")
    public ResponseEntity<TradeRefDTO> updateTrade(@RequestBody TradeDTO tradeDTO, @PathVariable String transactionRef) {
        return new ResponseEntity<>(tradeService.updateTrade(transactionRef, tradeDTO), HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<TradeRefDTO>> getAllTrades() {
        return new ResponseEntity<>(tradeService.getTrades(), HttpStatus.OK);
    }

    @DeleteMapping("/{transactionRef}")
    public ResponseEntity<TradeRefDTO> removeTrade(@PathVariable String transactionRef) {
        return new ResponseEntity<>(tradeService.removeTrade(transactionRef), HttpStatus.OK);
    }
}
