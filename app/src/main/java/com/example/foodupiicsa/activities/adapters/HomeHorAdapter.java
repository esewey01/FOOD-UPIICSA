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

    //Interfaz para actualizar el RecyclerView Vertical
    UpdateVerticalRec updateVerticalRec;
    // Contexto de la actividad o fragmento donde se utiliza el adaptador
    Activity activity;

    // Lista de datos para la vita horizontal
    ArrayList<HomeHorModel> list;

    //variables de control para manejar selecciones
    boolean check=true;
    boolean select=true;
    int row_index= -1;

    //Contructor del Adaptador.
    public HomeHorAdapter(UpdateVerticalRec updateVerticalRec, Activity activity, ArrayList<HomeHorModel> list) {
        this.updateVerticalRec = updateVerticalRec;
        this.activity = activity;
        this.list = list;
    }

    //INLA EL DISEÑO DEL ELEMENTO DE LA LISTA HORIZAONTAL Y CREA UN NUEVO VIEWHOLDER
    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        // Infla el diseño del elemento de la lista (item) y crea un nuevo ViewHolder
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.home_horizontal_item, parent, false);
        return new ViewHolder(view);
    }

    //VINCULA LOS DATOS A LAS VISTAS DEL VIEWHOLDER PARA LA POSICION ESPECIFICADA
    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.imageView.setImageResource(list.get(position).getImage());
        holder.name.setText(list.get(position).getName());
        //ACTUALIZA EL RECYCLERVIEW SEGUN LA POSICION SELECCIONADA
        if (check){
            ArrayList<HomeVerModel> homeVerModels=new ArrayList<>();
            homeVerModels.add(new HomeVerModel("Pizza1","#", "##:##",R.drawable.public_ipn));
            homeVerModels.add(new HomeVerModel("Pizza2","#", "##:##",R.drawable.public_image));
            homeVerModels.add(new HomeVerModel("Pizza3","#", "##:##",R.drawable.public_image2));
            homeVerModels.add(new HomeVerModel("Pizza4","#", "##:##",R.drawable.public_image3));

            updateVerticalRec.callBack(position,homeVerModels);
            check=false;
            }
            if (list == null || position < 0 || position >= list.size()) {
            return;}

            //CONFIGURA EL COMPORTAMIENTO AL HACER CLIC EN EL CARD VIEW
            holder.cardView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    row_index=position;
                    notifyDataSetChanged();
                    if (position==0){
                        ArrayList<HomeVerModel> homeVerModels=new ArrayList<>();
                        homeVerModels.add(new HomeVerModel("Pizza1","#", "##:##",R.drawable.pizza1));
                        homeVerModels.add(new HomeVerModel("Pizza1","#", "##:##",R.drawable.pizza2));
                        homeVerModels.add(new HomeVerModel("Pizza1","#", "##:##",R.drawable.pizza3));
                        homeVerModels.add(new HomeVerModel("Pizza4","#", "##:##",R.drawable.pizza4));

                        updateVerticalRec.callBack(position,homeVerModels);
                    }
                    else if (position==1){
                        ArrayList<HomeVerModel> homeVerModels=new ArrayList<>();
                        homeVerModels.add(new HomeVerModel("Burger4","#", "##:##",R.drawable.burger1));
                        homeVerModels.add(new HomeVerModel("Burger4","#", "##:##",R.drawable.burger2));
                        homeVerModels.add(new HomeVerModel("Burger4","#", "##:##",R.drawable.burger4));
                        homeVerModels.add(new HomeVerModel("Burger","#", "##:##",R.drawable.burger4));

                        updateVerticalRec.callBack(position,homeVerModels);
                    }
                    else if (position==2){
                        ArrayList<HomeVerModel> homeVerModels=new ArrayList<>();
                        homeVerModels.add(new HomeVerModel("TazaCafe4","#", "##:##",R.drawable.fries1));
                        homeVerModels.add(new HomeVerModel("TazaCafe4","#", "##:##",R.drawable.fries2));
                        homeVerModels.add(new HomeVerModel("TazaCafe4","#", "##:##",R.drawable.fries3));
                        homeVerModels.add(new HomeVerModel("TazaCafe","#", "##:##",R.drawable.fries4));

                        updateVerticalRec.callBack(position,homeVerModels);
                    }
                    else if (position==3){
                        ArrayList<HomeVerModel> homeVerModels=new ArrayList<>();
                        homeVerModels.add(new HomeVerModel("Zalamero4","#", "##:##",R.drawable.icecream1));
                        homeVerModels.add(new HomeVerModel("Zalamero4","#", "##:##",R.drawable.icecream2));
                        homeVerModels.add(new HomeVerModel("Zalamero4","#", "##:##",R.drawable.icecream3));
                        homeVerModels.add(new HomeVerModel("Zalamero","#", "##:##",R.drawable.icecream4));

                        updateVerticalRec.callBack(position,homeVerModels);
                    }

                    else if (position==4){
                        ArrayList<HomeVerModel> homeVerModels=new ArrayList<>();
                        homeVerModels.add(new HomeVerModel("Helado4","#", "##:##",R.drawable.sandwich1));
                        homeVerModels.add(new HomeVerModel("Helado4","#", "##:##",R.drawable.sandwich2));
                        homeVerModels.add(new HomeVerModel("Helado4","#", "##:##",R.drawable.sandwich3));
                        homeVerModels.add(new HomeVerModel("Helado4","#", "##:##",R.drawable.sandwich4));

                        updateVerticalRec.callBack(position,homeVerModels);
                    }
                }
            });

            //CAAMBIA EL FONDO DEL CARDWIEV PARA RESALTAR EL ELEMENTO SELECCIONADO
            if (select){
                if (position==0){
                    holder.cardView.setBackgroundResource(R.drawable.chance_bg);
                    select = false;
                }
            }
            else {
                if (row_index==position){
                    holder.cardView.setBackgroundResource(R.drawable.chance_bg);
                }else {
                    holder.cardView.setBackgroundResource(R.drawable.default_bg);
                }
            }
        }


        //DEVUELVE EL NUMERO DE ELEENTOS EN LA LISTA
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