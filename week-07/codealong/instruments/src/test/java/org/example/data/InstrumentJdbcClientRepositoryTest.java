package org.example.data;

import org.example.DataHelper;
import org.example.models.Instrument;
import org.example.models.InstrumentType;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.jdbc.core.simple.JdbcClient;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.NONE)
class InstrumentJdbcClientRepositoryTest {

//    JdbcClient client = DataHelper.getJdbcClient();

//    InstrumentRepository repository = new InstrumentJdbcClientRepository(client);
    @Autowired
    InstrumentRepository repository;
    @Autowired
    JdbcClient client;

    Instrument baselineInstrument = new Instrument(
            "zxcvzxcv",
            InstrumentType.TUBA,
            500,
            "Suzie",
            false
    );

    String existingSerialNumber = "msj111";

    String nonExistingSerialNumber = "asdfasdf";

    int startingNumberOfInstruments = 3;

    @BeforeEach
    void setup() {
        client.sql("CALL set_known_good_state()").update();
    }

    @Test
    void findAll() throws DataAccessException {
        List<Instrument> result = repository.findAll();

        assertEquals(startingNumberOfInstruments, result.size());
    }

    @Test
    void findByType() throws DataAccessException {
        List<Instrument> result = repository.findByType(InstrumentType.PIANO);

        assertEquals(1, result.size());
        assertEquals(InstrumentType.PIANO, result.get(0).getInstrumentType());
    }

    @Test
    void findBySerialNumberSuccess() throws DataAccessException {
        Instrument result = repository.findBySerialNumber(existingSerialNumber);

        assertNotNull(result);
        assertEquals(existingSerialNumber, result.getSerialNumber());
    }

    @Test
    void findBySerialNumberFailure() throws DataAccessException {
        Instrument result = repository.findBySerialNumber(nonExistingSerialNumber);

        assertNull(result);
    }

    @Test
    void add() throws DataAccessException {
        Instrument instrumentToAdd = baselineInstrument;

        Instrument result = repository.add(instrumentToAdd);

        assertEquals(startingNumberOfInstruments + 1, repository.findAll().size());
    }

    @Test
    void updateFindsSuccessfully() throws DataAccessException {
        int newCost = 501;
        Instrument instrumentToUpdate = baselineInstrument;
        instrumentToUpdate.setSerialNumber(existingSerialNumber);
        instrumentToUpdate.setCost(newCost);

        boolean result = repository.update(instrumentToUpdate);

        assertTrue(result);
        assertEquals(startingNumberOfInstruments, repository.findAll().size());
        assertEquals(newCost, repository.findBySerialNumber(existingSerialNumber).getCost());
    }

    @Test
    void updateFailsToFind() throws DataAccessException {
        Instrument instrumentToUpdate = baselineInstrument;
        instrumentToUpdate.setSerialNumber(nonExistingSerialNumber);

        boolean result = repository.update(instrumentToUpdate);

        assertFalse(result);
    }

    @Test
    void deleteBySerialNumberSucceeds() throws DataAccessException {
        boolean result = repository.deleteBySerialNumber(existingSerialNumber);

        assertTrue(result);
        assertEquals(startingNumberOfInstruments - 1, repository.findAll().size());
    }

    @Test
    void deleteBySerialNumberFails() throws DataAccessException {
        boolean result = repository.deleteBySerialNumber(nonExistingSerialNumber);

        assertFalse(result);
        assertEquals(startingNumberOfInstruments, repository.findAll().size());
    }
}