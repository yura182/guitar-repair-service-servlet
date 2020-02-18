package com.yura.repair.service.impl;

import com.yura.repair.dto.InstrumentDto;
import com.yura.repair.entity.InstrumentEntity;
import com.yura.repair.exception.EntityNotFoundException;
import com.yura.repair.exception.EntitySavingException;
import com.yura.repair.exception.InvalidParameterException;
import com.yura.repair.repository.InstrumentRepository;
import com.yura.repair.service.mapper.EntityMapper;
import com.yura.repair.service.validator.Validator;
import org.junit.After;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.Optional;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class InstrumentServiceImplTest {
    private static final InstrumentDto INSTRUMENT_DTO = getInstrumentDto();
    private static final InstrumentEntity INSTRUMENT_ENTITY = getInstrumentEntity();

    @Rule
    public ExpectedException exception = ExpectedException.none();

    @Mock
    private InstrumentRepository instrumentRepository;

    @Mock
    private EntityMapper<InstrumentEntity, InstrumentDto> instrumentMapper;

    @Mock
    private Validator<InstrumentDto> instrumentValidator;

    @InjectMocks
    private InstrumentServiceImpl instrumentService;

    @Test
    public void addShouldReturnIdOfAddedInstrument() {
        when(instrumentMapper.mapDtoToEntity(INSTRUMENT_DTO)).thenReturn(INSTRUMENT_ENTITY);
        when(instrumentRepository.saveAndReturnId(INSTRUMENT_ENTITY)).thenReturn(Optional.of(1));

        int expected = 1;
        int actual = instrumentService.add(INSTRUMENT_DTO);

        assertEquals(expected, actual);
    }

    @Test
    public void addShouldThrowEntitySavingException() {
        exception.expect(EntitySavingException.class);
        exception.expectMessage("Error during saving Instrument");

        instrumentService.add(INSTRUMENT_DTO);
    }

    @Test
    public void addShouldThrowInvalidParameterExceptionForNullParameter() {
        exception.expect(InvalidParameterException.class);

        doThrow(InvalidParameterException.class).when(instrumentValidator).validate(null);

        instrumentService.add(null);
    }

    @Test
    public void findByIdShouldShouldReturnInstrument() {
        when(instrumentRepository.findById(anyInt())).thenReturn(Optional.of(INSTRUMENT_ENTITY));
        when(instrumentMapper.mapEntityToDto(INSTRUMENT_ENTITY)).thenReturn(INSTRUMENT_DTO);

        InstrumentDto actual = instrumentService.findById(1);

        assertEquals(INSTRUMENT_DTO, actual);
    }

    @Test
    public void findByIdShouldThrowEntityNotFoundException() {
        exception.expect(EntityNotFoundException.class);
        exception.expectMessage("Instrument not found");

        when(instrumentRepository.findById(anyInt())).thenReturn(Optional.empty());

        instrumentService.findById(1);
    }

    @Test
    public void numberOfEntriesShouldReturnNumberOfEntries() {
        when(instrumentRepository.countAll()).thenReturn(10);

        int expected = 10;
        int actual = instrumentService.numberOfEntries();

        assertEquals(expected, actual);
    }

    private static InstrumentDto getInstrumentDto() {
        return InstrumentDto.builder()
                .withBrand("Cort")
                .withModel("123")
                .withYear(1995)
                .build();
    }

    private static InstrumentEntity getInstrumentEntity() {
        return InstrumentEntity.builder()
                .withId(1)
                .withBrand("Cort")
                .withModel("123")
                .withYear(1995)
                .build();
    }
}