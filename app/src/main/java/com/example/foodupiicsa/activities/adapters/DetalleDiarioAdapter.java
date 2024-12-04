package com.example.foodupiicsa.activities.adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.foodupiicsa.R;
import com.example.foodupiicsa.activities.models.DetalleDiarioModel;

import java.util.List;

public class DetalleDiarioAdapter extends RecyclerView.Adapter<DetalleDiarioAdapter.ViewHolder> {

    public DetalleDiarioAdapter(List<DetalleDiarioModel> list) {
        this.list = list;
    }

    List<DetalleDiarioModel> list;

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.detalles_comida_diaria_item, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.imageView.setImageResource(list.get(position).getImage());
        holder.nombre.setText(list.get(position).getNombre());
        holder.descripcion.setText(list.get(position).getDescripcion());
        holder.rating.setText(list.get(position).getRating());
        holder.precio.setText(list.get(position).getPrecio());
        holder.tiempo.setText(list.get(position).getTiempo());
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        ImageView imageView;
        TextView nombre,precio,descripcion,rating,tiempo;
        public  ViewHolder(@NonNull View itemView){
            super(itemView);
            imageView=itemView.findViewById(R.id.detalles_img);
            nombre=itemView.findViewById(R.id.detalles_name);
            descripcion=itemView.findViewById(R.id.detalles_des);
            rating=itemView.findViewById(R.id.detalles_rating);
            precio=itemView.findViewById(R.id.detalles_precio);
            tiempo=itemView.findViewById(R.id.detalles_timing);
        }
    }
}
