package com.rum.orderservice.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class TradeDTO {
    @NotBlank(message = "instrumentId cannot be blank")
    private Integer instrumentId;
    @NotBlank(message = "tradeType cannot be blank")
    private TradeType tradeType;
    @NotBlank(message = "units cannot be blank")
    private double units;
    @NotBlank(message = "price cannot be blank")
    private double price;
}
