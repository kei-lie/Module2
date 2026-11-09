package org.example.shelter;

import org.example.model.AdoptionStatus;
import org.example.model.Animal;

import java.util.ArrayList;
import java.util.List;

public class Shelter <T extends Animal>{
    private final List<T> animals = new ArrayList<>();

    public void addAnimal(T animal){
        animals.add(animal);
    }

    public List<T> getAllAnimals(){
        return new ArrayList<>(animals);
    }

    public List<T> findBySpecies(String species){
        List<T> result = new ArrayList<>();
        for (T animal : animals) {

            if (animal.getClass().getSimpleName().equalsIgnoreCase(species)) {
                result.add(animal);
            }
        }
        return result;
    }

    public List<T> findAvailableAnimals() {
        return animals.stream()
                .filter(animal -> animal.getAdoptionStatus() == AdoptionStatus.AVAILABLE)
                .toList();
    }

    public void markAsAdopted(String id) {
        for (T animal : animals) {

            if (animal.getId().toString().equals(id)) {

                System.out.println("Successfully marked " + animal.getName() + " as adopted!");
                animal.markAsAdopted();
                return;
            }
        }
        System.out.println("Animal with ID " + id + " not found.");
    }
}
