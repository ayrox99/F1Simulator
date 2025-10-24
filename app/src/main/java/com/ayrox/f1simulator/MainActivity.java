package com.ayrox.f1simulator;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements TeamAdapter.OnItemClickListener {

    private GameWorld world;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        // 1. On crée notre monde, le constructeur fait tout le travail !
        this.world = GameWorld.getInstance();

        // 2. On récupère la référence vers notre RecyclerView dans le layout
        // C'est le pont entre le fichier XML et notre code Java
        RecyclerView teamsRecyclerView = findViewById(R.id.teamsRecyclerView);

        // 3. On crée notre adaptateur en lui donnant la liste des écuries
        TeamAdapter adapter = new TeamAdapter(world.allTeams);

        // NOUVEAU : On dit à l'adaptateur que "cette classe" (this) écoute les clics
        adapter.setOnItemClickListener(this);

        // 4. On dit au RecyclerView comment afficher les éléments.
        // On veut une liste verticale simple, on utilise donc un LinearLayoutManager.
        teamsRecyclerView.setLayoutManager(new LinearLayoutManager(this));

        // 5. ET VOILÀ : On branche l'adaptateur au RecyclerView !
        // C'est cette ligne qui fait toute la magie.
        teamsRecyclerView.setAdapter(adapter);

    }

    // NOUVEAU : On doit OBLIGATOIREMENT ajouter cette méthode car on a "signé le contrat"
    @Override
    public void onItemClick(int position) {
        // Le "position" nous dit quel item a été cliqué
        // On récupère l'écurie correspondante dans notre liste
        Team clickedTeam = world.allTeams.get(position);

        // Au lieu de juste logger, on prépare une "intention" de démarrer un nouvel écran
        Intent intent = new Intent(this, TeamDetailActivity.class);

        // On attache des informations à cette intention.
        // C'est comme mettre une note sur une enveloppe.
        // On passe le nom de l'écurie cliquée.
        intent.putExtra("TEAM_NAME_EXTRA", clickedTeam.name);

        // On exécute l'intention !
        startActivity(intent);
    }


}