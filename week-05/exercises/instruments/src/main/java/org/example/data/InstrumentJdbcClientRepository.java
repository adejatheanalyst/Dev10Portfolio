package org.example.data;

import org.example.models.Instrument;
import org.example.models.InstrumentType;
import org.springframework.jdbc.core.simple.JdbcClient;
import org.springframework.jdbc.support.GeneratedKeyHolder;

import java.sql.ClientInfoStatus;
import java.util.List;

import static com.sun.tools.attach.VirtualMachine.list;

public class InstrumentJdbcClientRepository implements InstrumentRepository {

  JdbcClient jdbcClient;
  final String selectAll =  "SELECT serialNumber, instrumentType, cost, student, needsRepair FROM instruments ";

    public InstrumentJdbcClientRepository(JdbcClient jdbcClient) {
        this.jdbcClient = jdbcClient;
    }


    @Override
    public List<Instrument> findAll() throws DataAccessException {
    final  String sql = selectAll;
       return jdbcClient.sql(sql)
                .query(new InstrumentMapper())
                .list();

    }

    @Override
    public List<Instrument> findByType(InstrumentType instrumentType) throws DataAccessException {
        final String sql = selectAll + " where instrumentType = ?";

        return jdbcClient.sql(sql)
                .param(instrumentType.toString())
                .query(new InstrumentMapper())
                .list();
    }

    @Override
    public Instrument findBySerialNumber(String serialNumber) throws DataAccessException {
        final String sql = selectAll + " where serialNumber = ? ";

        return jdbcClient.sql(sql)
                .param(serialNumber)
                .query(new InstrumentMapper())
                .optional().orElse(null);
    }

    @Override
    public Instrument add(Instrument instrument) throws DataAccessException {
        final String sql = """
        insert into instruments (serialNumber, instrumentType, cost, student, needsRepair) 
        values (:serialNumber, :instrumentType, :cost, :student, :needsRepair)
        """;
        GeneratedKeyHolder keyHolder = new GeneratedKeyHolder();
        jdbcClient.sql(sql)
                .param("serialNumber",instrument.getSerialNumber())
                .param("instrumentType",instrument.getInstrumentType().toString())
                .param("cost",instrument.getCost())
                .param("student", instrument.getStudent())
                .param("needsRepair",instrument.isNeedsRepair())
                .update(keyHolder, "instrument_id");
        int instrumentId = keyHolder.getKey().intValue();
        instrument.setInstrumentId(instrumentId);
        return instrument;

    }

    @Override
    public boolean update(Instrument instrument) throws DataAccessException {
        final String sql = """
        update instruments
        set cost = :cost,
        student = :student,
        needsRepair = :needsRepair
        where serialNumber = :serialNumber
        """;
        return jdbcClient.sql(sql)
                .param("cost",instrument.getCost())
                .param("student", instrument.getStudent())
                .param("needsRepair",instrument.isNeedsRepair())
                .param("serialNumber",instrument.getSerialNumber())
                .update() > 0;
    }

    @Override
    public boolean deleteBySerialNumber(String serialNumber) throws DataAccessException {
        final String sql = "delete from instruments where serialNumber = ?";


        return jdbcClient.sql(sql)
                .param( serialNumber)
                .update() > 0;
    }
}
