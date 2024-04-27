package com.rum.orderservice.dto;

import lombok.Data;

@Data
public class InstrumentDTO {
    private Integer instrumentId;
    private String instrumentName;
    private Double instrumentValue;
    private String instrumentType;
}
