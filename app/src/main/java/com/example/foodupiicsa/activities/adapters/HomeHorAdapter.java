package com.example.foodupiicsa.activities.adapters;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.example.foodupiicsa.R;
import com.example.foodupiicsa.activities.models.HomeHorModel;
import com.example.foodupiicsa.activities.models.HomeVerModel;

import java.util.ArrayList;

public class HomeHorAdapter extends RecyclerView.Adapter<HomeHorAdapter.ViewHolder> {

    // Interfaz para actualizar el RecyclerView Vertical
    UpdateVerticalRec updateVerticalRec;
    // Contexto de la actividad o fragmento donde se utiliza el adaptador
    Activity activity;


    ArrayList<HomeHorModel> list;
    ArrayList<HomeHorModel> originalList;


    // Variables de control para manejar selecciones
    boolean check = true;
    boolean select = true;
    int row_index = -1;

    // Constructor del Adaptador.
    public HomeHorAdapter(UpdateVerticalRec updateVerticalRec, Activity activity, ArrayList<HomeHorModel> list) {
        this.updateVerticalRec = updateVerticalRec;
        this.activity = activity;
        this.list = list;
        this.originalList=new ArrayList<>(list);
    }

    //METODO PARA FILTRAR LA LISTA
    public void filter(String text) {
        list.clear();
        if(text.isEmpty()) {
            list.addAll(originalList);
        } else {
            text = text.toLowerCase();
            for(HomeHorModel item: originalList) {
                if(item.getName().toLowerCase().contains(text)) {
                    list.add(item);
                }
            }
        }
        notifyDataSetChanged();
    }


    // Infla el diseño del elemento de la lista horizontal y crea un nuevo ViewHolder
    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        // Infla el diseño correcto para los elementos horizontales
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.home_horizontal_item, parent, false);
        return new ViewHolder(view);
    }

    // Vincula los datos a las vistas del ViewHolder para la posición especificada
    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        // Vincular datos a las vistas
        holder.imageView.setImageResource(list.get(position).getImage());
        holder.name.setText(list.get(position).getName());

        // Actualiza el RecyclerView según la posición seleccionada
        if (check) {
            ArrayList<HomeVerModel> homeVerModels = new ArrayList<>();
            homeVerModels.add(new HomeVerModel(R.drawable.pizza1, "Pizza 1", "10:00", "5.0", "50"));
            homeVerModels.add(new HomeVerModel(R.drawable.pizza2, "Pizza 2", "10:00", "5.0", "50"));
            homeVerModels.add(new HomeVerModel(R.drawable.pizza3, "Pizza 3", "10:00", "5.0", "50"));
            homeVerModels.add(new HomeVerModel(R.drawable.pizza4, "Pizza 4", "10:00", "5.0", "50"));

            updateVerticalRec.callBack(position, homeVerModels);
            check = false;
        }

        // Configura el comportamiento al hacer clic en el CardView
        holder.cardView.setOnClickListener(view -> {
            row_index = position;
            notifyDataSetChanged();

            ArrayList<HomeVerModel> homeVerModels = new ArrayList<>();
            switch (position) {
                case 0:
                    homeVerModels.add(new HomeVerModel(R.drawable.pizza1, "Pizza 1", "10:00", "5.0", "50"));
                    homeVerModels.add(new HomeVerModel(R.drawable.pizza2, "Pizza 2", "10:00", "5.0", "50"));
                    homeVerModels.add(new HomeVerModel(R.drawable.pizza3, "Pizza 3", "10:00", "5.0", "50"));
                    homeVerModels.add(new HomeVerModel(R.drawable.pizza4, "Pizza 4", "10:00", "5.0", "50"));
                    break;
                case 1:
                    homeVerModels.add(new HomeVerModel(R.drawable.burger1, "Burger 1", "10:00", "5.0", "50"));
                    homeVerModels.add(new HomeVerModel(R.drawable.burger2, "Burger 2", "10:00", "5.0", "50"));
                    homeVerModels.add(new HomeVerModel(R.drawable.burger4, "Burger 3", "10:00", "5.0", "50"));
                    break;
                case 2:
                    homeVerModels.add(new HomeVerModel(R.drawable.fries1, "Fries 1", "10:00", "5.0", "50"));
                    homeVerModels.add(new HomeVerModel(R.drawable.fries2, "Fries 2", "10:00", "5.0", "50"));
                    homeVerModels.add(new HomeVerModel(R.drawable.fries3, "Fries 3", "10:00", "5.0", "50"));
                    homeVerModels.add(new HomeVerModel(R.drawable.fries4, "Fries 4", "10:00", "5.0", "50"));
                    break;
                case 3:
                    homeVerModels.add(new HomeVerModel(R.drawable.icecream1, "Ice Cream 1", "10:00", "5.0", "50"));
                    homeVerModels.add(new HomeVerModel(R.drawable.icecream2, "Ice Cream 3", "10:00", "5.0", "50"));
                    homeVerModels.add(new HomeVerModel(R.drawable.icecream3, "Ice Cream 2", "10:00", "5.0", "50"));
                    homeVerModels.add(new HomeVerModel(R.drawable.icecream4, "Ice Cream 4", "10:00", "5.0", "50"));
                    break;
                case 4:
                    homeVerModels.add(new HomeVerModel(R.drawable.sandwich1, "Sandwich 1", "10:00", "5.0", "50"));
                    homeVerModels.add(new HomeVerModel(R.drawable.sandwich2, "Sandwich 2", "10:00", "5.0", "50"));
                    homeVerModels.add(new HomeVerModel(R.drawable.sandwich3, "Sandwich 3", "10:00", "5.0", "50"));
                    homeVerModels.add(new HomeVerModel(R.drawable.sandwich4, "Sandwich 4", "10:00", "5.0", "50"));
                    break;
            }
            updateVerticalRec.callBack(position, homeVerModels);
        });

        // Cambia el fondo del CardView para resaltar el elemento seleccionado
        if (select) {
            if (position == 0) {
                holder.cardView.setBackgroundResource(R.drawable.chance_bg);
                select = false;
            }
        } else {
            if (row_index == position) {
                holder.cardView.setBackgroundResource(R.drawable.chance_bg);
            } else {
                holder.cardView.setBackgroundResource(R.drawable.default_bg);
            }
        }
    }

    // Devuelve el número de elementos en la lista
    @Override
    public int getItemCount() {
        // Devuelve el tamaño de la lista si no es nula, de lo contrario, devuelve 0
        return (list != null) ? list.size() : 0;
    }

    // Clase interna estática para representar los elementos del RecyclerView
    public static class ViewHolder extends RecyclerView.ViewHolder {
        // Referencias a las vistas dentro del diseño del elemento
        ImageView imageView;
        TextView name;
        CardView cardView;

        // Constructor del ViewHolder que vincula las vistas a los IDs del diseño
        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            // Encuentra las vistas dentro del diseño usando sus IDs
            imageView = itemView.findViewById(R.id.hor_img);
            name = itemView.findViewById(R.id.hor_text);
            cardView = itemView.findViewById(R.id.CardView);
        }
    }
}
