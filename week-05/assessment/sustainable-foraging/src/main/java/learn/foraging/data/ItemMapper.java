package learn.foraging.data;

import learn.foraging.models.Category;
import learn.foraging.models.Item;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class ItemMapper implements RowMapper<Item> {
    @Override
    public Item mapRow(ResultSet rs, int rowNum) throws SQLException {
        Item item = new Item();
        item.setId(rs.getInt("item_id"));
        item.setName(rs.getString("name"));
        item.setCategory(Category.valueOf(rs.getString("category")));
        item.setDollarPerKilogram(rs.getBigDecimal("dollars_per_kilogram"));
        return item;
    }
}
