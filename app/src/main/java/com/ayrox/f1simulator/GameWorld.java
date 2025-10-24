package com.ayrox.f1simulator;

import java.util.ArrayList;
import java.util.List;

public class GameWorld {

    private static GameWorld instance;

    public static GameWorld getInstance() {
        if (instance == null) {
            instance = new GameWorld();
        }
        return instance;
    }

    int currentYear;

    List<Driver> allDrivers;
    List<Team> allTeams;
    List<TechnicalChief> allTechnicalChiefs;

    // NOUVEAU : Le constructeur de la classe
    private GameWorld() {
        this.currentYear = 2025;
        this.allTeams = new ArrayList<>();
        this.allDrivers = new ArrayList<>();
        this.allTechnicalChiefs = new ArrayList<>();

        // --- CRÉATION DES PILOTES (Maintenant beaucoup plus simple !) ---
        // Génère 2 pilotes "star" avec des stats entre 85 et 99
        Driver driver1 = new Driver(85, 99);
        Driver driver2 = new Driver(83, 98);

        // Génère 2 pilotes "milieu de grille" avec des stats entre 70 et 85
        Driver driver3 = new Driver(70, 85);
        Driver driver4 = new Driver(72, 84);

        // --- CRÉATION DES ÉCURIES (Inchangé, mais on utilise les pilotes générés) ---
        Team teamA = new Team();
        teamA.name = "Écurie A";
        teamA.drivers = new ArrayList<>();
        teamA.drivers.add(driver1);
        teamA.drivers.add(driver3);
        // On met des stats de voiture pour le test
        teamA.aeroPerformance = 90;
        teamA.enginePerformance = 95;
        teamA.chassisPerformance = 88;
        teamA.reliability = 92;

        Team teamB = new Team();
        teamB.name = "Écurie B";
        teamB.drivers = new ArrayList<>();
        teamB.drivers.add(driver2);
        teamB.drivers.add(driver4);
        // Stats de voiture
        teamB.aeroPerformance = 92;
        teamB.enginePerformance = 93;
        teamB.chassisPerformance = 90;
        teamB.reliability = 90;

        // On ajoute tout au monde
        this.allTeams.add(teamA);
        this.allTeams.add(teamB);
        this.allDrivers.add(driver1);
        this.allDrivers.add(driver2);
        this.allDrivers.add(driver3);
        this.allDrivers.add(driver4);
    }

    // --- NOUVEAU : Une méthode pratique ---
    public Team findTeamByName(String name) {
        for (Team team : allTeams) {
            if (team.name.equals(name)) {
                return team;
            }
        }
        return null; // Si on ne trouve pas l'écurie
    }
    // On ajoutera plus tard les pilotes à la retraite, les jeunes qui attendent, etc.
    // List<Driver> retiredDrivers;
    // List<Driver> youngDriversPool;

}
