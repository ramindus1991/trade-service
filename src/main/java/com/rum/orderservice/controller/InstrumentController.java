package com.rum.orderservice.controller;

import com.rum.orderservice.dto.InstrumentDTO;
import com.rum.orderservice.service.InstrumentService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Slf4j
@RequestMapping("instruments")
@RestController
@AllArgsConstructor
public class InstrumentController {

    private InstrumentService instrumentService;

    @GetMapping
    public ResponseEntity<List<InstrumentDTO>> getAllTrades() {
        return new ResponseEntity<>(instrumentService.getInstruments(), HttpStatus.ACCEPTED);
    }

}
