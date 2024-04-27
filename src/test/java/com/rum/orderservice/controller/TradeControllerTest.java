package com.rum.orderservice.controller;

import com.rum.orderservice.dto.TradeDTO;
import com.rum.orderservice.dto.TradeRefDTO;
import com.rum.orderservice.service.TradeService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.Collections;
import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

public class TradeControllerTest {

    @Mock
    private TradeService tradeService;

    @InjectMocks
    private TradeController tradeController;

    private MockMvc mockMvc;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.openMocks(this);
        mockMvc = MockMvcBuilders.standaloneSetup(tradeController).build();
    }

    @Test
    @DisplayName("Should return created status when trade is created")
    void shouldReturnCreatedStatusWhenTradeIsCreated() throws Exception {
        when(tradeService.createTrade(any(TradeDTO.class))).thenReturn(new TradeRefDTO());

        mockMvc.perform(post("/trades")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{}"))
                .andExpect(status().isCreated());
    }

    @Test
    @DisplayName("Should return created status when trade is updated")
    void shouldReturnCreatedStatusWhenTradeIsUpdated() throws Exception {
        when(tradeService.updateTrade(anyString(), any(TradeDTO.class))).thenReturn(new TradeRefDTO());

        mockMvc.perform(put("/trades/transactionRef")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{}"))
                .andExpect(status().isCreated());
    }

    @Test
    @DisplayName("Should return ok status when trades exist")
    void shouldReturnOkStatusWhenTradesExist() throws Exception {
        when(tradeService.getTrades()).thenReturn(List.of(new TradeRefDTO()));

        mockMvc.perform(get("/trades"))
                .andExpect(status().isOk());
    }

    @Test
    @DisplayName("Should return ok status when no trades exist")
    void shouldReturnOkStatusWhenNoTradesExist() throws Exception {
        when(tradeService.getTrades()).thenReturn(Collections.emptyList());

        mockMvc.perform(get("/trades"))
                .andExpect(status().isOk());
    }

    @Test
    @DisplayName("Should return ok status when trade is removed")
    void shouldReturnOkStatusWhenTradeIsRemoved() throws Exception {
        when(tradeService.removeTrade(anyString())).thenReturn(new TradeRefDTO());

        mockMvc.perform(delete("/trades/transactionRef"))
                .andExpect(status().isOk());
    }
}