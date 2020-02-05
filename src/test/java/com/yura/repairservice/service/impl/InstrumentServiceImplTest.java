package com.yura.repairservice.service.impl;

import com.yura.repairservice.domain.instrument.Instrument;
import com.yura.repairservice.entity.InstrumentEntity;
import com.yura.repairservice.exception.EntityNotFoundException;
import com.yura.repairservice.exception.EntitySavingException;
import com.yura.repairservice.exception.InvalidParameterException;
import com.yura.repairservice.repository.InstrumentRepository;
import com.yura.repairservice.service.mapper.EntityMapper;
import com.yura.repairservice.service.validator.Validator;
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
    private static final Instrument INSTRUMENT = getInstrument();
    private static final InstrumentEntity INSTRUMENT_ENTITY = getInstrumentEntity();

    @Rule
    public ExpectedException exception = ExpectedException.none();

    @Mock
    private InstrumentRepository instrumentRepository;

    @Mock
    private EntityMapper<InstrumentEntity, Instrument> instrumentMapper;

    @Mock
    private Validator<Instrument> instrumentValidator;

    @InjectMocks
    private InstrumentServiceImpl instrumentService;

    @After
    public void resetMocks() {
        reset(instrumentRepository, instrumentMapper, instrumentValidator);
    }

    @Test
    public void addShouldReturnIdOfAddedInstrument() {
        when(instrumentMapper.mapDomainToEntity(INSTRUMENT)).thenReturn(INSTRUMENT_ENTITY);
        when(instrumentRepository.saveAndReturnId(INSTRUMENT_ENTITY)).thenReturn(Optional.of(1));

        int expected = 1;
        int actual = instrumentService.add(INSTRUMENT);

        assertEquals(expected, actual);
    }

    @Test
    public void addShouldThrowEntitySavingException() {
        exception.expect(EntitySavingException.class);
        exception.expectMessage("Error during saving Instrument");

        when(instrumentRepository.saveAndReturnId(INSTRUMENT_ENTITY)).thenReturn(Optional.empty());

        instrumentService.add(INSTRUMENT);
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
        when(instrumentMapper.mapEntityToDomain(INSTRUMENT_ENTITY)).thenReturn(INSTRUMENT);

        Instrument actual = instrumentService.findById(1);

        assertEquals(INSTRUMENT, actual);
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

    private static Instrument getInstrument() {
        return Instrument.builder()
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