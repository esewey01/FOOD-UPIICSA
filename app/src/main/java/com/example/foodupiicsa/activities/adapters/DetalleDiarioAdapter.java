package com.example.foodupiicsa.activities.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.foodupiicsa.R;
import com.example.foodupiicsa.activities.models.CarritoFunciones;
import com.example.foodupiicsa.activities.models.CartModel;
import com.example.foodupiicsa.activities.models.DetalleDiarioModel;

import java.util.List;

public class DetalleDiarioAdapter extends RecyclerView.Adapter<DetalleDiarioAdapter.ViewHolder> {

    List<DetalleDiarioModel> list;
    Context context;
    public DetalleDiarioAdapter(List<DetalleDiarioModel> list) {
        this.list = list;
    }



    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view =LayoutInflater.from(parent.getContext())
                .inflate(R.layout.detalles_comida_diaria_item,parent,false);
        context=parent.getContext();
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        DetalleDiarioModel item = list.get(position);

        holder.imageView.setImageResource(item.getImage());
        holder.nombre.setText(list.get(position).getNombre());
        holder.descripcion.setText(list.get(position).getDescripcion());
        holder.rating.setText(list.get(position).getRating());
        holder.precio.setText(list.get(position).getPrecio());
        holder.tiempo.setText(list.get(position).getTiempo());

        holder.addToCartButton.setOnClickListener(v-> {
            CarritoFunciones carritoFunciones= CarritoFunciones.getInstance();

            CartModel cartItem=new CartModel(
                    item.getImage(),
                    item.getNombre(),
                    item.getPrecio(),
                    item.getPrecio()
            );
            if (carritoFunciones.getCartItems().contains(cartItem)) {
                Toast.makeText(context, "Ya está en el carrito", Toast.LENGTH_SHORT).show();
            } else {
                carritoFunciones.addItem(cartItem);
                Toast.makeText(context, "Agregado al carrito", Toast.LENGTH_SHORT).show();
            }
        });

    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        ImageView imageView;
        TextView nombre,precio,descripcion,rating,tiempo;
        Button addToCartButton;
        public  ViewHolder(@NonNull View itemView){
            super(itemView);
            imageView=itemView.findViewById(R.id.detalles_img);
            nombre=itemView.findViewById(R.id.detalles_name);
            descripcion=itemView.findViewById(R.id.detalles_des);
            rating=itemView.findViewById(R.id.detalles_rating);
            precio=itemView.findViewById(R.id.detalles_precio);
            tiempo=itemView.findViewById(R.id.detalles_timing);

            //FUNCION DEL BOTON
            addToCartButton=itemView.findViewById(R.id.btn_add_to_cart);
        }
    }
}
