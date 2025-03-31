package learn.fund.data;

import learn.fund.models.Backer;

import java.util.List;

import static learn.fund.TestHelper.makeBacker;

public class BackerRepositoryDouble implements BackerRepository {

    @Override
    public Backer findById(int backerId) {
        if (backerId == 1) {
            return makeBacker(1);
        }
        return null;
    }

    @Override
    public List<Backer> findNameContains(String snippet) {
        if (snippet != null && snippet.equals("ack")) {
            return List.of(makeBacker(1));
        }
        return List.of();
    }

    @Override
    public Backer findByEmailAddress(String emailAddress) {
        if (emailAddress != null && emailAddress.equalsIgnoreCase("backer1@example.com")) {
            return makeBacker(1);
        }
        return null;
    }

    @Override
    public Backer add(Backer backer) {
        return makeBacker(1);
    }

    @Override
    public boolean update(Backer backer) {
        return backer.getBackerId() == 1;
    }

    @Override
    public boolean deleteById(int backerId) {
        return backerId == 1;
    }
}
