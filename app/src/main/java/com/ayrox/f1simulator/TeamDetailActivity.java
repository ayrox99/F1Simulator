package com.ayrox.f1simulator;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

// On implémente l'interface de notre DriverAdapter
public class TeamDetailActivity extends AppCompatActivity implements DriverAdapter.OnItemClickListener {

    // On garde une référence à l'écurie actuelle pour que onItemClick y ait accès
    private Team currentTeam;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_team_detail);

        // 1. Récupérer les données passées par l'Intent
        String teamName = getIntent().getStringExtra("TEAM_NAME_EXTRA");

        // 2. Récupérer notre monde et trouver l'objet Team
        GameWorld world = GameWorld.getInstance();
        this.currentTeam = world.findTeamByName(teamName);

        // 3. Récupérer le TextView pour le titre
        TextView teamNameTextView = findViewById(R.id.teamNameDetailTextView);

        // 4. Vérifier que l'écurie a été trouvée avant de continuer
        if (currentTeam != null) {
            // Mettre à jour le titre
            teamNameTextView.setText(currentTeam.name);

            // --- Configuration du RecyclerView des pilotes ---

            // 5. Récupérer le RecyclerView des pilotes
            RecyclerView driversRecyclerView = findViewById(R.id.driversRecyclerView);

            // 6. Créer l'adaptateur en lui passant la liste des pilotes de l'écurie
            DriverAdapter driverAdapter = new DriverAdapter(currentTeam.drivers);

            // 7. DIRE À L'ADAPTATEUR QUI ÉCOUTE LES CLICS (cette classe !)
            driverAdapter.setOnItemClickListener(this);

            // 8. Définir le LayoutManager (liste verticale)
            driversRecyclerView.setLayoutManager(new LinearLayoutManager(this));

            // 9. Brancher l'adaptateur
            driversRecyclerView.setAdapter(driverAdapter);
        } else {
            // Si on ne trouve pas l'écurie, afficher une erreur
            teamNameTextView.setText("Écurie non trouvée");
            Log.e("TeamDetailActivity", "Erreur: Impossible de trouver l'écurie avec le nom : " + teamName);
        }
    }

    // 10. Implémentation obligatoire de la méthode de l'interface
    @Override
    public void onItemClick(int position) {
        Log.d("TeamDetailActivity", "Clic détecté sur le pilote à la position : " + position);

        // Préparer l'intention de lancer le nouvel écran
        Intent intent = new Intent(this, DriverDetailActivity.class);

        // On passe les infos nécessaires pour retrouver le pilote
        // Le nom de l'écurie (pour retrouver la liste)
        intent.putExtra("TEAM_NAME_EXTRA", this.currentTeam.name);
        // L'index du pilote dans la liste de cette écurie
        intent.putExtra("DRIVER_INDEX_EXTRA", position);

        // Lancer l'Activity !
        startActivity(intent);
    }
}
