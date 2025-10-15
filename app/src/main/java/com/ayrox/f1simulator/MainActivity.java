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

        Log.d("Caca", "===== DÉBUT DE LA VÉRIFICATION DU MONDE =====");
        Log.d("Caca", "Année en cours : " + world.currentYear);
        Log.d("Caca", "Nombre total d'écuries : " + world.allTeams.size());

// On utilise une boucle "for" pour parcourir chaque écurie
        for (Team team : world.allTeams) {
            // Pour chaque écurie, on affiche son nom
            Log.d("Caca", "Écurie: " + team.name + " (" + team.drivers.size() + " pilotes)");

            // On fait une deuxième boucle à l'intérieur pour parcourir les pilotes de CETTE écurie
            for (Driver driver : team.drivers) {
                Log.d("Caca", "  - Pilote: " + driver.firstName + " " + driver.lastName);
            }
        }
        Log.d("Caca", "===== FIN DE LA VÉRIFICATION DU MONDE =====");

    }


}