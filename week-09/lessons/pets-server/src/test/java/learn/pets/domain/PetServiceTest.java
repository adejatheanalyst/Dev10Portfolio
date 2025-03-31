package learn.pets.domain;

import learn.pets.data.PetRepository;
import learn.pets.models.Pet;
import learn.pets.models.PetType;
import learn.pets.models.VaccinationStatus;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.time.LocalDate;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.NONE)
class PetServiceTest {
    private final int NOT_FOUND_ID = 99999;
    private final int VALID_ID = 1;
    @Autowired
    PetService service;

    @MockBean
    PetRepository repository;

    @Test
    void shouldFindAll() {
        List<Pet> expected = List.of(makePet());
        when(repository.findAll()).thenReturn(expected);

        List<Pet> actual = service.findAll();
        assertNotNull(actual);
        assertEquals(expected, actual);
    }

    @Test
    void shouldFindById() {
        Pet expected = makePet();
        when(repository.findById(VALID_ID)).thenReturn(expected);
        Pet actual = service.findById(VALID_ID);
        assertEquals(expected, actual);
    }

    @Test
    void shouldNotFindByNonexistentId() {
        Pet actual = service.findById(NOT_FOUND_ID);
        assertNull(actual);
    }

    @Test
    void shouldAddWhenValid() {
        Pet arg = makePet(); // petId = 0
        Pet expected = makePet();
        expected.setPetId(VALID_ID);
        when(repository.add(arg)).thenReturn(expected);

        Result<Pet> result = service.add(arg);
        assertEquals(ResultType.SUCCESS, result.getType());
        assertEquals(expected, result.getPayload());
    }

    @Test
    void shouldNotAddWhenInvalid() {
        // Should not add null
        Result<Pet> result = service.add(null);
        assertEquals(ResultType.INVALID, result.getType());

        // Should not add when petId is not 0
        Pet pet = makePet();
        pet.setPetId(VALID_ID);
        result = service.add(pet);
        assertEquals(ResultType.INVALID, result.getType());
        assertEquals("Pet id cannot be set for `add` operation", result.getMessages().get(0));

        // Should not add null or blank name
        pet.setName(null);
        result = service.add(pet);
        assertEquals(ResultType.INVALID, result.getType());
        assertEquals("Name is required", result.getMessages().get(0));

        pet.setName("");
        result = service.add(pet);
        assertEquals(ResultType.INVALID, result.getType());
        assertEquals("Name is required", result.getMessages().get(0));

        // Should not add null type
        pet = makePet();
        pet.setType(null);
        result = service.add(pet);
        assertEquals(ResultType.INVALID, result.getType());
        assertEquals("Type is required", result.getMessages().get(0));

        // Should not add null vaccination status
        pet = makePet();
        pet.setVaccinationStatus(null);
        result = service.add(pet);
        assertEquals(ResultType.INVALID, result.getType());
        assertEquals("Vaccination status is required", result.getMessages().get(0));

        // Should not add future DOB
        pet = makePet();
        pet.setDob(getOneYearFromToday());
        result = service.add(pet);
        assertEquals(ResultType.INVALID, result.getType());
        assertEquals("DOB cannot be in the future", result.getMessages().get(0));


        // Should not add duplicate pet (same name, breed, type, and DOB)
        Pet existingPet = makePet();
        existingPet.setPetId(VALID_ID);
        List<Pet> pets = List.of(existingPet);
        when(repository.findAll()).thenReturn(pets);
        pet = makePet();
        result = service.add(pet);
        assertEquals(ResultType.INVALID, result.getType());
        assertEquals("Cannot create a pet with the same name, breed, type, and DOB", result.getMessages().get(0));
    }

    @Test
    void shouldUpdateWhenValid() {
        Pet pet = makePet();
        pet.setPetId(VALID_ID);
        when(repository.update(pet)).thenReturn(true);

        Result<Pet> result = service.update(pet);
        assertEquals(ResultType.SUCCESS, result.getType());
    }

    @Test
    void shouldNotUpdateWhenInvalid() {
        // Should not update null pet
        Result<Pet> result = service.update(null);
        assertEquals(ResultType.INVALID, result.getType());

        // Should not update pet with petId <= 0
        Pet pet = makePet(); // petId = 0
        result = service.update(pet);
        assertEquals(ResultType.INVALID, result.getType());
        assertEquals("Pet id must be set for `update` operation", result.getMessages().get(0));

        // Should not update when pet not found
        pet.setPetId(NOT_FOUND_ID);
        when(repository.update(pet)).thenReturn(false); // Not strictly necessary, being explicit about expectations
        result = service.update(pet);
        assertEquals(ResultType.NOT_FOUND, result.getType());
        assertEquals(String.format("Pet id: %s, not found", NOT_FOUND_ID), result.getMessages().get(0));

        // Should not update with null or blank name
        pet.setName(null);
        result = service.update(pet);
        assertEquals(ResultType.INVALID, result.getType());
        assertEquals("Name is required", result.getMessages().get(0));

        pet.setName("");
        result = service.update(pet);
        assertEquals(ResultType.INVALID, result.getType());
        assertEquals("Name is required", result.getMessages().get(0));

        // Should not update with null type
        pet = makePet();
        pet.setType(null);
        result = service.update(pet);
        assertEquals(ResultType.INVALID, result.getType());
        assertEquals("Type is required", result.getMessages().get(0));

        // Should not update with null vaccination status
        pet = makePet();
        pet.setVaccinationStatus(null);
        result = service.update(pet);
        assertEquals(ResultType.INVALID, result.getType());
        assertEquals("Vaccination status is required", result.getMessages().get(0));

        // Should not update with future DOB
        pet = makePet();
        pet.setDob(getOneYearFromToday());
        result = service.update(pet);
        assertEquals(ResultType.INVALID, result.getType());
        assertEquals("DOB cannot be in the future", result.getMessages().get(0));

        // Should not update to duplicate (same name, breed, type and DOB)
        Pet existingPet = makePet();
        existingPet.setPetId(1);
        List<Pet> pets = List.of(existingPet);
        when(repository.findAll()).thenReturn(pets);
        pet = makePet();
        result = service.update(pet);
        assertEquals(ResultType.INVALID, result.getType());
        assertEquals("Cannot create a pet with the same name, breed, type, and DOB", result.getMessages().get(0));
    }

    @Test
    void shouldDelete() {
        Pet pet = makePet();
        pet.setPetId(1);
        when(repository.deleteById(1)).thenReturn(true);

        Result<Pet> result = service.deleteById(1);
        assertEquals(ResultType.SUCCESS, result.getType());
    }

    @Test
    void shouldNotDeleteWhenNotFound() {
        Pet pet = makePet();
        pet.setPetId(NOT_FOUND_ID);
        when(repository.deleteById(NOT_FOUND_ID)).thenReturn(false); // Again, being explicit about expectations

        Result<Pet> result = service.deleteById(NOT_FOUND_ID);
        assertEquals(ResultType.NOT_FOUND, result.getType());
        assertEquals(String.format("Pet id: %s, not found", NOT_FOUND_ID), result.getMessages().get(0));
    }

    private Pet makePet() {
        return new Pet("Potato", PetType.CAT, VaccinationStatus.NOT_UP_TO_DATE, "Siamese Cat",
                LocalDate.of(2022, 7, 31), false, "https://en.wikipedia.org/wiki/Siamese_cat#/media/File" +
                ":Siam_lilacpoint.jpg");
    }

    private LocalDate getOneYearFromToday() {
        return LocalDate.now().plusYears(1);
    }
}