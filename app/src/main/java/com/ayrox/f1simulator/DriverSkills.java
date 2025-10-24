package com.ayrox.f1simulator;

// Import nécessaire pour la génération de nombres aléatoires
import java.util.concurrent.ThreadLocalRandom;

public class DriverSkills {
    int pace;
    int consistency;
    int overtaking;
    int defending;
    int tyreManagement;
    int wetWeather;
    int composure;
    int developmentSkill;

    /**
     * Constructeur qui initialise toutes les compétences du pilote avec une valeur
     * aléatoire comprise entre min (inclus) et max (inclus).
     *
     * @param min La valeur minimale possible (incluse).
     * @param max La valeur maximale possible (incluse).
     */
    public DriverSkills(int min, int max) {
        // La méthode nextInt(min, max) de ThreadLocalRandom a une borne supérieure
        // EXCLUSIVE.
        // Pour inclure 'max' dans les résultats possibles, nous devons utiliser 'max + 1'
        // comme borne supérieure.
        int upperBound = max + 1;

        // Génération d'une valeur aléatoire pour chaque champ
        this.pace = ThreadLocalRandom.current().nextInt(min, upperBound);
        this.consistency = ThreadLocalRandom.current().nextInt(min, upperBound);
        this.overtaking = ThreadLocalRandom.current().nextInt(min, upperBound);
        this.defending = ThreadLocalRandom.current().nextInt(min, upperBound);
        this.tyreManagement = ThreadLocalRandom.current().nextInt(min, upperBound);
        this.wetWeather = ThreadLocalRandom.current().nextInt(min, upperBound);
        this.composure = ThreadLocalRandom.current().nextInt(min, upperBound);
        this.developmentSkill = ThreadLocalRandom.current().nextInt(min, upperBound);
    }
}