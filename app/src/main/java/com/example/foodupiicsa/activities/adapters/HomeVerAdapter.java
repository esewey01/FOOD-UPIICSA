package com.example.foodupiicsa.activities.adapters;

import android.content.Context;
import android.os.Build;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.example.foodupiicsa.R;
import com.example.foodupiicsa.activities.models.HomeVerModel;

import java.util.ArrayList;
import java.util.List;
public class HomeVerAdapter extends RecyclerView.Adapter<HomeVerAdapter.ViewHolder>{

    Context context;
    ArrayList<HomeVerModel> list;

    public HomeVerAdapter(Context context, ArrayList<HomeVerModel> list) {
        this.context = context;
        this.list = list;
    }


    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(parent.getContext())
                .inflate(R.layout.home_vertical_item, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position){
        holder.imageView.setImageResource(list.get(position).getImage());
        holder.name.setText(list.get(position).getName());
    }

    @Override
    public int getItemCount(){
        return list.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        ImageView imageView;
        TextView name,timing,likes;
        public ViewHolder(@NonNull View itemView){
            super(itemView);
            imageView = itemView.findViewById(com.example.foodupiicsa.R.id.public_ejemplo);
            name=itemView.findViewById(com.example.foodupiicsa.R.id.name);
            timing=itemView.findViewById(com.example.foodupiicsa.R.id.timing);
            likes=itemView.findViewById(com.example.foodupiicsa.R.id.likes);
            ///imageView=itemView.findViewById(R.id.public_ejemplo);

        }
    }
}

