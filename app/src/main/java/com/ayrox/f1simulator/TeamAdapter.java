package com.ayrox.f1simulator;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import java.util.List;

// 1. La classe doit hériter de RecyclerView.Adapter
// On lui spécifie qu'elle travaillera avec notre propre ViewHolder : TeamViewHolder
public class TeamAdapter extends RecyclerView.Adapter<TeamAdapter.TeamViewHolder> {

    // 2. L'adapter a besoin d'une référence à la liste de données qu'il doit afficher
    private List<Team> teamList;

    // Le constructeur de l'adapter : il reçoit la liste de données en paramètre
    public TeamAdapter(List<Team> teamList) {
        this.teamList = teamList;
    }

    // 3. Le ViewHolder : C'est une classe interne qui représente UNE SEULE ligne de la liste.
    // Son rôle est de garder en mémoire les composants de l'interface d'une ligne (ici, juste un TextView).
    public static class TeamViewHolder extends RecyclerView.ViewHolder {
        public TextView teamNameTextView;

        public TeamViewHolder(@NonNull View itemView) {
            super(itemView);
            // On fait le lien avec notre composant dans item_team.xml
            teamNameTextView = itemView.findViewById(R.id.teamNameTextView);
        }
    }

    // 4. onCreateViewHolder : Appelée par le RecyclerView quand il a besoin de créer une NOUVELLE ligne.
    // C'est ici qu'on "gonfle" (inflate) notre layout XML item_team.xml pour le transformer en objet Java.
    @NonNull
    @Override
    public TeamViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_team, parent, false);
        return new TeamViewHolder(view);
    }

    // 5. onBindViewHolder : Appelée par le RecyclerView pour AFFICHER les données à une position précise.
    // C'est ici qu'on fait le lien entre une donnée (un objet Team) et une vue (un TeamViewHolder).
    @Override
    public void onBindViewHolder(@NonNull TeamViewHolder holder, int position) {
        // On récupère l'objet Team à la position donnée dans notre liste
        Team currentTeam = teamList.get(position);

        // On utilise le ViewHolder pour mettre à jour l'interface avec les données de l'objet Team
        holder.teamNameTextView.setText(currentTeam.name);
    }

    // 6. getItemCount : Méthode très simple qui doit juste retourner le nombre total d'éléments dans la liste.
    @Override
    public int getItemCount() {
        return teamList.size();
    }
}
