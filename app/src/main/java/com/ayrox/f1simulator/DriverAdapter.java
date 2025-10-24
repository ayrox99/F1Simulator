package com.ayrox.f1simulator;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import java.util.List;

// C'est presque identique à TeamAdapter, mais on travaille avec des Drivers
public class DriverAdapter extends RecyclerView.Adapter<DriverAdapter.DriverViewHolder> {

    private List<Driver> driverList;

    // --- NOUVEAU (Partie 1) ---
    // L'interface "contrat" pour le clic
    public interface OnItemClickListener {
        void onItemClick(int position);
    }
    // -------------------------

    // --- NOUVEAU (Partie 2) ---
    // La variable pour stocker l'écouteur
    private OnItemClickListener listener;
    // -------------------------

    // --- NOUVEAU (Partie 3) ---
    // La méthode "setter" pour que l'Activity s'enregistre
    public void setOnItemClickListener(OnItemClickListener listener) {
        this.listener = listener;
    }

    public DriverAdapter(List<Driver> driverList) {
        this.driverList = driverList;
    }

    // Le ViewHolder fait maintenant référence à notre item_driver.xml
    public class DriverViewHolder extends RecyclerView.ViewHolder {
        public TextView driverNameTextView;

        public DriverViewHolder(@NonNull View itemView) {
            super(itemView);
            // On fait le lien avec l'ID de notre layout item_driver.xml
            driverNameTextView = itemView.findViewById(R.id.driverNameTextView);

            // --- NOUVEAU (Partie 4) ---
            // On ajoute le détecteur de clic sur la ligne entière
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int position = getAdapterPosition();
                    if (listener != null && position != RecyclerView.NO_POSITION) {
                        listener.onItemClick(position);
                    }
                }
            });
        }
    }

    @NonNull
    @Override
    public DriverViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        // On "gonfle" le bon layout : item_driver
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_driver, parent, false);
        return new DriverViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull DriverViewHolder holder, int position) {
        // On récupère un objet Driver
        Driver currentDriver = driverList.get(position);

        // On affiche le nom complet du pilote
        String driverName = currentDriver.firstName + " " + currentDriver.lastName;
        holder.driverNameTextView.setText(driverName);
    }

    @Override
    public int getItemCount() {
        return driverList.size();
    }
}
