package com.rum.orderservice.dto;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class TradeRefDTO {
    private String transactionRef;
    private Integer instrumentId;
    private TradeType tradeType;
    private double units;
    private double value;
    private LocalDateTime transactionTime;
}
