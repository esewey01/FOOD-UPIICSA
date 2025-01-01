package com.example.foodupiicsa.activities.adapters;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.foodupiicsa.R;
import com.example.foodupiicsa.activities.models.CartModel;


import java.util.List;

public class CartAdapter extends RecyclerView.Adapter<CartAdapter.ViewHolder> {
    private List<CartModel> cartItems;

    public CartAdapter(List<CartModel> list) {
        this.cartItems = list;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.mycart_item,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

        try {
            CartModel item = cartItems.get(position);
            //CONFIGURA NOMBRE, RATING Y PRECIO
            holder.name.setText(item.getName());
            holder.rating.setText(item.getRating());
            holder.price.setText(item.getPrice());
            holder.imageView.setImageResource(item.getImageResource());

            Log.d("CartAdapter", "Image Resource: " + item.getImage());
        }
        catch (Exception e){
            Log.e("CartAdapter", "Error al asignar datos al ViewHolder: " + e);
        }

    }

    @Override
    public int getItemCount() {
        return cartItems.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        ImageView imageView;
        TextView name,rating,price;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            imageView=itemView.findViewById(R.id.refimg);
            name=itemView.findViewById(R.id.detalles_name);
            rating=itemView.findViewById(R.id.detalles_rating);
            price=itemView.findViewById(R.id.precio);
        }


    }

}
