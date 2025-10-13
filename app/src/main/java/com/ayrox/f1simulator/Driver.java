package com.ayrox.f1simulator;
import java.util.List;

public class Driver {

    // === Identity ===
    String firstName;
    String lastName;
    int age;
    String nationality;
    List<Contract> contractHistory;

    // === Skills ===
    int pace;
    int consistency;
    int overtaking;
    int defending;
    int tyreManagement;
    int wetWeather;
    int composure;
    int developmentSkill;

    // === Career & Progression ===
    int potential;
    List<SkillSnapshot> skillProgressionHistory;

    // === History ===
    List<SeasonHistory> careerHistory;
}
