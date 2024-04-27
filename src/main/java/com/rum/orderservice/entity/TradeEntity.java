package com.rum.orderservice.entity;

import com.rum.orderservice.dto.TradeType;
import jakarta.persistence.*;
import lombok.Data;

import java.time.DateTimeException;
import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Table(name = "trades")
@Data
public class TradeEntity {
    @GeneratedValue(strategy = GenerationType.UUID)
    @Id
    private UUID transactionRef;
    private Integer instrumentId;
    private TradeType tradeType;
    private double units;
    private double price;
    private String username;
    private LocalDateTime transactionTime;
}
