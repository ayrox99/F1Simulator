package com.ayrox.f1simulator;

// N'oublie pas d'importer Log !
import android.util.Log;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ThreadLocalRandom;

public class RaceEngine {

    // NOUVEAU : Un TAG pour filtrer nos logs
    private static final String TAG = "RaceEngine";

    /**
     * Simule une course complète sur un circuit donné.
     * @param world L'état actuel du monde (pour avoir les écuries et pilotes)
     * @param track Le circuit sur lequel la course se déroule
     * @return Une liste de DriverRaceResult, triée de la P1 à la dernière place.
     */
    public List<DriverRaceResult> simulateRace(GameWorld world, Track track) {

        Log.d(TAG, "--- Début du calcul des scores pour : " + track.name + " ---");

        Map<Driver, Double> performanceScores = new HashMap<>();

        for (Team team : world.allTeams) {
            // Calcul du score de la voiture
            double carScore = (team.aeroPerformance * track.aeroFactor) +
                    (team.enginePerformance * track.engineFactor) +
                    (team.chassisPerformance * track.chassisFactor);

            // NOUVEAU LOG : Affiche le score de la voiture (une fois par écurie)
            Log.d(TAG, "Écurie: " + team.name + " | Car Score: " + String.format("%.2f", carScore));

            for (Driver driver : team.drivers) {
                String driverName = driver.firstName + " " + driver.lastName;

                // Calcul du score du pilote
                double driverScore = (driver.currentSkills.pace * 0.8) + (driver.currentSkills.composure * 0.2);
                // NOUVEAU LOG
                Log.d(TAG, "  -> Pilote: " + driverName + " | Driver Score: " + String.format("%.2f", driverScore));


                double basePerformance = (carScore * 0.6) + (driverScore * 0.4);
                // NOUVEAU LOG
                Log.d(TAG, "     | Base Performance (60% voiture, 40% pilote): " + String.format("%.2f", basePerformance));


                double consistencyEffect = 1.0 - (driver.currentSkills.consistency / 150.0);
                // NOUVEAU LOG
                Log.d(TAG, "     | Consistency Effect (plus c'est bas, mieux c'est): " + String.format("%.2f", consistencyEffect));


                double randomJitter = (ThreadLocalRandom.current().nextDouble(-5.0, 5.0)) * consistencyEffect;
                // NOUVEAU LOG
                Log.d(TAG, "     | Random Jitter (aléatoire * consistency): " + String.format("%.2f", randomJitter));


                double finalScore = basePerformance + randomJitter;
                // NOUVEAU LOG
                Log.d(TAG, "     | SCORE FINAL (Base + Jitter): " + String.format("%.2f", finalScore));


                performanceScores.put(driver, finalScore);
            }
        }

        Log.d(TAG, "--- Fin du calcul des scores. Tri... ---");

        // ... Le reste de la méthode (tri et création de la liste) est inchangé ...
        List<Map.Entry<Driver, Double>> sortedList = new ArrayList<>(performanceScores.entrySet());
        sortedList.sort(Map.Entry.comparingByValue(Comparator.reverseOrder()));
        List<DriverRaceResult> finalResults = new ArrayList<>();
        int position = 1;
        for (Map.Entry<Driver, Double> entry : sortedList) {
            finalResults.add(new DriverRaceResult(entry.getKey(), position));
            position++;
        }

        return finalResults;
    }
}

