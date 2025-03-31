package learn.pets.data.mappers;


import learn.pets.models.Pet;
import learn.pets.models.PetType;
import learn.pets.models.VaccinationStatus;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class PetMapper implements RowMapper<Pet> {


    @Override
    public Pet mapRow(ResultSet resultSet, int i) throws SQLException {
        Pet pet = new Pet();
        pet.setPetId(resultSet.getInt("pet_id"));
        PetType type = PetType.valueOf(resultSet.getString("pet_type"));
        pet.setType(type);
        VaccinationStatus vs = VaccinationStatus.valueOf(resultSet.getString("vaccination_status"));
        pet.setVaccinationStatus(vs);
        pet.setBreed(resultSet.getString("breed"));
        pet.setAdopted(resultSet.getBoolean("adopted"));
        if (resultSet.getDate("dob") != null) {
            pet.setDob(resultSet.getDate("dob").toLocalDate());
        }
        pet.setName(resultSet.getString("name"));
        pet.setImageUrl(resultSet.getString("image_url"));
        return pet;
    }
}
