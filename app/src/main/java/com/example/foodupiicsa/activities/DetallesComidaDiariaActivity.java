package com.example.foodupiicsa.activities;

import android.os.Bundle;
import android.widget.ImageView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.foodupiicsa.R;
import com.example.foodupiicsa.activities.adapters.DetalleDiarioAdapter;
import com.example.foodupiicsa.activities.models.DetalleDiarioModel;

import java.util.ArrayList;
import java.util.List;

public class DetallesComidaDiariaActivity extends AppCompatActivity {

    RecyclerView recyclerView;
    List<DetalleDiarioModel> detalleDiarioModelList;
    DetalleDiarioAdapter diarioAdapter;
    ImageView imageView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_detalles_comida_diaria);

        recyclerView=findViewById(R.id.detalles_rec);
        imageView=findViewById(R.id.detalle_img);

        String type=getIntent().getStringExtra("type");

        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        detalleDiarioModelList=new ArrayList<>();
        diarioAdapter=new DetalleDiarioAdapter(detalleDiarioModelList);
        recyclerView.setAdapter(diarioAdapter);


        if(type!=null && type.equalsIgnoreCase("DESAYUNO")){
            detalleDiarioModelList.add(new DetalleDiarioModel(R.drawable.fav1,"DESAYUNO","DESCRIPCIÓN", "4.4","100","10AM A 9AM"));
            detalleDiarioModelList.add(new DetalleDiarioModel(R.drawable.fav2,"DESAYUNO","DESCRIPCIÓN", "4.4","100","10AM A 9AM"));
            detalleDiarioModelList.add(new DetalleDiarioModel(R.drawable.fav3,"DESAYUNO","DESCRIPCIÓN", "4.4","100","10AM A 9AM"));
            diarioAdapter.notifyDataSetChanged();
        }

        if(type!=null && type.equalsIgnoreCase("DULCE")){
            imageView.setImageResource(R.drawable.sweets);
            detalleDiarioModelList.add(new DetalleDiarioModel(R.drawable.s1,"DULCE","DESCRIPCIÓN", "4.4","100","10AM A 9AM"));
            detalleDiarioModelList.add(new DetalleDiarioModel(R.drawable.s2,"DULCE","DESCRIPCIÓN", "4.4","100","10AM A 9AM"));
            detalleDiarioModelList.add(new DetalleDiarioModel(R.drawable.s3,"DULCE","DESCRIPCIÓN", "4.4","100","10AM A 9AM"));
            detalleDiarioModelList.add(new DetalleDiarioModel(R.drawable.s3,"DULCE","DESCRIPCIÓN", "4.4","100","10AM A 9AM"));
            diarioAdapter.notifyDataSetChanged();
        }

    }
}