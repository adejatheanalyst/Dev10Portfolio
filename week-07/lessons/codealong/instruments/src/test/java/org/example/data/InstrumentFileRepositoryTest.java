package org.example.data;

import org.example.models.Instrument;
import org.example.models.InstrumentType;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.lang.reflect.Array;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class InstrumentFileRepositoryTest {

    String testFilePath = "./data/instruments_test.csv";
    String seedFilePath = "./data/instruments_seed.csv";
    String nonExistentSerialNumber = "zxcvzxcv";

    InstrumentRepository repository = new InstrumentFileRepository(testFilePath);

    @BeforeEach
    void restorePristineTestData() throws IOException {
        Path seedPath = Paths.get(seedFilePath);
        Path testPath = Paths.get(testFilePath);

        Files.copy(seedPath, testPath, StandardCopyOption.REPLACE_EXISTING);
    }

    @Test
    void findAll() throws DataAccessException {
        List<Instrument> result = repository.findAll();

        assertEquals(15, result.size());
    }

    @Test
    void findByTypeFindsInstruments() throws DataAccessException {
        List<Instrument> result = repository.findByType(InstrumentType.TUBA);

        assertEquals(2, result.size());
    }

    @Test
    void findByTypeFindsNothing() throws DataAccessException {
        List<Instrument> result = repository.findByType(InstrumentType.TRUMPET);

        assertEquals(0, result.size());
    }

    @Test
    void findBySerialNumber() {
        // TODO: test positive and negative outcomes
    }

    @Test
    void add() throws DataAccessException {
        Instrument instrumentToAdd = new Instrument("asdfasdf", InstrumentType.TRUMPET, 200, "Billy", false);

        repository.add(instrumentToAdd);

        assertEquals(16, repository.findAll().size());
    }

    @Test
    void updateSucceeds() throws DataAccessException {
        Instrument instrumentToUpdate = new Instrument("l0l663",InstrumentType.TUBA,600,"Bobby",true);

        boolean result = repository.update(instrumentToUpdate);

        assertTrue(result);
        Instrument instrumentAfterUpdate = null;
        for (Instrument instrument : repository.findAll()) {
            if (instrument.getSerialNumber().equals("l0l663")) {
                instrumentAfterUpdate = instrument;
            }
        }
        assertEquals(600, instrumentAfterUpdate.getCost());
        assertEquals("Bobby", instrumentAfterUpdate.getStudent());
        assertEquals(true, instrumentAfterUpdate.isNeedsRepair());
    }

    @Test
    void updateFailsToFind() throws DataAccessException {
        Instrument instrumentToUpdate = new Instrument(nonExistentSerialNumber,InstrumentType.TUBA,600,"Bobby",true);

        boolean result = repository.update(instrumentToUpdate);

        assertFalse(result);
    }

    @Test
    void deleteBySerialNumberHappyPath() throws DataAccessException {
        boolean result = repository.deleteBySerialNumber("l0l663");

        assertTrue(result);
        assertEquals(14, repository.findAll().size());
    }

    @Test
    void deleteBySerialNumberUnhappyPath() throws DataAccessException {
        boolean result = repository.deleteBySerialNumber(nonExistentSerialNumber);

        assertFalse(result);
        assertEquals(15, repository.findAll().size());
    }
}