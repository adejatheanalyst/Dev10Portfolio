package learn.rpg.data;

import learn.rpg.models.Name;

public class NameRepository extends BaseRepository<Name> {

    public NameRepository(String filePath) {
        super(filePath, 2, true);
    }

    @Override
    protected Name deserialize(String[] fields) {
        Name name = new Name();
        name.setFirstName(fields[0]);
        name.setLastName(fields[1]);
        return name;
    }
}
