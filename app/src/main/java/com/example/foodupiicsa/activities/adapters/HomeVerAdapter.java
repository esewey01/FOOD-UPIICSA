package com.example.foodupiicsa.activities.adapters;

import android.content.Context;
import android.os.Build;
import android.text.Layout;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.example.foodupiicsa.R;
import com.example.foodupiicsa.activities.models.CarritoFunciones;
import com.example.foodupiicsa.activities.models.CartModel;
import com.example.foodupiicsa.activities.models.HomeHorModel;
import com.example.foodupiicsa.activities.models.HomeVerModel;
import com.google.android.material.bottomsheet.BottomSheetDialog;
import com.google.common.base.FinalizablePhantomReference;

import java.util.ArrayList;
import java.util.List;
public class HomeVerAdapter extends RecyclerView.Adapter<HomeVerAdapter.ViewHolder>{

    private BottomSheetDialog bottomSheetDialog;
    Context context;
    ArrayList<HomeVerModel> list;
    ArrayList <HomeVerModel> originalList;

    public HomeVerAdapter(Context context, ArrayList<HomeVerModel> list) {
        this.context = context;
        this.list = list;
        //GUARDA UNA COPILA DE LA LISTA ORIGINAL
        this.originalList = new ArrayList<>(list);
    }

    //METODO PARA FILTRAR LA LISTA
    public void filter(String text){
        list.clear();
        if (text.isEmpty()){
            list.addAll(originalList);
        }
        else{
            text.toLowerCase();
            for (HomeVerModel item:originalList){
                if (item.getName().toLowerCase().contains(text)){
                    list.add(item);
                }
            }
        }
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(parent.getContext())
                .inflate(R.layout.home_vertical_item, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position){


        final HomeVerModel item=list.get(position);

        final String mName=list.get(position).getName();
        final String mTiming=list.get(position).getTiming();
        final String mRating=list.get(position).getRating();
        final String mPrice=list.get(position).getPrice();
        final int mImage=list.get(position).getImage();

        //
        holder.imageView.setImageResource(list.get(position).getImage());
        holder.name.setText(list.get(position).getName());
        holder.timing.setText(list.get(position).getTiming());
        holder.rating.setText(list.get(position).getRating());
        holder.price.setText(list.get(position).getPrice());

        holder.itemView.setOnClickListener(view-> {
            bottomSheetDialog=new BottomSheetDialog(context,R.style.BottonSheetTheme);
            View sheetView= LayoutInflater.from(context).inflate(R.layout.bottom_sheet_layout,null);


            sheetView.findViewById(R.id.añadir_carrito).setOnClickListener(v-> {
                CarritoFunciones carritoFunciones= CarritoFunciones.getInstance();

                CartModel cartItem=new CartModel(
                  item.getImage(),
                  item.getName(),
                  item.getPrice(),
                  item.getRating()
                );


                //VALIDAR SI YA EXISTE EN EL CARRIT O
                if (carritoFunciones.getCartItems().contains(cartItem)){
                    Toast.makeText(context,"Ya esta en el carrito",Toast.LENGTH_SHORT).show();
                    Log.d("CartAction", "Producto ya agregado: " + item.getName());
                }
                else {
                    carritoFunciones.addItem(cartItem);
                    Log.d("CartAction", "Producto agregado: " + item.getName());
                    Toast.makeText(context,"Agregado al carrito",Toast.LENGTH_SHORT).show();
                }
            });
            ImageView bottomImg=sheetView.findViewById(R.id.bottom_img);
            TextView bottomName=sheetView.findViewById(R.id.bottom_name);
            TextView bottomPrice=sheetView.findViewById(R.id.bottom_price);
            TextView bottomRating=sheetView.findViewById(R.id.bottom_rating);

            bottomName.setText(mName);
            bottomPrice.setText(mPrice);
            bottomRating.setText(mRating);
            bottomImg.setImageResource(mImage);

            bottomSheetDialog.setContentView(sheetView);
            bottomSheetDialog.show();
        });
    }

    @Override
    public int getItemCount(){
        return list.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        ImageView imageView;
        TextView name,timing,rating,price;
        public ViewHolder(@NonNull View itemView){
            super(itemView);
            imageView = itemView.findViewById(R.id.ver_img);
            name=itemView.findViewById(R.id.ver_name );
            timing=itemView.findViewById(com.example.foodupiicsa.R.id.timing);
            rating=itemView.findViewById(R.id.rating);
            price=itemView.findViewById(R.id.price);

        }
    }
}

