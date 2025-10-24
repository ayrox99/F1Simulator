package com.ayrox.f1simulator;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

public class DriverDetailActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_driver_detail);

        // 1. Récupérer les infos de l'Intent
        Intent intent = getIntent();
        String teamName = intent.getStringExtra("TEAM_NAME_EXTRA");
        // getIntExtra a besoin d'une valeur par défaut si la clé n'est pas trouvée.
        // On utilise -1 comme signal d'erreur.
        int driverIndex = intent.getIntExtra("DRIVER_INDEX_EXTRA", -1);

        // 2. Vérifier si on a bien reçu les infos
        if (teamName == null || driverIndex == -1) {
            Log.e("DriverDetailActivity", "Erreur: Données du pilote manquantes.");
            finish(); // On ferme l'Activity si les données sont incorrectes
            return;   // On arrête l'exécution de onCreate
        }

        // 3. Récupérer le pilote
        GameWorld world = GameWorld.getInstance();
        Team team = world.findTeamByName(teamName);

        // 4. Vérifier que l'écurie et le pilote sont valides
        if (team == null) {
            Log.e("DriverDetailActivity", "Erreur: Impossible de trouver l'écurie : " + teamName);
            finish();
            return;
        }

        // On s'assure que l'index est valide avant de l'utiliser
        if (driverIndex < 0 || driverIndex >= team.drivers.size()) {
            Log.e("DriverDetailActivity", "Erreur: Index du pilote invalide : " + driverIndex);
            finish();
            return;
        }

        Driver driver = team.drivers.get(driverIndex);

        // 5. Récupérer tous les TextViews de notre layout
        TextView nameTitle = findViewById(R.id.driverNameTitleTextView);
        TextView paceValue = findViewById(R.id.paceValueTextView);
        TextView consistencyValue = findViewById(R.id.consistencyValueTextView);
        TextView overtakingValue = findViewById(R.id.overtakingValueTextView);
        TextView defendingValue = findViewById(R.id.defendingValueTextView);

        // --- Code complété ---
        // Note : Assurez-vous d'avoir ces IDs dans votre activity_driver_detail.xml
        TextView tyreManagementValue = findViewById(R.id.tyreManagementValueTextView);
        TextView wetWeatherValue = findViewById(R.id.wetWeatherValueTextView);
        TextView composureValue = findViewById(R.id.composureValueTextView);
        TextView developmentSkillValue = findViewById(R.id.developmentSkillValueTextView);

        // 6. Mettre à jour les TextViews avec les stats du pilote
        nameTitle.setText(driver.firstName + " " + driver.lastName);

        // On doit convertir les "int" en "String" pour les afficher
        // sinon Android pense qu'on lui passe un ID de ressource et l'appli crashe.
        paceValue.setText(String.valueOf(driver.currentSkills.pace));
        consistencyValue.setText(String.valueOf(driver.currentSkills.consistency));
        overtakingValue.setText(String.valueOf(driver.currentSkills.overtaking));
        defendingValue.setText(String.valueOf(driver.currentSkills.defending));

        // --- Code complété ---
        // On vérifie que le TextView existe avant de l'utiliser (au cas où il manquerait dans le XML)
        if (tyreManagementValue != null) {
            tyreManagementValue.setText(String.valueOf(driver.currentSkills.tyreManagement));
        }
        if (wetWeatherValue != null) {
            wetWeatherValue.setText(String.valueOf(driver.currentSkills.wetWeather));
        }
        if (composureValue != null) {
            composureValue.setText(String.valueOf(driver.currentSkills.composure));
        }
        if (developmentSkillValue != null) {
            developmentSkillValue.setText(String.valueOf(driver.currentSkills.developmentSkill));
        }
    }
}

