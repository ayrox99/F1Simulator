package com.ayrox.f1simulator;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

public class TeamDetailActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_team_detail);

        // 1. On récupère l'Intent qui a lancé cette Activity
        Intent intent = getIntent();

        // 2. On extrait la donnée (le nom de l'écurie) en utilisant la MÊME CLÉ
        String teamName = intent.getStringExtra("TEAM_NAME_EXTRA");

        // NOUVEAU : On récupère l'objet Team complet grâce au Singleton
        GameWorld world = GameWorld.getInstance();
        Team currentTeam = world.findTeamByName(teamName);

        // 3. On trouve le TextView dans notre layout
        TextView teamNameTextView = findViewById(R.id.teamNameDetailTextView);

        // On vérifie que l'écurie a bien été trouvée
        if (currentTeam != null) {
            teamNameTextView.setText(currentTeam.name);

            // --- NOUVEAU : On branche la liste des pilotes ---

            // 1. On récupère la référence du nouveau RecyclerView
            RecyclerView driversRecyclerView = findViewById(R.id.driversRecyclerView);

            // 2. On crée le nouvel adaptateur en lui passant la liste des pilotes de l'écurie
            DriverAdapter driverAdapter = new DriverAdapter(currentTeam.drivers);

            // 3. On lui dit comment s'afficher (liste verticale)
            driversRecyclerView.setLayoutManager(new LinearLayoutManager(this));

            // 4. On branche l'adaptateur !
            driversRecyclerView.setAdapter(driverAdapter);
        }
    }
}