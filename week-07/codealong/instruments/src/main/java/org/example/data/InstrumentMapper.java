package org.example.data;

import org.example.models.Instrument;
import org.example.models.InstrumentType;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class InstrumentMapper implements RowMapper<Instrument> {
    @Override
    public Instrument mapRow(ResultSet rs, int rowNum) throws SQLException {
        String instrumentTypeString = rs.getString("instrumentType");
        InstrumentType instrumentType = InstrumentType.valueOf(instrumentTypeString);

        return new Instrument(
                rs.getString("serialNumber"),
                instrumentType,
                rs.getInt("cost"),
                rs.getString("student"),
                rs.getBoolean("needsRepair")
        );
    }
}
