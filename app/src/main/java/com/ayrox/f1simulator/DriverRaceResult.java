package com.ayrox.f1simulator;

/**
 * Une classe simple pour stocker le résultat d'un pilote pour une seule course.
 */
public class DriverRaceResult {

    Driver driver;
    int finishingPosition;
    // On pourra ajouter plus tard :
    // String status; // "Finished", "Crashed", "Engine Failure"
    // int lapsCompleted;
    // boolean fastestLap;

    // Constructeur
    public DriverRaceResult(Driver driver, int finishingPosition) {
        this.driver = driver;
        this.finishingPosition = finishingPosition;
    }
}
