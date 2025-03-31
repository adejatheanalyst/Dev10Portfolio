package org.example.data;

import org.example.models.Instrument;
import org.example.models.InstrumentType;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class InstrumentMapper implements RowMapper<Instrument> {
    @Override
    public Instrument mapRow(ResultSet rs, int rowNum) throws SQLException {
        Instrument instrument = new Instrument();
        instrument.setSerialNumber(rs.getString("serialNumber"));
        instrument.setInstrumentType(InstrumentType.valueOf(rs.getString("instrumentType")));
        instrument.setCost(rs.getInt("cost"));
        instrument.setStudent(rs.getString("student"));
        instrument.setNeedsRepair(rs.getBoolean("needsRepair"));
        return instrument;
    }


}
