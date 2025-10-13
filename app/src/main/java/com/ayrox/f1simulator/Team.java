package com.ayrox.f1simulator;

import java.util.List;

public class Team {

    // === Identity ===
    String name;
    String base;
    String principal;

    // === Personnel ===
    List<Driver> drivers;
    TechnicalChief technicalChief;

    // === Resources & Development ===
    double budget;
    double nextYearCarInvestment;

    // Répartition des efforts de R&D (la somme doit faire 1.0)
    double aeroDevelopmentFocus;
    double engineDevelopmentFocus;
    double chassisDevelopmentFocus;
    double reliabilityDevelopmentFocus;

    // Infrastructures spécialisées
    int aeroFacilityLevel;
    int engineFacilityLevel;
    int chassisFacilityLevel;
    int reliabilityFacilityLevel;

    // === Car Performance ===
    int chassisPerformance;
    int enginePerformance;
    int aeroPerformance;
    int reliability;

    // === History ===
    List<TeamSeasonHistory> teamHistory;
}
