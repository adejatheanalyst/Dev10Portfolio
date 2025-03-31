package learn.foraging.data;

import learn.foraging.models.Category;
import learn.foraging.models.Item;

import java.util.List;

import static learn.foraging.TestData.*;

public class ItemRepositoryDouble implements ItemRepository {

    @Override
    public List<Item> findByCategory(Category category) {
        return null;
    }

    @Override
    public Item findById(int id) {
        if (id == 1) {
            return EDIBLE;
        }
        return null;
    }

    @Override
    public Item add(Item item) {
        return EDIBLE;
    }
}
