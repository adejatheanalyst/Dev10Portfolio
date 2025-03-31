package learn.pets.data;

import learn.pets.data.mappers.PetMapper;
import learn.pets.models.Pet;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import java.sql.PreparedStatement;
import java.sql.Statement;
import java.util.List;
import java.sql.Date;

@Repository
public class PetJdbcTemplateRepository implements PetRepository{
    private final JdbcTemplate jdbcTemplate;
    public PetJdbcTemplateRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }
    @Override
    public List<Pet> findAll() {
        final String sql = "select * from pet limit 1000;";
        return jdbcTemplate.query(sql, new PetMapper());
    }

    @Override
    public Pet findById(int petId) {
        final String sql = "select * from pet where pet_id = ?;";
        return jdbcTemplate.query(sql, new PetMapper(), petId).stream().findFirst().orElse(null);
    }

    @Override
    public Pet add(Pet pet) {
        final String sql = "insert into pet (`name`, pet_type, breed, dob, adopted, vaccination_status, image_url) " +
                "values (?,?,?,?,?,?,?);";
        KeyHolder keyHolder = new GeneratedKeyHolder();
        int rowsAffected = jdbcTemplate.update(connection -> {
            PreparedStatement ps = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            ps.setString(1, pet.getName());
            ps.setString(2, pet.getType().toString());
            ps.setString(3, pet.getBreed());
            ps.setDate(4, pet.getDob() == null ? null : Date.valueOf(pet.getDob()));
            ps.setBoolean(5, pet.isAdopted());
            ps.setString(6, pet.getVaccinationStatus().toString());
            ps.setString(7, pet.getImageUrl());

            return ps;
        }, keyHolder);

        if (rowsAffected <= 0) {
            return null;
        }

        pet.setPetId(keyHolder.getKey().intValue());
        return pet;
    }

    @Override
    public boolean update(Pet pet) {
        final String sql = "update pet set "
                + "`name` = ?, "
                + "pet_type = ?, "
                + "breed = ?, "
                + "dob = ?, "
                + "adopted = ?, "
                + "image_url = ?, "
                + "vaccination_status = ? "
                + "where pet_id = ?;";
        return jdbcTemplate.update(sql,
                pet.getName(),
                pet.getType().toString(),
                pet.getBreed(),
                pet.getDob(),
                pet.isAdopted(),
                pet.getImageUrl(),
                pet.getVaccinationStatus().toString(),
                pet.getPetId()) > 0;
    }

    @Override
    public boolean deleteById(int id) {
        return jdbcTemplate.update("delete from pet where pet_id = ?;", id) > 0;
    }
}
