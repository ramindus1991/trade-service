package com.rum.orderservice.service;

import com.rum.orderservice.dto.TradeDTO;
import com.rum.orderservice.dto.TradeRefDTO;
import com.rum.orderservice.entity.TradeEntity;
import com.rum.orderservice.exception.ResourceNotFoundException;
import com.rum.orderservice.exception.UnauthorizedException;
import com.rum.orderservice.repository.TradeRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;

class TradeServiceTest {

    @InjectMocks
    private TradeService tradeService;

    @Mock
    private TradeRepository tradeRepository;

    @Mock
    private AuthService authService;

    @Mock
    private UpdateService updateService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    @DisplayName("Should return TradeRefDTO when trade is created")
    void shouldReturnTradeRefDTOWhenTradeIsCreated() {
        TradeDTO tradeDTO = new TradeDTO();
        TradeEntity tradeEntity = new TradeEntity();
        UUID transactionRef = UUID.randomUUID();
        tradeEntity.setTransactionRef(transactionRef);
        tradeEntity.setUsername("user");
        when(tradeRepository.save(any())).thenReturn(tradeEntity);
        when(authService.getCurrentUser()).thenReturn("user");

        TradeRefDTO expectedTradeRefDTO = new TradeRefDTO();
        expectedTradeRefDTO.setTransactionRef(transactionRef.toString());

        TradeRefDTO response = tradeService.createTrade(tradeDTO);

        assertEquals(expectedTradeRefDTO, response);
    }

    @Test
    @DisplayName("Should return TradeRefDTO when trade is updated")
    void shouldReturnTradeRefDTOWhenTradeIsUpdated() {
        TradeDTO tradeDTO = new TradeDTO();
        TradeEntity tradeEntity = new TradeEntity();
        UUID transactionRef = UUID.randomUUID();
        tradeEntity.setTransactionRef(transactionRef);
        tradeEntity.setUsername("user");
        when(tradeRepository.findById(any(UUID.class))).thenReturn(java.util.Optional.of(tradeEntity));
        when(tradeRepository.save(any())).thenReturn(tradeEntity);
        when(authService.getCurrentUser()).thenReturn("user");

        TradeRefDTO expectedTradeRefDTO = new TradeRefDTO();
        expectedTradeRefDTO.setTransactionRef(transactionRef.toString());

        TradeRefDTO response = tradeService.updateTrade(tradeEntity.getTransactionRef().toString(), tradeDTO);

        assertEquals(expectedTradeRefDTO, response);
    }

    @Test
    @DisplayName("Should throw Not Found when trade is not found in updateTrade")
    void shouldThrowNotFoundWhenTradeIsNotFoundInUpdateTrade() {
        TradeDTO tradeDTO = new TradeDTO();
        TradeEntity tradeEntity = new TradeEntity();
        UUID transactionRef = UUID.randomUUID();
        tradeEntity.setTransactionRef(transactionRef);
        tradeEntity.setUsername("user");
        when(tradeRepository.findById(any(UUID.class))).thenReturn(java.util.Optional.empty());
        when(tradeRepository.save(any())).thenReturn(tradeEntity);
        when(authService.getCurrentUser()).thenReturn("user");

        TradeRefDTO expectedTradeRefDTO = new TradeRefDTO();
        expectedTradeRefDTO.setTransactionRef(transactionRef.toString());

        assertThrows(ResourceNotFoundException.class, ()->tradeService.updateTrade(tradeEntity.getTransactionRef().toString(), tradeDTO));
    }

    @Test
    @DisplayName("Should throw Unauthorized when user is not same in updateTrade")
    void shouldThrowUnauthorizedWhenUserIsNotSameInUpdateTrade() {
        TradeDTO tradeDTO = new TradeDTO();
        TradeEntity tradeEntity = new TradeEntity();
        UUID transactionRef = UUID.randomUUID();
        tradeEntity.setTransactionRef(transactionRef);
        tradeEntity.setUsername("user");
        when(tradeRepository.findById(any(UUID.class))).thenReturn(Optional.of(tradeEntity));
        when(tradeRepository.save(any())).thenReturn(tradeEntity);
        when(authService.getCurrentUser()).thenReturn("user1");

        TradeRefDTO expectedTradeRefDTO = new TradeRefDTO();
        expectedTradeRefDTO.setTransactionRef(transactionRef.toString());

        assertThrows(UnauthorizedException.class, ()->tradeService.updateTrade(tradeEntity.getTransactionRef().toString(), tradeDTO));
    }

    @Test
    @DisplayName("Should return list of TradeRefDTO when trades exist")
    void shouldReturnListOfTradeRefDTOWhenTradesExist() {
        TradeEntity tradeEntity = new TradeEntity();
        UUID transactionRef = UUID.randomUUID();
        tradeEntity.setTransactionRef(transactionRef);
        tradeEntity.setUsername("user");
        when(tradeRepository.findAllByUsername(anyString())).thenReturn(List.of(tradeEntity));
        when(authService.getCurrentUser()).thenReturn("user");

        List<TradeRefDTO> response = tradeService.getTrades();

        assertEquals(1, response.size());
    }

    @Test
    @DisplayName("Should return empty list when no trades exist")
    void shouldReturnEmptyListWhenNoTradesExist() {
        when(tradeRepository.findAllByUsername(anyString())).thenReturn(Collections.emptyList());
        when(authService.getCurrentUser()).thenReturn("user");

        List<TradeRefDTO> response = tradeService.getTrades();

        assertEquals(0, response.size());
    }

    @Test
    @DisplayName("Should return TradeRefDTO when trade is removed")
    void shouldReturnTradeRefDTOWhenTradeIsRemoved() {
        TradeEntity tradeEntity = new TradeEntity();
        UUID transactionRef = UUID.randomUUID();
        tradeEntity.setTransactionRef(transactionRef);
        tradeEntity.setUsername("user");
        when(tradeRepository.findById(any(UUID.class))).thenReturn(java.util.Optional.of(tradeEntity));
        when(authService.getCurrentUser()).thenReturn("user");

        TradeRefDTO expectedTradeRefDTO = new TradeRefDTO();
        expectedTradeRefDTO.setTransactionRef(transactionRef.toString());

        TradeRefDTO response = tradeService.removeTrade(transactionRef.toString());

        assertEquals(expectedTradeRefDTO, response);
    }

    @Test
    @DisplayName("Should throw Not Found when trade is not found in removeTrade")
    void shouldThrowNotFoundWhenTradeIsNotFoundInRemoveTrade() {
        TradeDTO tradeDTO = new TradeDTO();
        TradeEntity tradeEntity = new TradeEntity();
        UUID transactionRef = UUID.randomUUID();
        tradeEntity.setTransactionRef(transactionRef);
        tradeEntity.setUsername("user");
        when(tradeRepository.findById(any(UUID.class))).thenReturn(java.util.Optional.empty());
        when(tradeRepository.save(any())).thenReturn(tradeEntity);
        when(authService.getCurrentUser()).thenReturn("user");

        TradeRefDTO expectedTradeRefDTO = new TradeRefDTO();
        expectedTradeRefDTO.setTransactionRef(transactionRef.toString());

        assertThrows(ResourceNotFoundException.class, ()->tradeService.removeTrade(tradeEntity.getTransactionRef().toString()));
    }

    @Test
    @DisplayName("Should throw Unauthorized when user is not same in removeTrade")
    void shouldThrowUnauthorizedWhenUserIsNotSameInRemoveTrade() {
        TradeEntity tradeEntity = new TradeEntity();
        UUID transactionRef = UUID.randomUUID();
        tradeEntity.setTransactionRef(transactionRef);
        tradeEntity.setUsername("user");
        when(tradeRepository.findById(any(UUID.class))).thenReturn(Optional.of(tradeEntity));
        when(tradeRepository.save(any())).thenReturn(tradeEntity);
        when(authService.getCurrentUser()).thenReturn("user1");

        TradeRefDTO expectedTradeRefDTO = new TradeRefDTO();
        expectedTradeRefDTO.setTransactionRef(transactionRef.toString());

        assertThrows(UnauthorizedException.class, ()->tradeService.removeTrade(tradeEntity.getTransactionRef().toString()));
    }
}