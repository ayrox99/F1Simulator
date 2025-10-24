package com.ayrox.f1simulator;

import android.util.Log;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class SeasonEngine {

    private static final String TAG = "SeasonEngine";

    // Le SeasonEngine "possède" un RaceEngine pour simuler les courses
    private RaceEngine raceEngine;

    // Le barème de points de la F1 (P1 à P10)
    private static final int[] POINTS_SYSTEM = {25, 18, 15, 12, 10, 8, 6, 4, 2, 1};

    public SeasonEngine() {
        this.raceEngine = new RaceEngine(); // On crée notre moteur de course
    }

    /**
     * Simule une saison complète.
     * Pour l'instant, affiche les résultats finaux dans le Logcat.
     * @param world L'état actuel du monde
     */
    public void simulateSeason(GameWorld world) {
        Log.d(TAG, "===== SIMULATION DE LA SAISON " + world.currentYear + " =====");

        // On utilise des Maps pour suivre les scores totaux
        Map<Driver, Integer> driverChampionshipPoints = new HashMap<>();
        Map<Team, Integer> teamChampionshipPoints = new HashMap<>();

        // Initialisation des scores à 0 pour tout le monde
        for (Driver driver : world.allDrivers) {
            driverChampionshipPoints.put(driver, 0);
        }
        for (Team team : world.allTeams) {
            teamChampionshipPoints.put(team, 0);
        }

        // 1. On boucle sur chaque circuit du calendrier
        for (Track track : world.allTracks) {
            Log.d(TAG, "--- Simulation de la course à: " + track.name + " ---");

            // On lance la simulation de la course
            List<DriverRaceResult> raceResults = raceEngine.simulateRace(world, track);

            // On affiche le CLASSEMENT FINAL
            for (DriverRaceResult result : raceResults) {
                String logMessage = "P" + result.finishingPosition + ": " +
                        result.driver.firstName + " " + result.driver.lastName;
                // On logue avec le TAG "MainActivity" pour séparer du bruit
                Log.d(TAG, logMessage);
            }
            Log.d(TAG, "===== FIN DE LA SIMULATION (MONZA) =====");

            // 2. On attribue les points
            for (int i = 0; i < raceResults.size(); i++) {
                if (i < POINTS_SYSTEM.length) { // Si le pilote est dans le top 10
                    int points = POINTS_SYSTEM[i];
                    Driver driver = raceResults.get(i).driver;

                    // Ajoute les points au pilote
                    int currentDriverPoints = driverChampionshipPoints.get(driver);
                    driverChampionshipPoints.put(driver, currentDriverPoints + points);
                }
            }
        } // Fin de la boucle des courses

        // 3. On calcule les points des constructeurs (basés sur les points pilotes)
        for (Team team : world.allTeams) {
            int teamPoints = 0;
            for (Driver driver : team.drivers) {
                teamPoints += driverChampionshipPoints.get(driver);
            }
            teamChampionshipPoints.put(team, teamPoints);
        }

        // 4. On affiche les classements finaux dans les logs
        Log.d(TAG, "===== CLASSEMENT FINAL PILOTES " + world.currentYear + " =====");
        logSortedMap(driverChampionshipPoints);

        Log.d(TAG, "===== CLASSEMENT FINAL CONSTRUCTEURS " + world.currentYear + " =====");
        logSortedMap(teamChampionshipPoints);

        Log.d(TAG, "===== FIN DE LA SAISON " + world.currentYear + " =====");

        // Étape future : On enregistrera ces résultats dans DriverSeasonHistory, etc.
    }

    /**
     * Une méthode utilitaire pour trier et afficher joliment nos Maps de résultats.
     * (C'est un peu complexe, mais c'est pour un affichage propre)
     */
    private <T> void logSortedMap(Map<T, Integer> map) {
        // 1. Convertit la Map en une Liste triable (comme on a fait dans RaceEngine)
        List<Map.Entry<T, Integer>> sortedList = new ArrayList<>(map.entrySet());

        // 2. Trie la liste par valeur (points), en ordre décroissant
        sortedList.sort(Map.Entry.comparingByValue(Comparator.reverseOrder()));

        // 3. Affiche chaque entrée
        int rank = 1;
        for (Map.Entry<T, Integer> entry : sortedList) {
            String name;
            Object key = entry.getKey();
            if (key instanceof Driver) {
                name = ((Driver) key).firstName + " " + ((Driver) key).lastName;
            } else if (key instanceof Team) {
                name = ((Team) key).name;
            } else {
                name = key.toString();
            }
            Log.d(TAG, "P" + rank + ": " + name + " (" + entry.getValue() + " points)");
            rank++;
        }
    }
}
