package com.ayrox.f1simulator;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;

import java.util.List;

public class MainActivity extends AppCompatActivity implements TeamAdapter.OnItemClickListener {

    private GameWorld world;
    private static final String TAG = "MainActivityLogs";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        this.world = GameWorld.getInstance();

        RecyclerView teamsRecyclerView = findViewById(R.id.teamsRecyclerView);
        TeamAdapter adapter = new TeamAdapter(world.allTeams);
        adapter.setOnItemClickListener(this);
        teamsRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        teamsRecyclerView.setAdapter(adapter);

        // --- MODIFICATION : On lance la simulation de SAISON ---
        runTestSeasonSimulation();
    }

    /**
     * MODIFICATION : On teste maintenant le SeasonEngine
     */
    private void runTestSeasonSimulation() {
        Log.d(TAG, "===== LANCEMENT DE LA SIMULATION DE SAISON (DEPUIS MAIN) =====");

        // 1. On crée notre nouveau moteur de saison
        SeasonEngine seasonEngine = new SeasonEngine();

        // 2. On lance la simulation de la saison !
        // Tous les logs (courses, calculs, classements) seront gérés par SeasonEngine
        seasonEngine.simulateSeason(world);

        Log.d(TAG, "===== SIMULATION DE SAISON TERMINÉE (DEPUIS MAIN) =====");
    }


    @Override
    public void onItemClick(int position) {
        Team clickedTeam = world.allTeams.get(position);
        Intent intent = new Intent(this, TeamDetailActivity.class);
        intent.putExtra("TEAM_NAME_EXTRA", clickedTeam.name);
        startActivity(intent);
    }
}

