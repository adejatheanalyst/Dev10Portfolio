package org.example.data;

import org.example.models.Instrument;
import org.example.models.InstrumentType;
import org.springframework.jdbc.core.simple.JdbcClient;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;

import java.util.List;

public class InstrumentJdbcClientRepository implements InstrumentRepository {

    final JdbcClient client;

    final String BASIC_SELECT = "SELECT serial_number, `type`, cost, student, needs_repair FROM instruments";

    public InstrumentJdbcClientRepository(JdbcClient client) {
        this.client = client;
    }

    @Override
    public List<Instrument> findAll() throws DataAccessException {
        final String sql = BASIC_SELECT;

        return client.sql(sql)
                .query(new InstrumentMapper()) // new Instrument(eachRow)
                .list();
    }

    @Override
    public List<Instrument> findByType(InstrumentType instrumentType) throws DataAccessException {
        final String sql = BASIC_SELECT + " WHERE `type` = ?";

        return client.sql(sql)
                .param(instrumentType.toString())
                .query(new InstrumentMapper())
                .list();
    }

    @Override
    public Instrument findBySerialNumber(String serialNumber) throws DataAccessException {
        final String sql = BASIC_SELECT + " WHERE serial_number = ?";

        return client.sql(sql)
                .param(serialNumber)
                .query(new InstrumentMapper())
                .optional().orElse(null);
    }

    @Override
    public Instrument add(Instrument instrument) throws DataAccessException {
        final String sql = """
                INSERT INTO instruments (serial_number, `type`, cost, student, needs_repair)
                VALUES (:serial_number, :type, :cost, :student, :needs_repair);
                """;

        KeyHolder keyHolder = new GeneratedKeyHolder();
        client.sql(sql)
                .param("serial_number", instrument.getSerialNumber())
                .param("type", instrument.getInstrumentType().toString())
                .param("cost", instrument.getCost())
                .param("student", instrument.getStudent())
                .param("needs_repair", instrument.isNeedsRepair())
                .update(keyHolder, "instrument_id");

        int instrumentId = keyHolder.getKey().intValue();
        // TODO
        // instrument.setInstrumentId(instrumentId);
        return instrument;
    }

    @Override
    public boolean update(Instrument instrument) throws DataAccessException {
        final String sql = """
                UPDATE instruments SET
                cost = :cost,
                student = :student,
                needs_repair = :needs_repair
                WHERE serial_number = :serial_number
                """;
        // TODO: update based on primary key int, not serial number

        int rowsAffected = client.sql(sql)
                .param("cost", instrument.getCost())
                .param("student", instrument.getStudent())
                .param("needs_repair", instrument.isNeedsRepair())
                .param("serial_number", instrument.getSerialNumber())
                .update();

        return rowsAffected == 1;
//        if (rowsAffected == 0) {
//            return false;
//        } else if (rowsAffected == 1) {
//            return true;
//        } else {
//            throw new DataAccessException("Somehow multiple rows were updated?!");
//        }
    }

    @Override
    public boolean deleteBySerialNumber(String serialNumber) throws DataAccessException {
        final String sql = "DELETE FROM instruments WHERE serial_number = ?";

        int rowsAffected = client.sql(sql)
                .param(serialNumber)
                .update();

        return rowsAffected == 1;
    }
}
