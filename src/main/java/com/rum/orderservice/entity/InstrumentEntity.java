package com.rum.orderservice.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Table(name = "instruments")
@Data
public class InstrumentEntity {
    @Id
    private Integer instrumentId;
    private String instrumentName;
    private Double instrumentValue;
    private String instrumentType;
}
