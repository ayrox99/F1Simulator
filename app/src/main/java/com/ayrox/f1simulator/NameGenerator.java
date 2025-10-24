package com.ayrox.f1simulator;

import java.util.concurrent.ThreadLocalRandom;

public class NameGenerator {
    // Listes de noms et prénoms pour la génération aléatoire
    // (Vous pouvez bien sûr étendre ces listes)
    public static final String[] DRIVERFIRSTNAMES = {
            "Alex", "Max", "Lewis", "Charles", "Lando", "Carlos", "George",
            "Sergio", "Fernando", "Esteban", "Pierre", "Yuki", "Valtteri",
            "Zhou", "Kevin", "Nico", "Daniel", "Oscar", "Logan", "Lance",
            "Mick", "Sebastian", "Kimi", "Jenson", "Felipe"
    };

    public static final String[] DRIVERLASTNAMES = {
            "Verstappen", "Hamilton", "Leclerc", "Norris", "Sainz", "Russell",
            "Perez", "Alonso", "Ocon", "Gasly", "Tsunoda", "Bottas", "Guanyu",
            "Magnussen", "Hulkenberg", "Ricciardo", "Piastri", "Sargeant",
            "Stroll", "Albon", "Schumacher", "Vettel", "Räikkönen", "Button", "Massa"
    };

    public static String getRandomDriverFirstName (){
        int firstNameIndex = ThreadLocalRandom.current().nextInt(DRIVERFIRSTNAMES.length);
        return DRIVERFIRSTNAMES[firstNameIndex];
    };

    public static String getRandomDriverLastName (){
        int lastNameIndex = ThreadLocalRandom.current().nextInt(DRIVERLASTNAMES.length);
        return DRIVERLASTNAMES[lastNameIndex];
    };
}
