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

    public Driver (int min, int max){
        this.firstName = NameGenerator.getRandomDriverFirstName();
        this.lastName = NameGenerator.getRandomDriverLastName();
        this.currentSkills = new DriverSkills(min, max);
    }
}
