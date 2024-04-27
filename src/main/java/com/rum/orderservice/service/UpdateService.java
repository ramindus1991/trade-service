package com.rum.orderservice.service;

import com.rum.orderservice.dto.TradeRefDTO;
import com.rum.orderservice.dto.UpdateRequest;
import com.rum.orderservice.exception.ResourceNotFoundException;
import com.rum.orderservice.feign.PortfolioServiceClient;
import com.rum.orderservice.repository.InstrumentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UpdateService {

        @Autowired
        PortfolioServiceClient portfolioServiceClient;

        @Autowired
        private InstrumentRepository instrumentRepository;

        @Autowired
        private AuthService authService;

        public void update(TradeRefDTO tradeRefDTO){
            UpdateRequest updateRequest = new UpdateRequest();
            updateRequest.setInstrumentName(instrumentRepository.findById(Long.valueOf(tradeRefDTO.getInstrumentId())).orElseThrow().getInstrumentName());
            updateRequest.setTradeType(tradeRefDTO.getTradeType().toString());
            updateRequest.setUnits(tradeRefDTO.getUnits());
            portfolioServiceClient.sendUpdate(authService.getCurrentUser(), updateRequest);
        }

}
