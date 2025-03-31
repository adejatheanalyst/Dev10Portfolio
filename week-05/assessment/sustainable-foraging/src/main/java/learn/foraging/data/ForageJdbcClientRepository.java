package learn.foraging.data;

import learn.foraging.models.Forage;
import org.springframework.jdbc.core.simple.JdbcClient;
import org.springframework.jdbc.support.GeneratedKeyHolder;

import java.time.LocalDate;
import java.util.List;

public class ForageJdbcClientRepository implements ForageRepository {

    private final JdbcClient jdbcClient;

    public ForageJdbcClientRepository(JdbcClient jdbcClient) {
        this.jdbcClient = jdbcClient;
    }

    @Override
    public Forage findById(int forageId) {
        final String sql = """
                select f.forage_id, f.`date`, f.amount,
                    fr.forager_id, fr.first_name, fr.last_name, fr.state_abbr,
                    i.item_id, i.`name`, i.category, i.dollars_per_kilogram
                from forage f
                inner join forager fr on f.forager_id = fr.forager_id
                inner join item i on f.item_id = i.item_id
                where f.forage_id = ?
                order by i.`name` asc, fr.last_name asc;
                """;
        return jdbcClient.sql(sql)
                .param(forageId)
                .query(new ForageMapper())
                .optional().orElse(null);
    }

    @Override
    public List<Forage> findByDate(LocalDate date) {
        final String sql = """
                select f.forage_id, f.`date`, f.amount,
                    fr.forager_id, fr.first_name, fr.last_name, fr.state_abbr,
                    i.item_id, i.`name`, i.category, i.dollars_per_kilogram
                from forage f
                inner join forager fr on f.forager_id = fr.forager_id
                inner join item i on f.item_id = i.item_id
                where f.date = ?
                order by i.`name` asc, fr.last_name asc;
                """;
        return jdbcClient.sql(sql)
                .param(date)
                .query(new ForageMapper())
                .list();
    }

    @Override
    public Forage add(Forage forage) {

        final String sql = """
                insert into forage (`date`, amount, forager_id, item_id)
                values (:date, :amount, :forager_id, :item_id);
                """;

        GeneratedKeyHolder keyHolder = new GeneratedKeyHolder();

        jdbcClient.sql(sql)
                .param("date", forage.getDate())
                .param("amount", forage.getKilograms())
                .param("forager_id", forage.getForager().getId())
                .param("item_id", forage.getItem().getId())
                .update(keyHolder, "forage_id");

        int forageId = keyHolder.getKey().intValue();
        forage.setId(forageId);

        return forage;
    }

    @Override
    public boolean update(Forage forage) {
        return false;
    }
}
