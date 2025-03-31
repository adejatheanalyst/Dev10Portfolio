package org.example.domain;

import org.example.data.DataAccessException;
import org.example.data.InstrumentRepositoryDouble;
import org.example.models.Instrument;
import org.example.models.InstrumentType;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class InstrumentServiceTest {

    InstrumentService service;

    @BeforeEach
    void makeFreshService() {
        service = new InstrumentService(new InstrumentRepositoryDouble());
    }

    @Test
    void findAll() throws DataAccessException {
        List<Instrument> result = service.findAll();

        assertEquals(2, result.size());
    }

    @Nested
    class FindByType {

        @Test
        void findsSomething() throws DataAccessException {
            InstrumentListResult result = service.findByType(InstrumentType.TUBA);

            assertTrue(result.isSuccess());
            assertNotNull(result.getInstrumentList());
            assertEquals(1, result.getInstrumentList().size());
        }

        @Test
        void findsNothing() throws DataAccessException {
            InstrumentListResult result = service.findByType(InstrumentType.PIANO);

            assertFalse(result.isSuccess());
            assertNull(result.getInstrumentList());
        }
    }

    @Test
    void findBySerialNumber() {

    }

    @Nested
    class AddTests {
        @Test
        void happyPath() throws DataAccessException {
            Instrument instrumentToAdd = new Instrument(
                    "xyz",
                    InstrumentType.TRUMPET,
                    250,
                    "Billy",
                    false
            );
            int countBeforeAdd = service.findAll().size();

            InstrumentResult result = service.add(instrumentToAdd);

            assertTrue(result.isSuccess());
            assertNotNull(result.getInstrument());
            assertEquals(instrumentToAdd, result.getInstrument());
            assertEquals(countBeforeAdd + 1, service.findAll().size());
        }

        @Test
        void failsWhenSerialNumberIsBlank() throws DataAccessException {
            Instrument instrumentToAdd = new Instrument(
                    "",
                    InstrumentType.TRUMPET,
                    250,
                    "Billy",
                    false
            );
            int countBeforeAdd = service.findAll().size();

            InstrumentResult result = service.add(instrumentToAdd);

            assertFalse(result.isSuccess());
            assertEquals("Serial number cannot be blank", result.getErrorMessages().get(0));
            assertEquals(countBeforeAdd, service.findAll().size());
        }

        @Test
        void failsWhenSerialNumberIsNull() throws DataAccessException {
            Instrument instrumentToAdd = new Instrument(
                    null,
                    InstrumentType.TRUMPET,
                    250,
                    "Billy",
                    false
            );
            int countBeforeAdd = service.findAll().size();

            InstrumentResult result = service.add(instrumentToAdd);

            assertFalse(result.isSuccess());
            assertEquals("Serial number cannot be null", result.getErrorMessages().get(0));
            assertEquals(countBeforeAdd, service.findAll().size());
        }

        @Test
        void failsWhenSerialNumberIsDuplicate() throws DataAccessException {
            Instrument instrumentToAdd = new Instrument(
                    InstrumentRepositoryDouble.EXISTING_SERIAL_NUMBER,
                    InstrumentType.TRUMPET,
                    250,
                    "Billy",
                    false
            );
            int countBeforeAdd = service.findAll().size();

            InstrumentResult result = service.add(instrumentToAdd);

            assertFalse(result.isSuccess());
            assertEquals("Serial number must be unique", result.getErrorMessages().get(0));
            assertEquals(countBeforeAdd, service.findAll().size());
        }

        @Test
        void failsWhenCostIsNegative() throws DataAccessException {
            Instrument instrumentToAdd = new Instrument(
                    "xyz",
                    InstrumentType.TRUMPET,
                    -1,
                    "Billy",
                    false
            );
            int countBeforeAdd = service.findAll().size();

            InstrumentResult result = service.add(instrumentToAdd);

            assertFalse(result.isSuccess());
            assertEquals("Cost must be 0 or positive", result.getErrorMessages().get(0));
            assertEquals(countBeforeAdd, service.findAll().size());
        }

        @Test
        void failsWhenStudentIsNull() throws DataAccessException {
            Instrument instrumentToAdd = new Instrument(
                    "xyz",
                    InstrumentType.TRUMPET,
                    250,
                    null,
                    false
            );
            int countBeforeAdd = service.findAll().size();

            InstrumentResult result = service.add(instrumentToAdd);

            assertFalse(result.isSuccess());
            assertEquals("Student cannot be null", result.getErrorMessages().get(0));
            assertEquals(countBeforeAdd, service.findAll().size());
        }
    }

    @Nested
    class UpdateTests {
        @Test
        void happyPath() throws DataAccessException {
            Instrument instrumentToUpdate = new Instrument(
                    InstrumentRepositoryDouble.EXISTING_SERIAL_NUMBER,
                    InstrumentType.TUBA,
                    100,
                    "Jimmy",
                    true
            );

            InstrumentResult result = service.update(instrumentToUpdate);

            assertTrue(result.isSuccess());
            assertNotNull(result.getInstrument());
            assertEquals(instrumentToUpdate, result.getInstrument());
        }

        @Test
        void failsWhenSerialNumberIsNull() throws DataAccessException {
            Instrument instrumentToUpdate = new Instrument(
                    null,
                    InstrumentType.TUBA,
                    100,
                    "Jimmy",
                    true
            );

            InstrumentResult result = service.update(instrumentToUpdate);

            assertFalse(result.isSuccess());
            assertNull(result.getInstrument());
            assertEquals("Serial number cannot be null", result.getErrorMessages().get(0));
        }

        @Test
        void failsWhenSerialNumberIsBlank() throws DataAccessException {
            Instrument instrumentToUpdate = new Instrument(
                    "",
                    InstrumentType.TRUMPET,
                    250,
                    "Billy",
                    false
            );

            InstrumentResult result = service.update(instrumentToUpdate);

            assertFalse(result.isSuccess());
            assertEquals("Serial number cannot be blank", result.getErrorMessages().get(0));
        }

        @Test
        void failsWhenCostIsNegative() throws DataAccessException {
            Instrument instrumentToAdd = new Instrument(
                    InstrumentRepositoryDouble.EXISTING_SERIAL_NUMBER,
                    InstrumentType.TRUMPET,
                    -1,
                    "Billy",
                    false
            );

            InstrumentResult result = service.update(instrumentToAdd);

            assertFalse(result.isSuccess());
            assertEquals("Cost must be 0 or positive", result.getErrorMessages().get(0));
        }

        @Test
        void failsWhenStudentIsNull() throws DataAccessException {
            Instrument instrumentToAdd = new Instrument(
                    InstrumentRepositoryDouble.EXISTING_SERIAL_NUMBER,
                    InstrumentType.TRUMPET,
                    250,
                    null,
                    false
            );

            InstrumentResult result = service.update(instrumentToAdd);

            assertFalse(result.isSuccess());
            assertEquals("Student cannot be null", result.getErrorMessages().get(0));
        }

        @Test
        void failsWhenChangingInstrumentType() throws DataAccessException {
            Instrument instrumentToAdd = new Instrument(
                    InstrumentRepositoryDouble.EXISTING_SERIAL_NUMBER,
                    InstrumentType.PIANO,
                    250,
                    "Jimmy",
                    false
            );

            InstrumentResult result = service.update(instrumentToAdd);

            assertFalse(result.isSuccess());
            assertEquals("Cannot change instrument type", result.getErrorMessages().get(0));
        }

        @Test
        void failsWhenInstrumentDoesNotExist() throws DataAccessException {
            Instrument instrumentToAdd = new Instrument(
                    "qwerqwer",
                    InstrumentType.PIANO,
                    250,
                    "Jimmy",
                    false
            );

            InstrumentResult result = service.update(instrumentToAdd);

            assertFalse(result.isSuccess());
            assertEquals("Instrument does not exist", result.getErrorMessages().get(0));
        }
    }

    @Nested
    class DeleteTests {
        @Test
        void happyPath() throws DataAccessException {
            SerialNumberResult result = service.deleteBySerialNumber(InstrumentRepositoryDouble.EXISTING_SERIAL_NUMBER);

            assertTrue(result.isSuccess());
            assertEquals(InstrumentRepositoryDouble.EXISTING_SERIAL_NUMBER, result.getSerialNumber());
        }

        @Test
        void failsWhenCannotFind() throws DataAccessException {
            SerialNumberResult result = service.deleteBySerialNumber("zxcvzxcvzxcv");

            assertFalse(result.isSuccess());
            assertNull(result.getSerialNumber());
            assertEquals("Could not find instrument for deletion", result.getErrorMessages().get(0));
        }
    }
}