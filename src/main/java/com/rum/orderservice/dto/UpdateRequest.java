package com.rum.orderservice.dto;

import lombok.Data;

@Data
public class UpdateRequest {
    private String instrumentName;
    private String tradeType;
    private double units;
}