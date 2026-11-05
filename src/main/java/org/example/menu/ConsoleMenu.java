package org.example.menu;

import org.example.model.*;
import org.example.shelter.Shelter;

import java.util.List;
import java.util.Scanner;

public class ConsoleMenu {
    private final Shelter<Animal> shelter;
    private final Scanner scanner = new Scanner(System.in);

    public ConsoleMenu(Shelter<Animal> shelter) {
        this.shelter = shelter;
    }

    public void start() {
        boolean running = true;

        while (running) {
            printMenu();
            String input = scanner.nextLine().trim();

            switch (input) {
                case "1" -> addAnimal();
                case "2" -> listAnimals();
                case "3" -> findAnimal();
                case "4" -> listAvailable();
                case "5" -> markAnimal();
                case "0" -> {
                    System.out.println("Program stopped, exiting...");
                    running = false;
                }
                default -> System.out.println("Incorrect data input. Please try again.");
            }
            System.out.println();
        }
    }

    private void printMenu() {
        System.out.println("""
                === Animal Shelter Menu ===
                1. Add animal
                2. List all animals
                3. Find animals by species
                4. List available animals
                5. Mark animal as adopted
                0. Exit""");
    }

    private void addAnimal() {
        System.out.println("\n--- Add Animal ---");

        System.out.println("What is the animal's species? (dog, cat, bird):");
        String species = scanner.nextLine().trim().toLowerCase();

        System.out.println("What is the animal's name?");
        String name = scanner.nextLine().trim();

        System.out.println("What is the animal's age?");
        int age;
        try {
            age = Integer.parseInt(scanner.nextLine().trim());
        } catch (NumberFormatException e) {
            System.out.println("Invalid age entered. Defaulting to 0.");
            age = 0;
        }

        switch (species) {
            case "dog" -> shelter.addAnimal(new Dog(new AnimalId(), name, age));
            case "cat" -> shelter.addAnimal(new Cat(new AnimalId(), name, age));
            case "bird" -> shelter.addAnimal(new Bird(new AnimalId(), name, age));
            default -> {
                System.out.println("Unknown species! Animal not added.");
                return;
            }
        }

        System.out.println("Successfully added an animal.");
    }
    private void listAnimals() {
        System.out.println("\n--- All Animals in Shelter ---");
        for (Animal animal : shelter.getAllAnimals()) {
            System.out.println(animal);
        }
    }

    private void findAnimal() {
        System.out.println("\n--- Find Animals by Species ---");
        System.out.println("Enter species to search for (dog, cat, bird):");
        String species = scanner.nextLine().trim();

        List<Animal> foundAnimals = shelter.findBySpecies(species);

        if (foundAnimals.isEmpty()) {
            System.out.println("No animals found for species: " + species);
        } else {
            System.out.println("Found animals:");
            for (Animal animal : foundAnimals) {
                System.out.println(animal);
            }
        }
    }

    private void listAvailable() {
        System.out.println("Listing available animals... (Implement shelter method)");
    }

    private void markAnimal() {
        System.out.println("Marking animal as adopted... (Implement shelter method)");
    }
}