package learn.fund.data;

import learn.fund.models.Backer;

import java.util.List;

public interface BackerRepository {
    Backer findById(int backerId);

    List<Backer> findNameContains(String snippet);

    Backer findByEmailAddress(String emailAddress);

    Backer add(Backer backer);

    boolean update(Backer backer);

    boolean deleteById(int backerId);
}
