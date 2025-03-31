package learn.pets.models;

import java.time.LocalDate;
import java.util.Objects;

public class Pet {
    private int petId;
    private String name;
    private PetType type;
    private VaccinationStatus vaccinationStatus;
    private String breed;
    private LocalDate dob;
    private boolean adopted;
    private String imageUrl;

    public Pet() {
    }

    public Pet(String name, PetType type, VaccinationStatus vaccinationStatus, String breed, LocalDate dob, boolean adopted, String imageUrl) {
        this.name = name;
        this.type = type;
        this.vaccinationStatus = vaccinationStatus;
        this.breed = breed;
        this.dob = dob;
        this.adopted = adopted;
        this.imageUrl = imageUrl;
    }

    public int getPetId() {
        return petId;
    }

    public void setPetId(int petId) {
        this.petId = petId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public PetType getType() {
        return type;
    }

    public void setType(PetType type) {
        this.type = type;
    }

    public VaccinationStatus getVaccinationStatus() {
        return vaccinationStatus;
    }

    public void setVaccinationStatus(VaccinationStatus vaccinationStatus) {
        this.vaccinationStatus = vaccinationStatus;
    }

    public String getBreed() {
        return breed;
    }

    public void setBreed(String breed) {
        this.breed = breed;
    }

    public LocalDate getDob() {
        return dob;
    }

    public void setDob(LocalDate dob) {
        this.dob = dob;
    }

    public boolean isAdopted() {
        return adopted;
    }

    public void setAdopted(boolean adopted) {
        this.adopted = adopted;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Pet pet = (Pet) o;
        return petId == pet.petId && adopted == pet.adopted && Objects.equals(name, pet.name) && type == pet.type && vaccinationStatus == pet.vaccinationStatus && Objects.equals(breed, pet.breed) && Objects.equals(dob, pet.dob) && Objects.equals(imageUrl, pet.imageUrl);
    }

    @Override
    public int hashCode() {
        return Objects.hash(petId, name, type, vaccinationStatus, breed, dob, adopted, imageUrl);
    }

    @Override
    public String toString() {
        return "Pet{" +
                "id=" + petId +
                ", name='" + name + '\'' +
                ", type=" + type +
                ", vaccinationStatus=" + vaccinationStatus +
                ", breed='" + breed + '\'' +
                ", dob=" + dob +
                ", adopted=" + adopted +
                ", imageUrl='" + imageUrl + '\'' +
                '}';
    }
}
