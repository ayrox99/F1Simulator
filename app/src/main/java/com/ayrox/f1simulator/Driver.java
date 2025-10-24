package com.ayrox.f1simulator;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

public class Driver extends AgeablePerson {

    // === Contract ===
    List<Contract> contractHistory;

    // === Skills ===
    DriverSkills currentSkills;

    // === Career & Progression ===
    int potential;
    List<SkillSnapshot> skillProgressionHistory;

    // === History ===
    List<DriverSeasonHistory> careerHistory;

    // --- NOUVELLE FONCTION ---

    // Listes de noms et prénoms pour la génération aléatoire
    // (Vous pouvez bien sûr étendre ces listes)
    private static final String[] PRENOMS = {
            "Alex", "Max", "Lewis", "Charles", "Lando", "Carlos", "George",
            "Sergio", "Fernando", "Esteban", "Pierre", "Yuki", "Valtteri",
            "Zhou", "Kevin", "Nico", "Daniel", "Oscar", "Logan", "Lance",
            "Mick", "Sebastian", "Kimi", "Jenson", "Felipe"
    };

    private static final String[] NOMS_DE_FAMILLE = {
            "Verstappen", "Hamilton", "Leclerc", "Norris", "Sainz", "Russell",
            "Perez", "Alonso", "Ocon", "Gasly", "Tsunoda", "Bottas", "Guanyu",
            "Magnussen", "Hulkenberg", "Ricciardo", "Piastri", "Sargeant",
            "Stroll", "Albon", "Schumacher", "Vettel", "Räikkönen", "Button", "Massa"
    };

    /**
     * Génère un nom complet aléatoire (Prénom Nom) à partir des listes ci-dessus.
     *
     * @return Un String contenant un prénom et un nom aléatoires.
     */
    public static String genererNomComplet() {
        // Obtenir un index aléatoire pour le prénom
        int indexPrenom = ThreadLocalRandom.current().nextInt(PRENOMS.length);

        // Obtenir un index aléatoire pour le nom de famille
        int indexNom = ThreadLocalRandom.current().nextInt(NOMS_DE_FAMILLE.length);

        // Combiner et retourner le nom complet
        return PRENOMS[indexPrenom] + " " + NOMS_DE_FAMILLE[indexNom];
    }
    public Driver (int min, int max){
        // Obtenir un index aléatoire pour le prénom
        int indexPrenom = ThreadLocalRandom.current().nextInt(PRENOMS.length);

        // Obtenir un index aléatoire pour le nom de famille
        int indexNom = ThreadLocalRandom.current().nextInt(NOMS_DE_FAMILLE.length);

        this.firstName = PRENOMS[indexPrenom];
        this.lastName = NOMS_DE_FAMILLE[indexNom];
        this.currentSkills = new DriverSkills(min, max);
    }
}
