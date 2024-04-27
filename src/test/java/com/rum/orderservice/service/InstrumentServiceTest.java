package com.rum.orderservice.service;

import com.rum.orderservice.dto.InstrumentDTO;
import com.rum.orderservice.entity.InstrumentEntity;
import com.rum.orderservice.repository.InstrumentRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

public class InstrumentServiceTest {

    @Mock
    private InstrumentRepository instrumentRepository;

    @InjectMocks
    private InstrumentService instrumentService;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    @DisplayName("Should return list of instruments when instruments exist")
    void shouldReturnListOfInstrumentsWhenInstrumentsExist() {
        InstrumentDTO expectedInstrumentDTO = new InstrumentDTO();
        expectedInstrumentDTO.setInstrumentId(1);
        expectedInstrumentDTO.setInstrumentName("Instrument1");
        expectedInstrumentDTO.setInstrumentType("Type1");
        expectedInstrumentDTO.setInstrumentValue(100.0);

        InstrumentEntity instrumentEntity = new InstrumentEntity();
        instrumentEntity.setInstrumentId(1);
        instrumentEntity.setInstrumentName("Instrument1");
        instrumentEntity.setInstrumentType("Type1");
        instrumentEntity.setInstrumentValue(100.0);

        when(instrumentRepository.findAll()).thenReturn(List.of(instrumentEntity));

        List<InstrumentDTO> result = instrumentService.getInstruments();

        assertEquals(1, result.size());
        assertEquals(expectedInstrumentDTO, result.get(0));
    }

    @Test
    @DisplayName("Should return empty list when no instruments exist")
    void shouldReturnEmptyListWhenNoInstrumentsExist() {
        when(instrumentRepository.findAll()).thenReturn(Collections.emptyList());

        List<InstrumentDTO> result = instrumentService.getInstruments();

        assertEquals(0, result.size());
    }
}