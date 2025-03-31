package org.example.data;

import org.example.DataHelper;
import org.example.models.Instrument;
import org.example.models.InstrumentType;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.jdbc.core.simple.JdbcClient;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class InstrumentJdbcClientRepositoryTest {


    JdbcClient client = DataHelper.getJdbcClient();


    InstrumentRepository repository = new InstrumentJdbcClientRepository(client);
Instrument baseline = new Instrument("msj111",InstrumentType.TUBA,600,"Bobby",true);
 String existingSerialNumber = "msj111";

 int startingSize = 3;
    @BeforeEach
   void setup() {;
     client.sql("call set_known_good_state()").update();
    }

    @Test
    void findAll() throws DataAccessException {
     List<Instrument> result = repository.findAll();

     assertEquals(startingSize, result.size());
    }

    @Test
    void findByType() throws DataAccessException {
     List<Instrument> result = repository.findByType(InstrumentType.TUBA);
     assertEquals(1, result.size());
     assertEquals(InstrumentType.TUBA, result.get(0).getInstrumentType());
    }

    @Test
    void findBySerialNumber() throws DataAccessException {
     Instrument result = repository.findBySerialNumber(existingSerialNumber);
     assertNotNull(result);
     assertEquals(existingSerialNumber, result.getSerialNumber());
    }

    @Test
    void add() throws DataAccessException {
     Instrument instrumentToAdd = new Instrument("asdfasdf", InstrumentType.TRUMPET, 200, "Billy", false);

     repository.add(instrumentToAdd);

     assertEquals(startingSize + 1, repository.findAll().size());
    }

    @Test
    void update() throws DataAccessException {
  int newCost = 600;
  Instrument instrumentToUpdate = baseline;
  instrumentToUpdate.setCost(newCost);
  instrumentToUpdate.setSerialNumber(existingSerialNumber);

     boolean result = repository.update(instrumentToUpdate);

     assertTrue(result);
     assertEquals(newCost, repository.findBySerialNumber(existingSerialNumber).getCost());
    }

    @Test
    void deleteBySerialNumber() throws DataAccessException {
     boolean result = repository.deleteBySerialNumber(existingSerialNumber);


     assertEquals(startingSize - 1, repository.findAll().size());
    }
}