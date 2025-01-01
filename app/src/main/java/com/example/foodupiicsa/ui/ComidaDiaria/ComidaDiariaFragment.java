package com.example.foodupiicsa.ui.ComidaDiaria;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.foodupiicsa.R;
import com.example.foodupiicsa.activities.adapters.ComidaDiariaAdapter;
import com.example.foodupiicsa.activities.models.ComidaDiariaModel;

import java.util.ArrayList;
import java.util.List;

public class ComidaDiariaFragment extends Fragment {

    RecyclerView recyclerView;
    List<ComidaDiariaModel> comidaDiariaModels;
    ComidaDiariaAdapter comidaDiariaAdapter;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState){
        View root= inflater.inflate(R.layout.fragment_comida_diaria,container,false);
        recyclerView=root.findViewById(R.id.comida_diaria_rec);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

        comidaDiariaModels=new ArrayList<>();

        comidaDiariaModels.add(new ComidaDiariaModel(R.drawable.breakfast, "DESAYUNO","20%", "Energiza tu mañana con nuestro desayuno completo","DESAYUNO"));
        comidaDiariaModels.add(new ComidaDiariaModel(R.drawable.lunch, "ALMUERZO","10%", "Opciones saludables y deliciosas para tu almuerzo","ALMUERZO"));
        comidaDiariaModels.add(new ComidaDiariaModel(R.drawable.dinner, "CENA","30%", "Cena equilibrada para recargar energías","CENA"));
        comidaDiariaModels.add(new ComidaDiariaModel(R.drawable.sweets, "DULCE","22%", "Satisface tu antojo dulce con nuestras delicias","DULCE"));
        comidaDiariaModels.add(new ComidaDiariaModel(R.drawable.coffe, "CAFFE","10%", "El café perfecto para cualquier ocasión","CAFFE"));


        comidaDiariaAdapter=new ComidaDiariaAdapter(getContext(),comidaDiariaModels);
        recyclerView.setAdapter(comidaDiariaAdapter);
        comidaDiariaAdapter.notifyDataSetChanged();
        return root;

    }

}
