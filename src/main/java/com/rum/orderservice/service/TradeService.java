package com.rum.orderservice.service;

import com.rum.orderservice.dto.TradeDTO;
import com.rum.orderservice.dto.TradeRefDTO;
import com.rum.orderservice.entity.TradeEntity;
import com.rum.orderservice.exception.ResourceNotFoundException;
import com.rum.orderservice.exception.UnauthorizedException;
import com.rum.orderservice.repository.TradeRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Service
@AllArgsConstructor
public class TradeService {

    private TradeRepository tradeRepository;

    private AuthService authService;

    private UpdateService updateService;

    public TradeRefDTO createTrade(TradeDTO tradeDTO) {
        TradeEntity tradeEntity = map(tradeDTO);
        tradeEntity.setUsername(authService.getCurrentUser());
        tradeEntity.setTransactionTime(LocalDateTime.now());
        TradeRefDTO result = map(tradeRepository.save(tradeEntity));
        updateService.update(result);
        return result;
    }

    public TradeRefDTO updateTrade(String transactionRef, TradeDTO tradeDTO) {
        TradeEntity tradeEntity = tradeRepository.findById(UUID.fromString(transactionRef)).orElseThrow(() -> new ResourceNotFoundException("Trade not found"));
        if(!tradeEntity.getUsername().equals(authService.getCurrentUser()))
            throw new UnauthorizedException("Unauthorized access");
        tradeEntity.setInstrumentId(tradeDTO.getInstrumentId());
        tradeEntity.setTradeType(tradeDTO.getTradeType());
        tradeEntity.setUnits(tradeDTO.getUnits());
        tradeEntity.setPrice(tradeDTO.getPrice());
        TradeRefDTO result =  map(tradeRepository.save(tradeEntity));
        updateService.update(result);
        return result;
    }

    public TradeRefDTO removeTrade(String transactionRef) {
        TradeEntity tradeEntity = tradeRepository.findById(UUID.fromString(transactionRef)).orElseThrow(() -> new ResourceNotFoundException("Trade not found"));
        if(tradeEntity.getUsername().equals(authService.getCurrentUser()))
            tradeRepository.delete(tradeEntity);
        else
            throw new UnauthorizedException("Unauthorized access");
        TradeRefDTO result = map(tradeEntity);
        updateService.update(result);
        return result;
    }

    public List<TradeRefDTO> getTrades() {
        return map(tradeRepository.findAllByUsername(authService.getCurrentUser()));
    }

    TradeRefDTO map(TradeEntity tradeEntity){
        TradeRefDTO dto = new TradeRefDTO();
        dto.setTransactionRef(tradeEntity.getTransactionRef().toString());
        dto.setInstrumentId(tradeEntity.getInstrumentId());
        dto.setTradeType(tradeEntity.getTradeType());
        dto.setUnits(tradeEntity.getUnits());
        dto.setValue(tradeEntity.getPrice());
        dto.setTransactionTime(tradeEntity.getTransactionTime());
        return dto;
    }

    TradeEntity map(TradeDTO tradeDTO){
        TradeEntity entity = new TradeEntity();
        entity.setInstrumentId(tradeDTO.getInstrumentId());
        entity.setTradeType(tradeDTO.getTradeType());
        entity.setUnits(tradeDTO.getUnits());
        entity.setPrice(tradeDTO.getPrice());
        return entity;
    }

    private List<TradeRefDTO> map(List<TradeEntity> all) {
        return all.stream().map(this::map).toList();
    }

}
