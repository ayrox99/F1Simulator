package com.ayrox.f1simulator;
import java.util.List;

public class Driver extends Person{

    // === Contract ===
    List<Contract> contractHistory;

    // === Skills ===
    DriverSkills currentSkills;

    // === Career & Progression ===
    int potential;
    List<SkillSnapshot> skillProgressionHistory;

    // === History ===
    List<SeasonHistory> careerHistory;
}
