package learn.pets.data;

import learn.pets.models.Pet;

import java.util.List;

public interface PetRepository {
    List<Pet> findAll();

    Pet findById(int id);

    Pet add(Pet pet);

    boolean update(Pet pet);

    boolean deleteById(int id);

}
