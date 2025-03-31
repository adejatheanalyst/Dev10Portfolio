package learn.foraging.data;

import learn.foraging.models.Category;
import learn.foraging.models.Item;
import org.springframework.jdbc.core.simple.JdbcClient;
import org.springframework.jdbc.support.GeneratedKeyHolder;

import java.util.List;

public class ItemJdbcClientRepository implements ItemRepository {

    private final JdbcClient jdbcClient;

    public ItemJdbcClientRepository(JdbcClient jdbcClient) {
        this.jdbcClient = jdbcClient;
    }

    @Override
    public List<Item> findByCategory(Category category) {
        final String sql = """
                select item_id, `name`, category, dollars_per_kilogram
                from item
                where category = ?;
                """;
        return jdbcClient.sql(sql)
                .param(category.toString())
                .query(new ItemMapper())
                .list();
    }

    @Override
    public Item findById(int id) {
        final String sql = """
                select item_id, `name`, category, dollars_per_kilogram
                from item
                where item_id = ?;
                """;
        return jdbcClient.sql(sql)
                .param(id)
                .query(new ItemMapper())
                .optional().orElse(null);
    }

    @Override
    public Item add(Item item) {

        final String sql = """
                insert into item (`name`, category, dollars_per_kilogram)
                values (:name, :category, :dollars_per_kilogram);
                """;

        GeneratedKeyHolder keyHolder = new GeneratedKeyHolder();

        jdbcClient.sql(sql)
                .param("name", item.getName())
                .param("category", item.getCategory().toString())
                .param("dollars_per_kilogram", item.getDollarPerKilogram())
                .update(keyHolder, "item_id");

        int itemId = keyHolder.getKey().intValue();
        item.setId(itemId);

        return item;
    }
}
