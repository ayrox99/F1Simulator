package com.ayrox.f1simulator;

import java.util.ArrayList;
import java.util.List;

public class GameWorld {

    int currentYear;

    List<Driver> allDrivers;
    List<Team> allTeams;
    List<TechnicalChief> allTechnicalChiefs;

    // NOUVEAU : Le constructeur de la classe
    public GameWorld() {
        // On déplace tout le code de création ici
        this.currentYear = 2025;

        // On initialise nos listes pour éviter les erreurs
        this.allTeams = new ArrayList<>();
        this.allDrivers = new ArrayList<>();
        this.allTechnicalChiefs = new ArrayList<>();

        // Création de l'écurie Mercedes
        Team mercedes = new Team();
        mercedes.name = "Mercedes-AMG Petronas";
        mercedes.base = "Brackley, UK";
        mercedes.budget = 500000000.0;
        mercedes.drivers = new ArrayList<>();

        // Création du pilote Hamilton
        Driver hamilton = new Driver();
        hamilton.firstName = "Lewis";
        hamilton.lastName = "Hamilton";
        hamilton.age = 40;
        hamilton.currentSkills = new DriverSkills();
        hamilton.currentSkills.pace = 95;

        // Création de l'écurie Ferrari
        Team ferrari = new Team();
        ferrari.name = "Ferrari";
        ferrari.base = "Maranello";
        ferrari.budget = 500000000.0;
        ferrari.drivers = new ArrayList<>();

        // Création du pilote Leclerc
        Driver leclerc = new Driver();
        leclerc.firstName = "Charles";
        leclerc.lastName = "Leclerc";
        leclerc.age = 26;
        leclerc.currentSkills = new DriverSkills();
        leclerc.currentSkills.pace = 95;

        // On lie le pilote à l'écurie et on ajoute l'écurie au monde
        ferrari.drivers.add(leclerc);
        mercedes.drivers.add(hamilton);
        this.allTeams.add(ferrari);
        this.allTeams.add(mercedes);

    }
    // On ajoutera plus tard les pilotes à la retraite, les jeunes qui attendent, etc.
    // List<Driver> retiredDrivers;
    // List<Driver> youngDriversPool;

}
