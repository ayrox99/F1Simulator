package com.ayrox.f1simulator;

/**
 * Représente un circuit avec ses caractéristiques uniques
 * qui influencent la performance des voitures.
 */
public class Track {

    String name;

    // L'importance de chaque aspect de la voiture sur ce circuit.
    // La somme de ces trois facteurs devrait être 1.0
    double aeroFactor;
    double engineFactor;
    double chassisFactor;

    // On pourra ajouter plus tard :
    // double tyreWearFactor; // Usure des pneus
    // double overtakingDifficulty; // Difficulté de dépassement
    // double errorPossibility; // Propension aux erreurs sur ce circuit

    /**
     * Constructeur pour créer un nouveau circuit.
     *
     * @param name Nom du circuit (ex: "Monza")
     * @param aeroFactor Importance de l'aérodynamisme (ex: 0.2)
     * @param engineFactor Importance du moteur (ex: 0.6)
     * @param chassisFactor Importance du châssis (ex: 0.2)
     */
    public Track(String name, double aeroFactor, double engineFactor, double chassisFactor) {
        this.name = name;
        this.aeroFactor = aeroFactor;
        this.engineFactor = engineFactor;
        this.chassisFactor = chassisFactor;
    }
}
