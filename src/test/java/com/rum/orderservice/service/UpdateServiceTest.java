package com.rum.orderservice.service;

import com.rum.orderservice.dto.TradeRefDTO;
import com.rum.orderservice.dto.TradeType;
import com.rum.orderservice.dto.UpdateRequest;
import com.rum.orderservice.entity.InstrumentEntity;
import com.rum.orderservice.exception.ResourceNotFoundException;
import com.rum.orderservice.feign.PortfolioServiceClient;
import com.rum.orderservice.repository.InstrumentRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

public class UpdateServiceTest {

    @Mock
    private PortfolioServiceClient portfolioServiceClient;

    @Mock
    private InstrumentRepository instrumentRepository;

    @Mock
    private AuthService authService;

    @InjectMocks
    private UpdateService updateService;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    @DisplayName("Should update trade when valid trade reference is provided")
    void shouldUpdateTradeWhenValidTradeReferenceIsProvided() {
        TradeRefDTO tradeRefDTO = new TradeRefDTO();
        tradeRefDTO.setInstrumentId(1);
        tradeRefDTO.setTradeType(TradeType.BUY);
        tradeRefDTO.setUnits(10);

        InstrumentEntity instrument = new InstrumentEntity();
        instrument.setInstrumentName("APPL");

        when(instrumentRepository.findById(any(Long.class))).thenReturn(Optional.of(instrument));
        when(authService.getCurrentUser()).thenReturn("user1");

        updateService.update(tradeRefDTO);

        verify(portfolioServiceClient).sendUpdate(anyString(), any(UpdateRequest.class));
    }

    @Test
    @DisplayName("Should throw exception when invalid instrument id is provided")
    void shouldThrowExceptionWhenInvalidInstrumentIdIsProvided() {
        TradeRefDTO tradeRefDTO = new TradeRefDTO();
        tradeRefDTO.setInstrumentId(1);

        when(instrumentRepository.findById(any(Long.class))).thenReturn(Optional.empty());

        assertThrows(ResourceNotFoundException.class, () -> updateService.update(tradeRefDTO));
    }
}