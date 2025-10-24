package com.ayrox.f1simulator;

import java.util.ArrayList;
import java.util.List;

public class GameWorld {

    // --- Singleton ---
    private static GameWorld instance;
    public static GameWorld getInstance() {
        if (instance == null) {
            instance = new GameWorld();
        }
        return instance;
    }

    // --- Données du monde ---
    int currentYear;
    List<Driver> allDrivers;
    List<Team> allTeams;
    List<TechnicalChief> allTechnicalChiefs;
    List<Track> allTracks; // <-- NOUVEAU

    // Constructeur privé (Singleton)
    private GameWorld() {
        this.currentYear = 2025;
        this.allTeams = new ArrayList<>();
        this.allDrivers = new ArrayList<>();
        this.allTechnicalChiefs = new ArrayList<>();
        this.allTracks = new ArrayList<>();

        // --- NOUVEAU : Création des circuits ---
        // Un circuit rapide (type Monza)
        Track monza = new Track("Monza", 0.2, 0.6, 0.2); // 60% Moteur
        // Un circuit technique (type Monaco)
        Track monaco = new Track("Monaco", 0.3, 0.1, 0.6); // 60% Châssis
        this.allTracks.add(monza);
        this.allTracks.add(monaco);


        // --- CRÉATION DES PILOTES (avec ton constructeur aléatoire) ---
        Driver driver1 = new Driver(85, 99); // Pilote star 1
        Driver driver2 = new Driver(83, 98); // Pilote star 2
        Driver driver3 = new Driver(70, 85); // Milieu de grille 1
        Driver driver4 = new Driver(72, 84); // Milieu de grille 2

        this.allDrivers.add(driver1);
        this.allDrivers.add(driver2);
        this.allDrivers.add(driver3);
        this.allDrivers.add(driver4);

        // --- CRÉATION DES ÉCURIES ---
        Team teamA = new Team();
        teamA.name = "Écurie A (Performante Moteur)";
        teamA.drivers = new ArrayList<>();
        teamA.drivers.add(driver1);
        teamA.drivers.add(driver3);
        teamA.enginePerformance = 95; // Point fort
        teamA.aeroPerformance = 85;
        teamA.chassisPerformance = 80; // Point faible
        teamA.reliability = 90;

        Team teamB = new Team();
        teamB.name = "Écurie B (Performante Châssis)";
        teamB.drivers = new ArrayList<>();
        teamB.drivers.add(driver2);
        teamB.drivers.add(driver4);
        teamB.enginePerformance = 85; // Point faible
        teamB.aeroPerformance = 88;
        teamB.chassisPerformance = 95; // Point fort
        teamB.reliability = 90;

        this.allTeams.add(teamA);
        this.allTeams.add(teamB);
    }

    // --- Méthodes utilitaires ---
    public Team findTeamByName(String name) {
        for (Team team : allTeams) {
            if (team.name.equals(name)) {
                return team;
            }
        }
        return null;
    }

    // On ajoutera plus tard les pilotes à la retraite, les jeunes qui attendent, etc.
    // List<Driver> retiredDrivers;
    // List<Driver> youngDriversPool;
}
