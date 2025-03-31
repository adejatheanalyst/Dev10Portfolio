package learn.foraging.data;

import learn.foraging.models.Category;
import learn.foraging.models.Forage;
import learn.foraging.models.Forager;
import learn.foraging.models.Item;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class ForageMapper implements RowMapper<Forage> {
    @Override
    public Forage mapRow(ResultSet rs, int rowNum) throws SQLException {
        Item item = new Item();
        item.setId(rs.getInt("item_id"));
        item.setName(rs.getString("name"));
        item.setCategory(Category.valueOf(rs.getString("category")));
        item.setDollarPerKilogram(rs.getBigDecimal("dollars_per_kilogram"));

        Forager forager = new Forager();
        forager.setId(rs.getInt("forager_id"));
        forager.setFirstName(rs.getString("first_name"));
        forager.setLastName(rs.getString("last_name"));
        forager.setState(rs.getString("state_abbr"));

        Forage forage = new Forage();
        forage.setId(rs.getInt("forage_id"));
        forage.setDate(rs.getDate("date").toLocalDate());
        forage.setKilograms(rs.getBigDecimal("amount"));
        forage.setItem(item);
        forage.setForager(forager);
        return forage;
    }
}
