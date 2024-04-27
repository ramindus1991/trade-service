package com.rum.orderservice.controller;

import com.rum.orderservice.dto.InstrumentDTO;
import com.rum.orderservice.service.InstrumentService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

class InstrumentControllerTest {

    private InstrumentService instrumentService;
    private InstrumentController instrumentController;

    @BeforeEach
    void setUp() {
        instrumentService = mock(InstrumentService.class);
        instrumentController = new InstrumentController(instrumentService);
    }

    @Test
    @DisplayName("Should return list of instruments when service has instruments")
    void shouldReturnListOfInstrumentsWhenServiceHasInstruments() {
        InstrumentDTO instrumentDTO = new InstrumentDTO();
        when(instrumentService.getInstruments()).thenReturn(List.of(instrumentDTO));

        ResponseEntity<List<InstrumentDTO>> response = instrumentController.getAllTrades();

        assertEquals(HttpStatus.ACCEPTED, response.getStatusCode());
        assertEquals(1, response.getBody().size());
        assertEquals(instrumentDTO, response.getBody().get(0));
    }

    @Test
    @DisplayName("Should return accepted status with empty list when service has no instruments")
    void shouldReturnAcceptedStatusWithEmptyListWhenServiceHasNoInstruments() {
        when(instrumentService.getInstruments()).thenReturn(Collections.emptyList());

        ResponseEntity<List<InstrumentDTO>> response = instrumentController.getAllTrades();

        assertEquals(HttpStatus.ACCEPTED, response.getStatusCode());
        assertEquals(0, response.getBody().size());
    }
}