package com.ayrox.f1simulator;

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

public class MainActivity extends AppCompatActivity {

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
        GameWorld world = new GameWorld();

        // 2. On récupère la référence vers notre RecyclerView dans le layout
        // C'est le pont entre le fichier XML et notre code Java
        RecyclerView teamsRecyclerView = findViewById(R.id.teamsRecyclerView);

        // 3. On crée notre adaptateur en lui donnant la liste des écuries
        TeamAdapter adapter = new TeamAdapter(world.allTeams);

        // 4. On dit au RecyclerView comment afficher les éléments.
        // On veut une liste verticale simple, on utilise donc un LinearLayoutManager.
        teamsRecyclerView.setLayoutManager(new LinearLayoutManager(this));

        // 5. ET VOILÀ : On branche l'adaptateur au RecyclerView !
        // C'est cette ligne qui fait toute la magie.
        teamsRecyclerView.setAdapter(adapter);

    }


}