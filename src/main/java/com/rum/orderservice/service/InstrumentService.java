package com.rum.orderservice.service;

import com.rum.orderservice.dto.InstrumentDTO;
import com.rum.orderservice.dto.TradeDTO;
import com.rum.orderservice.dto.TradeRefDTO;
import com.rum.orderservice.repository.InstrumentRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class InstrumentService {

    private InstrumentRepository instrumentRepository;

    public List<InstrumentDTO> getInstruments() {
        return instrumentRepository.findAll().stream().map(entity->{
            InstrumentDTO instrumentDTO = new InstrumentDTO();
            instrumentDTO.setInstrumentId(entity.getInstrumentId());
            instrumentDTO.setInstrumentName(entity.getInstrumentName());
            instrumentDTO.setInstrumentType(entity.getInstrumentType());
            instrumentDTO.setInstrumentValue(entity.getInstrumentValue());
            return instrumentDTO;
        }).toList();
    }
}
