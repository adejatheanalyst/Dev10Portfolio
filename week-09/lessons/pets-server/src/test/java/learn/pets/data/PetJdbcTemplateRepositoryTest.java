package learn.pets.data;

import learn.pets.models.Pet;
import learn.pets.models.PetType;
import learn.pets.models.VaccinationStatus;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDate;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.NONE)
class PetJdbcTemplateRepositoryTest {
    final static int NEXT_ID = 9;

    @Autowired
    PetJdbcTemplateRepository repository;

    @Autowired
    KnownGoodState knownGoodState;

    @BeforeEach
    void setup(){
        knownGoodState.set();
    }

    @Test
    void shouldFindAll() {
        List<Pet> pets = repository.findAll();
        assertNotNull(pets);
        assertTrue((pets.size() >= 7));
    }

    @Test
    void shouldFindById() {
        // Happy Path
        Pet pet = repository.findById(1);
        assertNotNull(pet);
        assertEquals(pet.getName(), "Jimmy");

        // Unhappy Path
        pet = repository.findById(NEXT_ID + 1);
        assertNull(pet);
    }


    @Test
    void add() {
        Pet pet = makePet();
        Pet actual = repository.add(pet);
        assertNotNull(actual);
    }

    @Test
    void update() {
        // Happy Path
        Pet pet = makePet();
        pet.setPetId(1);
        assertTrue(repository.update(pet));

        // Unhappy Path
        pet.setPetId(NEXT_ID + 1);
        assertFalse(repository.update(pet));

    }

    @Test
    void deleteById() {
        // Happy Path
        assertTrue(repository.deleteById(1));
        // Unhappy Path
        assertFalse(repository.deleteById(1));
    }

    private Pet makePet() {
        return new Pet("Potato", PetType.CAT, VaccinationStatus.NOT_UP_TO_DATE, "Siamese Cat",
                LocalDate.of(2022, 7, 31), false, "https://en.wikipedia.org/wiki/Siamese_cat#/media/File" +
                ":Siam_lilacpoint.jpg");
    }
}