package com.ayrox.f1simulator;

import android.os.Bundle;
import android.util.Log;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

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
        // On crée notre monde, le constructeur fait tout le travail !
        GameWorld world = new GameWorld();

        // On utilise Log.d pour afficher le résultat (plus propre)
        Log.d("MainActivity", "Monde créé pour l'année : " + world.currentYear);
        Log.d("MainActivity", "Nombre d'écuries : " + world.allTeams.size());
        for (int i = 0; i < world.allTeams.size(); i++) {
            Log.d("MainActivity", "Ecurie " + i + " : " + world.allTeams.get(i).name);
        }
    }


}